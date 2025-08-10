package TestCases.Component;

import PageObjects.AdminDashboardPage;
import PageObjects.AdminRegistrationPage;
import PageObjects.LogInPage;
import TestCases.Base;
import Utilities.ExcelDataSourceInfo;
import Utilities.ExcelReader;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.lang.reflect.Method;

public class AdminRegistration extends Base {

    private AdminRegistrationPage adminRegistrationPage;

    @Test(description = "Check if all input fields are displayed")
    public void TC_MVS_AR_001() {

        getToAdminRegistrationPage();

        Assert.assertTrue(adminRegistrationPage.isElementDisplayed(adminRegistrationPage.getEmailIdFieldLocator()), "Email field is not displayed");
        Assert.assertTrue(adminRegistrationPage.isElementDisplayed(adminRegistrationPage.getPasswordFieldLocator()), "Password field is not displayed");

        logOut();

    }

    @Test(description = "Check register button is displayed")
    public void TC_MVS_AR_002() {

        getToAdminRegistrationPage();

        Assert.assertTrue(adminRegistrationPage.isElementDisplayed(adminRegistrationPage.getRegisterButtonFieldLocator()), "Register Button is not displayed");

        logOut();

    }

    @ExcelDataSourceInfo(sheetName = "Admin Registration", filepath = "src/test/java/TestData/Excel/Component/Admin Registration.xlsx")
    @Test(dataProvider = "data-provider")
    public void TC_MVS_AR_003__032(String email, String password, String type, String message) {

        getToAdminRegistrationPage();

        adminRegistrationPage.setEmailId(email);
        adminRegistrationPage.setPassword(password);
        adminRegistrationPage.clickRegisterButton();

        if (type.equals("Positive")) {

            Assert.assertFalse(adminRegistrationPage.isElementDisplayed(adminRegistrationPage.getEmailIdFieldErrorLocator()), message);

        } else {

            Assert.assertTrue(adminRegistrationPage.isElementDisplayed(adminRegistrationPage.getEmailIdFieldErrorLocator()), message);

        }

        logOut();

    }

    @ExcelDataSourceInfo(sheetName = "Password", filepath = "src/test/java/TestData/Excel/Component/Admin Registration.xlsx")
    @Test(dataProvider = "data-provider")
    public void TC_MVS_AR_033__047(String email, String password, String type, String message) {

        getToAdminRegistrationPage();

        adminRegistrationPage.setEmailId(email);
        adminRegistrationPage.setPassword(password);
        adminRegistrationPage.clickRegisterButton();

        if (type.equals("Positive")) {

            Assert.assertFalse(adminRegistrationPage.isElementDisplayed(adminRegistrationPage.getEmailIdFieldErrorLocator()), message);

        } else {

            Assert.assertTrue(adminRegistrationPage.isElementDisplayed(adminRegistrationPage.getPasswordFieldErrorLocator()), message);

        }

        logOut();

    }

    @DataProvider(name = "data-provider")
    public Object[][] DataProvider(Method method) throws IOException {

        ExcelDataSourceInfo info = method.getAnnotation(ExcelDataSourceInfo.class);

        ExcelReader excelReader = new ExcelReader(info.filepath(), info.sheetName());

        return excelReader.getData();

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
