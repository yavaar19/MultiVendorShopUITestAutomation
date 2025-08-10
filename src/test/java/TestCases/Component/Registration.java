package TestCases.Component;

import PageObjects.RegistrationPage;
import TestCases.Base;
import Utilities.ExcelDataSourceInfo;
import Utilities.ExcelReader;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.io.IOException;
import java.lang.reflect.Method;

public class Registration extends Base {

    private RegistrationPage registrationPage;

    @Test(description = "Check if all input fields are displayed")
    public void TC_MVS_CR_001() {

        registrationPage = basePage.clickRegisterCustomerButton();

        SoftAssert softAssert = new SoftAssert();

        softAssert.assertTrue(registrationPage.isElementDisplayed(registrationPage.getFirstNameFieldLocator()), "First name field is not displayed");
        softAssert.assertTrue(registrationPage.isElementDisplayed(registrationPage.getLastNameFieldLocator()), "Last name field is not displayed");
        softAssert.assertTrue(registrationPage.isElementDisplayed(registrationPage.getEmailIdFieldLocator()), "Email field is not displayed");
        softAssert.assertTrue(registrationPage.isElementDisplayed(registrationPage.getPasswordFieldLocator()), "Password field is not displayed");
        softAssert.assertTrue(registrationPage.isElementDisplayed(registrationPage.getPhoneNoFieldLocator()), "Contact No field is not displayed");
        softAssert.assertTrue(registrationPage.isElementDisplayed(registrationPage.getStreetFieldLocator()), "Street field is not displayed");
        softAssert.assertTrue(registrationPage.isElementDisplayed(registrationPage.getCityFieldLocator()), "City field is not displayed");
        softAssert.assertTrue(registrationPage.isElementDisplayed(registrationPage.getPinCodeFieldLocator()), "Pincode field is not displayed");

        softAssert.assertAll();

    }

    @Test(description = "Check register button is displayed")
    public void TC_MVS_CR_002() {

        registrationPage = basePage.clickRegisterCustomerButton();

        Assert.assertTrue(registrationPage.isElementDisplayed(registrationPage.getRegisterUserButtonLocator()), "Register Button is not displayed");


    }

    @ExcelDataSourceInfo(sheetName = "FirstName", filepath = "src/test/java/TestData/Excel/Component/Registration.xlsx")
    @Test(dataProvider = "data-provider")
    public void TC_MVS_CR_003__016(String firstName, String lastName, String email, String password, String contactNo, String street, String city, String pinCode, String type, String message) {

        registrationPage = basePage.clickRegisterCustomerButton();
        registrationPage.setAllFields(firstName, lastName, email,
                password, contactNo, street, city, pinCode);

        registrationPage.registerUser();

        if(type.equals("Positive")) {

            Assert.assertFalse(registrationPage.isElementDisplayed(registrationPage.getFirstNameErrorLocator()), message);

        } else {

            Assert.assertTrue(registrationPage.isElementDisplayed(registrationPage.getFirstNameErrorLocator()), message);

        }

    }

    @ExcelDataSourceInfo(sheetName = "LastName", filepath = "src/test/java/TestData/Excel/Component/Registration.xlsx")
    @Test(dataProvider = "data-provider")
    public void TC_MVS_CR_017__030(String firstName, String lastName, String email, String password, String contactNo, String street, String city, String pinCode, String type, String message) {

        registrationPage = basePage.clickRegisterCustomerButton();
        registrationPage.setAllFields(firstName, lastName, email,
                password, contactNo, street, city, pinCode);

        registrationPage.registerUser();


        if(type.equals("Positive")) {

            Assert.assertFalse(registrationPage.isElementDisplayed(registrationPage.getLastNameErrorLocator()), message);

        } else {

            Assert.assertTrue(registrationPage.isElementDisplayed(registrationPage.getLastNameErrorLocator()), message);
        }

    }

    @ExcelDataSourceInfo(sheetName = "Email", filepath = "src/test/java/TestData/Excel/Component/Registration.xlsx")
    @Test(dataProvider = "data-provider")
    public void TC_MVS_CR_031__060(String firstName, String lastName, String email, String password, String contactNo, String street, String city, String pinCode, String type, String message) {

        registrationPage = basePage.clickRegisterCustomerButton();
        registrationPage.setAllFields(firstName, lastName, email,
                password, contactNo, street, city, pinCode);

        registrationPage.registerUser();

        if(type.equals("Positive")) {

            Assert.assertFalse(registrationPage.isElementDisplayed(registrationPage.getEmailIdErrorLocator()), message);

        } else {

            Assert.assertTrue(registrationPage.isElementDisplayed(registrationPage.getEmailIdErrorLocator()), message);

        }

    }

