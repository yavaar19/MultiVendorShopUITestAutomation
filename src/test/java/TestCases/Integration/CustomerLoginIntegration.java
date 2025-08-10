package TestCases.Integration;

import PageObjects.LogInPage;
import PageObjects.SellerDashboardPage;
import TestCases.Base;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CustomerLoginIntegration extends Base {

    private LogInPage logInPage;

    @Test(description = "Confirm newly created customer can log in with correct credentials", priority = 1)
    public void TC_MVS_IT_040(){

        logInPage = basePage.clickLogInButton();

        logInPage.setUserRole("Customer");
        logInPage.setEmail("testcustomer@example.com");
        logInPage.setPassword("omhRbv7017#");
        logInPage.clickLogInButtonOnLogInPage(SellerDashboardPage.class);

        logInPage.waitForElementToAppear(logInPage.getLogInToast());

        Assert.assertEquals(logInPage.getToastMessage(), "Logged in sucessful","Login should be successful with valid email and password");

    }

    @Test(description = "Confirm newly created customer can log in with correct credentials with different capitalization in email", priority = 2)
    public void TC_MVS_IT_041(){

        logInPage = basePage.clickLogInButton();

        logInPage.setUserRole("Customer");
        logInPage.setEmail("TestCustomer@example.com");
        logInPage.setPassword("omhRbv7017#");
        logInPage.clickLogInButtonOnLogInPage(SellerDashboardPage.class);

        logInPage.waitForElementToAppear(logInPage.getLogInToast());

        Assert.assertEquals(logInPage.getToastMessage(), "Logged in sucessful", "Login failed with email containing letters in capital");

    }

    @Test(description = "Confirm newly created customer cannot log in with email address containing a leading white space", priority = 3)
    public void TC_MVS_IT_042(){

        logInPage = basePage.clickLogInButton();

        logInPage.setUserRole("Customer");
        logInPage.setEmail(" testcustomer@example.com");
        logInPage.setPassword("omhRbv7017#");
        logInPage.clickLogInButtonOnLogInPage(SellerDashboardPage.class);

        logInPage.waitForElementToAppear(logInPage.getLogInToast());

        Assert.assertEquals(logInPage.getToastMessage(), "Invalid email or password.", "Login should fail with invalid credentials with email containing leading white space");

    }

    @Test(description = "Confirm newly created customer cannot log in with email address containing a trailing white space", priority = 4)
    public void TC_MVS_IT_043(){

        logInPage = basePage.clickLogInButton();

        logInPage.setUserRole("Customer");
        logInPage.setEmail("testcustomer@example.com ");
        logInPage.setPassword("omhRbv7017#");
        logInPage.clickLogInButtonOnLogInPage(SellerDashboardPage.class);

        logInPage.waitForElementToAppear(logInPage.getLogInToast());

        Assert.assertEquals(logInPage.getToastMessage(), "Invalid email or password.", "Login should fail with invalid credentials with email containing trailing white space");

    }

    @Test(description = "Confirm newly created customer cannot log in with email address missing the @ symbol", priority = 5)
    public void TC_MVS_IT_044(){

        logInPage = basePage.clickLogInButton();

        logInPage.setUserRole("Customer");
        logInPage.setEmail("testcustomerexample.com");
        logInPage.setPassword("omhRbv7017#");
        logInPage.clickLogInButtonOnLogInPage(SellerDashboardPage.class);

        Assert.assertTrue(logInPage.isElementDisplayed(logInPage.getEmailIdFieldErrorLocator()), "Login should fail with invalid credentials with email missing the @ symbol");

    }

    @Test(description = "Confirm newly created customer cannot log in with email address missing the period", priority = 6)
    public void TC_MVS_IT_045(){

        logInPage = basePage.clickLogInButton();

        logInPage.setUserRole("Customer");
        logInPage.setEmail("testcustomer@examplecom");
        logInPage.setPassword("omhRbv7017#");
        logInPage.clickLogInButtonOnLogInPage(SellerDashboardPage.class);

        Assert.assertTrue(logInPage.isElementDisplayed(logInPage.getEmailIdFieldErrorLocator()), "Login should fail with invalid credentials with email missing the period");

    }

    @Test(description = "Confirm newly created customer cannot log in with different password capitalization", priority = 7)
    public void TC_MVS_IT_046(){

        logInPage = basePage.clickLogInButton();

        logInPage.setUserRole("Customer");
        logInPage.setEmail("testcustomer@example.com");
        logInPage.setPassword("OMHrBV7017#");
        logInPage.clickLogInButtonOnLogInPage(SellerDashboardPage.class);

        logInPage.waitForElementToAppear(logInPage.getLogInToast());

        Assert.assertEquals(logInPage.getToastMessage(), "Invalid email or password.", "Login should fail with invalid credentials with password containing different capitalization than original");

    }

    @Test(description = "Confirm newly created customer cannot log in with incorrect email and correct password", priority = 8)
    public void TC_MVS_IT_047(){

        logInPage = basePage.clickLogInButton();

        logInPage.setUserRole("Customer");
        logInPage.setEmail("test@example.com");
        logInPage.setPassword("omhRbv7017#");
        logInPage.clickLogInButtonOnLogInPage(SellerDashboardPage.class);

        logInPage.waitForElementToAppear(logInPage.getLogInToast());

        Assert.assertEquals(logInPage.getToastMessage(), "Invalid email or password.", "Login should fail with incorrect email and correct password");

    }

    @Test(description = "Confirm newly created customer cannot log in as an admin", priority = 9)
    public void TC_MVS_IT_048(){

        logInPage = basePage.clickLogInButton();

        logInPage.setUserRole("Admin");
        logInPage.setEmail("testcustomer@example.com");
        logInPage.setPassword("omhRbv7017#");
        logInPage.clickLogInButtonOnLogInPage(SellerDashboardPage.class);

        Assert.assertFalse(logInPage.isElementDisplayed(logInPage.getLogInSuccessToast()), "Login should fail when logging in as a admin with customer credential");

    }

    @Test(description = "Confirm newly created customer cannot log in as an seller", priority = 10)
    public void TC_MVS_IT_049(){

        logInPage = basePage.clickLogInButton();

        logInPage.setUserRole("Seller");
        logInPage.setEmail("testcustomer@example.com");
        logInPage.setPassword("omhRbv7017#");
        logInPage.clickLogInButtonOnLogInPage(SellerDashboardPage.class);

        Assert.assertFalse(logInPage.isElementDisplayed(logInPage.getLogInSuccessToast()), "Login should fail when logging in as a seller with customer credential");

    }

    @Test(description = "Confirm newly created customer cannot log in as an delivery person", priority = 11)
    public void TC_MVS_IT_050(){

        logInPage = basePage.clickLogInButton();

        logInPage.setUserRole("Delivery");
        logInPage.setEmail("testcustomer@example.com");
        logInPage.setPassword("omhRbv7017#");
        logInPage.clickLogInButtonOnLogInPage(SellerDashboardPage.class);

        Assert.assertFalse(logInPage.isElementDisplayed(logInPage.getLogInSuccessToast()), "Login should fail when logging in as a delivery person with customer credential");

    }

}
