package TestCases.Integration;

import PageObjects.LogInPage;
import PageObjects.SellerDashboardPage;
import TestCases.Base;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SellerLoginIntegration extends Base {

    private LogInPage logInPage;

    @Test(description = "Confirm newly created seller can log in with correct credentials", priority = 1)
    public void TC_MVS_IT_022(){

        logInPage = basePage.clickLogInButton();

        logInPage.setUserRole("Seller");
        logInPage.setEmail("testseller@example.com");
        logInPage.setPassword("omhRbv7017#");
        logInPage.clickLogInButtonOnLogInPage(SellerDashboardPage.class);

        logInPage.waitForElementToAppear(logInPage.getLogInToast());

        Assert.assertEquals(logInPage.getToastMessage(), "Logged in sucessful","Login should be successful with valid email and password");

    }

    @Test(description = "Confirm newly created seller can log in with correct credentials with different capitalization in email", priority = 2)
    public void TC_MVS_IT_023(){

        logInPage = basePage.clickLogInButton();

        logInPage.setUserRole("Seller");
        logInPage.setEmail("TestSeller@example.com");
        logInPage.setPassword("omhRbv7017#");
        logInPage.clickLogInButtonOnLogInPage(SellerDashboardPage.class);

        logInPage.waitForElementToAppear(logInPage.getLogInToast());

        Assert.assertEquals(logInPage.getToastMessage(), "Logged in sucessful", "Login failed with email containing letters in capital");

    }

    @Test(description = "Confirm newly created seller cannot log in with email address containing a leading white space", priority = 3)
    public void TC_MVS_IT_024(){

        logInPage = basePage.clickLogInButton();

        logInPage.setUserRole("Seller");
        logInPage.setEmail(" testseller@example.com");
        logInPage.setPassword("omhRbv7017#");
        logInPage.clickLogInButtonOnLogInPage(SellerDashboardPage.class);

        logInPage.waitForElementToAppear(logInPage.getLogInToast());

        Assert.assertEquals(logInPage.getToastMessage(), "Invalid email or password.", "Login should fail with invalid credentials with email containing leading white space");

    }

    @Test(description = "Confirm newly created seller cannot log in with email address containing a trailing white space", priority = 4)
    public void TC_MVS_IT_025(){

        logInPage = basePage.clickLogInButton();

        logInPage.setUserRole("Seller");
        logInPage.setEmail("testseller@example.com ");
        logInPage.setPassword("omhRbv7017#");
        logInPage.clickLogInButtonOnLogInPage(SellerDashboardPage.class);

        logInPage.waitForElementToAppear(logInPage.getLogInToast());

        Assert.assertEquals(logInPage.getToastMessage(), "Invalid email or password.", "Login should fail with invalid credentials with email containing trailing white space");

    }

    @Test(description = "Confirm newly created seller cannot log in with email address missing the @ symbol", priority = 5)
    public void TC_MVS_IT_026(){

        logInPage = basePage.clickLogInButton();

        logInPage.setUserRole("Seller");
        logInPage.setEmail("testsellerexample.com");
        logInPage.setPassword("omhRbv7017#");
        logInPage.clickLogInButtonOnLogInPage(SellerDashboardPage.class);

        Assert.assertTrue(logInPage.isElementDisplayed(logInPage.getEmailIdFieldErrorLocator()), "Login should fail with invalid credentials with email missing the @ symbol");

    }

    @Test(description = "Confirm newly created seller cannot log in with email address missing the period", priority = 6)
    public void TC_MVS_IT_027(){

        logInPage = basePage.clickLogInButton();

        logInPage.setUserRole("Seller");
        logInPage.setEmail("testseller@examplecom");
        logInPage.setPassword("omhRbv7017#");
        logInPage.clickLogInButtonOnLogInPage(SellerDashboardPage.class);

        Assert.assertTrue(logInPage.isElementDisplayed(logInPage.getEmailIdFieldErrorLocator()), "Login should fail with invalid credentials with email missing the period");

    }

    @Test(description = "Confirm newly created seller cannot log in with different password capitalization", priority = 7)
    public void TC_MVS_IT_028(){

        logInPage = basePage.clickLogInButton();

        logInPage.setUserRole("Seller");
        logInPage.setEmail("testseller@example.com");
        logInPage.setPassword("OMHrBV7017#");
        logInPage.clickLogInButtonOnLogInPage(SellerDashboardPage.class);

        logInPage.waitForElementToAppear(logInPage.getLogInToast());

        Assert.assertEquals(logInPage.getToastMessage(), "Invalid email or password.", "Login should fail with invalid credentials with password containing different capitalization than original");

    }

    @Test(description = "Confirm newly created seller cannot log in with incorrect email and correct password", priority = 8)
    public void TC_MVS_IT_029(){

        logInPage = basePage.clickLogInButton();

        logInPage.setUserRole("Seller");
        logInPage.setEmail("test@example.com");
        logInPage.setPassword("omhRbv7017#");
        logInPage.clickLogInButtonOnLogInPage(SellerDashboardPage.class);

        logInPage.waitForElementToAppear(logInPage.getLogInToast());

        Assert.assertEquals(logInPage.getToastMessage(), "Invalid email or password.", "Login should fail with incorrect email and correct password");

    }

    @Test(description = "Confirm newly created seller cannot log in as an admin", priority = 9)
    public void TC_MVS_IT_030(){

        logInPage = basePage.clickLogInButton();

        logInPage.setUserRole("Admin");
        logInPage.setEmail("testseller@example.com");
        logInPage.setPassword("omhRbv7017#");
        logInPage.clickLogInButtonOnLogInPage(SellerDashboardPage.class);

        Assert.assertFalse(logInPage.isElementDisplayed(logInPage.getLogInSuccessToast()), "Login should fail when logging in as a admin with seller credential");

    }

    @Test(description = "Confirm newly created seller cannot log in as a customer", priority = 10)
    public void TC_MVS_IT_031(){

        logInPage = basePage.clickLogInButton();

        logInPage.setUserRole("Customer");
        logInPage.setEmail("testseller@example.com");
        logInPage.setPassword("omhRbv7017#");
        logInPage.clickLogInButtonOnLogInPage(SellerDashboardPage.class);

        Assert.assertFalse(logInPage.isElementDisplayed(logInPage.getLogInSuccessToast()), "Login should fail when logging in as a customer with seller credential");

    }

    @Test(description = "Confirm newly created seller cannot log in as a delivery person", priority = 11)
    public void TC_MVS_IT_032(){

        logInPage = basePage.clickLogInButton();

        logInPage.setUserRole("Delivery");
        logInPage.setEmail("testseller@example.com");
        logInPage.setPassword("omhRbv7017#");
        logInPage.clickLogInButtonOnLogInPage(SellerDashboardPage.class);

        Assert.assertFalse(logInPage.isElementDisplayed(logInPage.getLogInSuccessToast()), "Login should fail when logging in as a delivery person with seller credential");

    }

}
