package TestCases.Integration;

import PageObjects.AdminDashboardPage;
import PageObjects.AdminRegistrationPage;
import PageObjects.LogInPage;
import TestCases.Base;
import org.testng.Assert;
import org.testng.annotations.Test;

public class AdminRegistrationIntegration extends Base {

    private AdminRegistrationPage adminRegistrationPage;

    @Test(description = "Confirm admin can register another admin account with valid data", priority = 1)
    public void TC_MVS_IT_001() {

        getToAdminRegistrationPage();

        adminRegistrationPage.setEmailId("testadmin@example.com");
        adminRegistrationPage.setPassword("KHchjg8215!");
        adminRegistrationPage.clickRegisterButton();
        adminRegistrationPage.waitForElementToAppear(adminRegistrationPage.getRegistrationToast());

        Assert.assertEquals(adminRegistrationPage.getToastMessage(), "Admin registered Successfully","Admin registration failed");

        logOut();

    }

    @Test(description = "Confirm admin cannot register another admin using existing admin email id", priority = 2)
    public void TC_MVS_IT_002() {

        getToAdminRegistrationPage();

        adminRegistrationPage.setEmailId("testadmin@example.com");
        adminRegistrationPage.setPassword("KHchjg8215!");
        adminRegistrationPage.clickRegisterButton();

        Assert.assertEquals(adminRegistrationPage.getToastMessage(), "User already register with this Email", "Admin registration should not be allowed with already existing email");

        logOut();

    }

    public void getToAdminRegistrationPage() {

        LogInPage logInPage = basePage.clickLogInButton();

        logInPage.setEmail("demo.admin@demo.com");
        logInPage.setPassword("123456");
        logInPage.setUserRole("Admin");

        AdminDashboardPage adminDashboardPage = logInPage.clickLogInButtonOnLogInPage(AdminDashboardPage.class);
        adminRegistrationPage = adminDashboardPage.clickRegisterAdminButton();

    }

    public void logOut() {

        adminRegistrationPage.clickLogOutButton();

    }

}