    @ExcelDataSourceInfo(sheetName = "Password", filepath = "src/test/java/TestData/Excel/Component/Registration.xlsx")
    @Test(dataProvider = "data-provider")
    public void TC_MVS_CR_061__075(String firstName, String lastName, String email, String password, String contactNo, String street, String city, String pinCode, String type, String message) {

        registrationPage = basePage.clickRegisterCustomerButton();
        registrationPage.setAllFields(firstName, lastName, email,
                password, contactNo, street, city, pinCode);

        registrationPage.registerUser();

        if(type.equals("Positive")) {

            Assert.assertFalse(registrationPage.isElementDisplayed(registrationPage.getPasswordErrorLocator()), message);

        } else {

            Assert.assertTrue(registrationPage.isElementDisplayed(registrationPage.getPasswordErrorLocator()), message);

        }

    }

    @ExcelDataSourceInfo(sheetName = "ContactNo", filepath = "src/test/java/TestData/Excel/Component/Registration.xlsx")
    @Test(dataProvider = "data-provider")
    public void TC_MVS_CR_076__081(String firstName, String lastName, String email, String password, String contactNo, String street, String city, String pinCode, String type, String message) {

        registrationPage = basePage.clickRegisterCustomerButton();
        registrationPage.setAllFields(firstName, lastName, email,
                password, contactNo, street, city, pinCode);

        registrationPage.registerUser();

        if(type.equals("Positive")) {

            Assert.assertFalse(registrationPage.isElementDisplayed(registrationPage.getPhoneNoErrorLocator()), message);

        } else {

            Assert.assertTrue(registrationPage.isElementDisplayed(registrationPage.getPhoneNoErrorLocator()), message);

        }

    }

    @ExcelDataSourceInfo(sheetName = "Street", filepath = "src/test/java/TestData/Excel/Component/Registration.xlsx")
    @Test(dataProvider = "data-provider")
    public void TC_MVS_CR_082__090(String firstName, String lastName, String email, String password, String contactNo, String street, String city, String pinCode, String type, String message) {

        registrationPage = basePage.clickRegisterCustomerButton();
        registrationPage.setAllFields(firstName, lastName, email,
                password, contactNo, street, city, pinCode);

        registrationPage.registerUser();

        if(type.equals("Positive")) {

            Assert.assertFalse(registrationPage.isElementDisplayed(registrationPage.getStreetErrorLocator()), message);

        } else {

            Assert.assertTrue(registrationPage.isElementDisplayed(registrationPage.getStreetFieldLocator()), message);

        }

    }

    @ExcelDataSourceInfo(sheetName = "City", filepath = "src/test/java/TestData/Excel/Component/Registration.xlsx")
    @Test(dataProvider = "data-provider")
    public void TC_MVS_CR_091__098(String firstName, String lastName, String email, String password, String contactNo, String street, String city, String pinCode, String type, String message) {

        registrationPage = basePage.clickRegisterCustomerButton();
        registrationPage.setAllFields(firstName, lastName, email,
                password, contactNo, street, city, pinCode);

        registrationPage.registerUser();

        if(type.equals("Positive")) {

            Assert.assertFalse(registrationPage.isElementDisplayed(registrationPage.getCityErrorLocator()), message);

        } else {

            Assert.assertTrue(registrationPage.isElementDisplayed(registrationPage.getCityErrorLocator()), message);

        }

    }

    @ExcelDataSourceInfo(sheetName = "Pincode", filepath = "src/test/java/TestData/Excel/Component/Registration.xlsx")
    @Test(dataProvider = "data-provider")
    public void TC_MVS_CR_099__108(String firstName, String lastName, String email, String password, String contactNo, String street, String city, String pinCode, String type, String message) {

        registrationPage = basePage.clickRegisterCustomerButton();
        registrationPage.setAllFields(firstName, lastName, email,
                password, contactNo, street, city, pinCode);

        registrationPage.registerUser();

        if(type.equals("Positive")) {

            Assert.assertFalse(registrationPage.isElementDisplayed(registrationPage.getPinCodeErrorLocator()), message);

        } else {

            Assert.assertTrue(registrationPage.isElementDisplayed(registrationPage.getPinCodeErrorLocator()), message);

        }

    }

    @DataProvider(name = "data-provider")
    public Object[][] DataProvider(Method method) throws IOException {

        ExcelDataSourceInfo info = method.getAnnotation(ExcelDataSourceInfo.class);

        ExcelReader excelReader = new ExcelReader(info.filepath(), info.sheetName());

        return excelReader.getData();

    }

}
