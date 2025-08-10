package TestCases.Integration;

import PageObjects.AdminDashboardPage;
import PageObjects.LogInPage;
import TestCases.Base;
import org.testng.Assert;
import org.testng.annotations.Test;

public class AdminLoginIntegration extends Base {

    private LogInPage logInPage;

    @Test(description = "Confirm newly created admin can log in with correct credentials", priority = 1)
    public void TC_MVS_IT_003() {

        goToLogInPage();

        logInPage.setEmail("testadmin@example.com");
        logInPage.setPassword("KHchjg8215!");
        logInPage.setUserRole("Admin");
        logInPage.clickLogInButtonOnLogInPage(AdminDashboardPage.class);
        logInPage.waitForElementToAppear(logInPage.getLogInToast());

        Assert.assertEquals(logInPage.getToastMessage(), "Logged in sucessful","Login should be successful with valid email and password");

    }

    @Test(description = "Confirm newly created admin can log in with correct credentials with different capitalization in email", priority = 2)
    public void TC_MVS_IT_004() {

        goToLogInPage();

        logInPage.setEmail("TestAdmin@example.com");
        logInPage.setPassword("KHchjg8215!");
        logInPage.setUserRole("Admin");
        logInPage.clickLogInButtonOnLogInPage(AdminDashboardPage.class);
        logInPage.waitForElementToAppear(logInPage.getLogInToast());

        Assert.assertEquals(logInPage.getToastMessage(), "Logged in sucessful", "Login failed with email containing letters in capital");

    }

    @Test(description = "Confirm newly created admin cannot log in with email address containing a leading white space", priority = 3)
    public void TC_MVS_IT_005() {

        goToLogInPage();

        logInPage.setEmail(" testadmin@example.com");
        logInPage.setPassword("KHchjg8215!");
        logInPage.setUserRole("Admin");
        logInPage.clickLogInButtonOnLogInPage(AdminDashboardPage.class);
        logInPage.waitForElementToAppear(logInPage.getLogInToast());

        Assert.assertEquals(logInPage.getToastMessage(), "Invalid email or password.", "Login should fail with invalid credentials with email containing leading white space");

    }

    @Test(description = "Confirm newly created admin cannot log in with email address containing a trailing white space", priority = 4)
    public void TC_MVS_IT_006() {

        goToLogInPage();

        logInPage.setEmail("testadmin@example.com ");
        logInPage.setPassword("KHchjg8215!");
        logInPage.setUserRole("Admin");
        logInPage.clickLogInButtonOnLogInPage(AdminDashboardPage.class);
        logInPage.waitForElementToAppear(logInPage.getLogInToast());

        Assert.assertEquals(logInPage.getToastMessage(), "Invalid email or password.", "Login should fail with invalid credentials with email containing trailing white space");

    }

    @Test(description = "Confirm newly created admin cannot log in with email address missing the @ symbol", priority = 5)
    public void TC_MVS_IT_007() {

        goToLogInPage();

        logInPage.setEmail("testadminexample.com");
        logInPage.setPassword("KHchjg8215!");
        logInPage.setUserRole("Admin");
        logInPage.clickLogInButtonOnLogInPage(AdminDashboardPage.class);

        Assert.assertTrue(logInPage.isElementDisplayed(logInPage.getEmailIdFieldErrorLocator()), "Login should fail with invalid credentials with email missing the @ symbol");

    }

    @Test(description = "Confirm newly created admin cannot log in with email address missing the period", priority = 6)
    public void TC_MVS_IT_008() {

        goToLogInPage();

        logInPage.setEmail("testadmin@examplecom");
        logInPage.setPassword("KHchjg8215!");
        logInPage.setUserRole("Admin");
        logInPage.clickLogInButtonOnLogInPage(AdminDashboardPage.class);

        Assert.assertTrue(logInPage.isElementDisplayed(logInPage.getEmailIdFieldErrorLocator()), "Login should fail with invalid credentials with email missing the period");

    }

    @Test(description = "Confirm newly created admin cannot log in with different password capitalization", priority = 7)
    public void TC_MVS_IT_009() {

        goToLogInPage();

        logInPage.setEmail("testadmin@example.com");
        logInPage.setPassword("khCHJG8215!");
        logInPage.setUserRole("Admin");
        logInPage.clickLogInButtonOnLogInPage(AdminDashboardPage.class);
        logInPage.waitForElementToAppear(logInPage.getLogInToast());

        Assert.assertEquals(logInPage.getToastMessage(), "Invalid email or password.", "Login should fail with invalid credentials with password containing different capitalization than original");

    }

    @Test(description = "Confirm newly created admin cannot log in with incorrect email and correct password", priority = 8)
    public void TC_MVS_IT_010() {

        goToLogInPage();

        logInPage.setEmail("test@example.com");
        logInPage.setPassword("KHchjg8215!");
        logInPage.setUserRole("Admin");
        logInPage.clickLogInButtonOnLogInPage(AdminDashboardPage.class);
        logInPage.waitForElementToAppear(logInPage.getLogInToast());

        Assert.assertEquals(logInPage.getToastMessage(), "Invalid email or password.", "Login should fail with incorrect email and correct password");

    }

    @Test(description = "Confirm newly created admin cannot log in as a customer", priority = 9)
    public void TC_MVS_IT_011() {

        goToLogInPage();

        logInPage.setEmail("testadmin@example.com");
        logInPage.setPassword("KHchjg8215!");
        logInPage.setUserRole("Customer");
        logInPage.clickLogInButtonOnLogInPage(AdminDashboardPage.class);

        Assert.assertFalse(logInPage.isElementDisplayed(logInPage.getLogInErrorToast()), "Login should fail when logging in as a customer with admin credential");

    }

    @Test(description = "Confirm newly created admin cannot log in as a seller", priority = 10)
    public void TC_MVS_IT_012() {

        goToLogInPage();

        logInPage.setEmail("testadmin@example.com");
        logInPage.setPassword("KHchjg8215!");
        logInPage.setUserRole("Seller");
        logInPage.clickLogInButtonOnLogInPage(AdminDashboardPage.class);

        Assert.assertFalse(logInPage.isElementDisplayed(logInPage.getLogInSuccessToast()), "Login should fail when logging in as a seller with admin credential");

    }

    @Test(description = "Confirm newly created admin cannot log in as a delivery person", priority = 11)
    public void TC_MVS_IT_013() {

        goToLogInPage();

        logInPage.setEmail("testadmin@example.com");
        logInPage.setPassword("KHchjg8215!");
        logInPage.setUserRole("Delivery");
        logInPage.clickLogInButtonOnLogInPage(AdminDashboardPage.class);

        Assert.assertFalse(logInPage.isElementDisplayed(logInPage.getLogInSuccessToast()), "Login should fail when logging in as a delivery person with admin credential");

    }

    public void goToLogInPage() {

        logInPage = basePage.clickLogInButton();

    }

}
