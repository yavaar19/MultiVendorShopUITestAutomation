package TestCases.Component;

import PageObjects.AdminDashboardPage;
import PageObjects.DeliveryRegistrationPage;
import PageObjects.LogInPage;
import PageObjects.SellerDashboardPage;
import TestCases.Base;
import Utilities.ExcelDataSourceInfo;
import Utilities.ExcelReader;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.io.IOException;
import java.lang.reflect.Method;

public class DeliveryRegistration extends Base {

    private DeliveryRegistrationPage deliveryRegistrationPage;


    @Test(description = "Check if all input fields are displayed")
    public void TC_MVS_DR_001() {

        getToDeliveryRegistrationPage();

        SoftAssert softAssert = new SoftAssert();

        softAssert.assertTrue(deliveryRegistrationPage.isElementDisplayed(deliveryRegistrationPage.getFirstNameFieldLocator()), "First name field is not displayed");
        softAssert.assertTrue(deliveryRegistrationPage.isElementDisplayed(deliveryRegistrationPage.getLastNameFieldLocator()), "Last name field is not displayed");
        softAssert.assertTrue(deliveryRegistrationPage.isElementDisplayed(deliveryRegistrationPage.getEmailIdFieldLocator()), "Email field is not displayed");
        softAssert.assertTrue(deliveryRegistrationPage.isElementDisplayed(deliveryRegistrationPage.getPasswordFieldLocator()), "Password field is not displayed");
        softAssert.assertTrue(deliveryRegistrationPage.isElementDisplayed(deliveryRegistrationPage.getPhoneNoFieldLocator()), "Contact No field is not displayed");
        softAssert.assertTrue(deliveryRegistrationPage.isElementDisplayed(deliveryRegistrationPage.getStreetFieldLocator()), "Street field is not displayed");
        softAssert.assertTrue(deliveryRegistrationPage.isElementDisplayed(deliveryRegistrationPage.getCityFieldLocator()), "City field is not displayed");
        softAssert.assertTrue(deliveryRegistrationPage.isElementDisplayed(deliveryRegistrationPage.getPinCodeFieldLocator()), "Pincode field is not displayed");

        softAssert.assertAll();

        logOut();

    }

    @Test(description = "Check register button is displayed")
    public void TC_MVS_DR_002() {

        getToDeliveryRegistrationPage();

        Assert.assertTrue(deliveryRegistrationPage.isElementDisplayed(deliveryRegistrationPage.getRegisterUserButtonLocator()), "Register Button is not displayed");

        logOut();

    }

    @ExcelDataSourceInfo(sheetName = "FirstName", filepath = "src/test/java/TestData/Excel/Component/Delivery Registration.xlsx")
    @Test(dataProvider = "data-provider")
    public void TC_MVS_DR_003__016(String firstName, String lastName, String email, String password, String contactNo, String street, String city, String pinCode, String type, String message) {

        getToDeliveryRegistrationPage();

        deliveryRegistrationPage.setAllFields(firstName, lastName, email,
                password, contactNo, street, city, pinCode);

        deliveryRegistrationPage.registerUser();

        if(type.equals("Positive")) {

            Assert.assertFalse(deliveryRegistrationPage.isElementDisplayed(deliveryRegistrationPage.getFirstNameErrorLocator()), message);

        } else {

            Assert.assertTrue(deliveryRegistrationPage.isElementDisplayed(deliveryRegistrationPage.getFirstNameErrorLocator()), message);

        }

        logOut();

    }

    @ExcelDataSourceInfo(sheetName = "LastName", filepath = "src/test/java/TestData/Excel/Component/Delivery Registration.xlsx")
    @Test(dataProvider = "data-provider")
    public void TC_MVS_DR_017__030(String firstName, String lastName, String email, String password, String contactNo, String street, String city, String pinCode, String type, String message) {

        getToDeliveryRegistrationPage();

        deliveryRegistrationPage.setAllFields(firstName, lastName, email,
                password, contactNo, street, city, pinCode);

        deliveryRegistrationPage.registerUser();


        if(type.equals("Positive")) {

            Assert.assertFalse(deliveryRegistrationPage.isElementDisplayed(deliveryRegistrationPage.getLastNameErrorLocator()), message);

        } else {

            Assert.assertTrue(deliveryRegistrationPage.isElementDisplayed(deliveryRegistrationPage.getLastNameErrorLocator()), message);
        }

        logOut();

    }

    @ExcelDataSourceInfo(sheetName = "Email", filepath = "src/test/java/TestData/Excel/Component/Delivery Registration.xlsx")
    @Test(dataProvider = "data-provider")
    public void TC_MVS_DR_031__060(String firstName, String lastName, String email, String password, String contactNo, String street, String city, String pinCode, String type, String message) {

        getToDeliveryRegistrationPage();

        deliveryRegistrationPage.setAllFields(firstName, lastName, email,
                password, contactNo, street, city, pinCode);

        deliveryRegistrationPage.registerUser();

        if(type.equals("Positive")) {

            Assert.assertFalse(deliveryRegistrationPage.isElementDisplayed(deliveryRegistrationPage.getEmailIdErrorLocator()), message);

        } else {

            Assert.assertTrue(deliveryRegistrationPage.isElementDisplayed(deliveryRegistrationPage.getEmailIdErrorLocator()), message);

        }

        logOut();

    }

    @ExcelDataSourceInfo(sheetName = "Password", filepath = "src/test/java/TestData/Excel/Component/Delivery Registration.xlsx")
    @Test(dataProvider = "data-provider")
    public void TC_MVS_DR_061__075(String firstName, String lastName, String email, String password, String contactNo, String street, String city, String pinCode, String type, String message) {

        getToDeliveryRegistrationPage();

        deliveryRegistrationPage.setAllFields(firstName, lastName, email,
                password, contactNo, street, city, pinCode);

        deliveryRegistrationPage.registerUser();

        if(type.equals("Positive")) {

            Assert.assertFalse(deliveryRegistrationPage.isElementDisplayed(deliveryRegistrationPage.getPasswordErrorLocator()), message);

        } else {

            Assert.assertTrue(deliveryRegistrationPage.isElementDisplayed(deliveryRegistrationPage.getPasswordErrorLocator()), message);

        }

        logOut();

    }

    @ExcelDataSourceInfo(sheetName = "ContactNo", filepath = "src/test/java/TestData/Excel/Component/Delivery Registration.xlsx")
    @Test(dataProvider = "data-provider")
    public void TC_MVS_DR_076__081(String firstName, String lastName, String email, String password, String contactNo, String street, String city, String pinCode, String type, String message) {

        getToDeliveryRegistrationPage();

        deliveryRegistrationPage.setAllFields(firstName, lastName, email,
                password, contactNo, street, city, pinCode);

        deliveryRegistrationPage.registerUser();

        if(type.equals("Positive")) {

            Assert.assertFalse(deliveryRegistrationPage.isElementDisplayed(deliveryRegistrationPage.getPhoneNoErrorLocator()), message);

        } else {

            Assert.assertTrue(deliveryRegistrationPage.isElementDisplayed(deliveryRegistrationPage.getPhoneNoErrorLocator()), message);

        }

        logOut();

    }

    @ExcelDataSourceInfo(sheetName = "Street", filepath = "src/test/java/TestData/Excel/Component/Delivery Registration.xlsx")
    @Test(dataProvider = "data-provider")
    public void TC_MVS_DR_082__090(String firstName, String lastName, String email, String password, String contactNo, String street, String city, String pinCode, String type, String message) {

        getToDeliveryRegistrationPage();

        deliveryRegistrationPage.setAllFields(firstName, lastName, email,
                password, contactNo, street, city, pinCode);

        deliveryRegistrationPage.registerUser();

        if(type.equals("Positive")) {

            Assert.assertFalse(deliveryRegistrationPage.isElementDisplayed(deliveryRegistrationPage.getStreetErrorLocator()), message);

        } else {

            Assert.assertTrue(deliveryRegistrationPage.isElementDisplayed(deliveryRegistrationPage.getStreetFieldLocator()), message);

        }

        logOut();

    }

    @ExcelDataSourceInfo(sheetName = "City", filepath = "src/test/java/TestData/Excel/Component/Delivery Registration.xlsx")
    @Test(dataProvider = "data-provider")
    public void TC_MVS_DR_091__098(String firstName, String lastName, String email, String password, String contactNo, String street, String city, String pinCode, String type, String message) {

        getToDeliveryRegistrationPage();

        deliveryRegistrationPage.setAllFields(firstName, lastName, email,
                password, contactNo, street, city, pinCode);

        deliveryRegistrationPage.registerUser();

        if(type.equals("Positive")) {

            Assert.assertFalse(deliveryRegistrationPage.isElementDisplayed(deliveryRegistrationPage.getCityErrorLocator()), message);

        } else {

            Assert.assertTrue(deliveryRegistrationPage.isElementDisplayed(deliveryRegistrationPage.getCityErrorLocator()), message);

        }

        logOut();

    }

    @ExcelDataSourceInfo(sheetName = "Pincode", filepath = "src/test/java/TestData/Excel/Component/Delivery Registration.xlsx")
    @Test(dataProvider = "data-provider")
    public void TC_MVS_DR_099__108(String firstName, String lastName, String email, String password, String contactNo, String street, String city, String pinCode, String type, String message) {

        getToDeliveryRegistrationPage();

        deliveryRegistrationPage.setAllFields(firstName, lastName, email,
                password, contactNo, street, city, pinCode);

        deliveryRegistrationPage.registerUser();

        if(type.equals("Positive")) {

            Assert.assertFalse(deliveryRegistrationPage.isElementDisplayed(deliveryRegistrationPage.getPinCodeErrorLocator()), message);

        } else {

            Assert.assertTrue(deliveryRegistrationPage.isElementDisplayed(deliveryRegistrationPage.getPinCodeErrorLocator()), message);

        }

        logOut();

    }

    @DataProvider(name = "data-provider")
    public Object[][] DataProvider(Method method) throws IOException {

        ExcelDataSourceInfo info = method.getAnnotation(ExcelDataSourceInfo.class);

        ExcelReader excelReader = new ExcelReader(info.filepath(), info.sheetName());

        return excelReader.getData();

    }

    public void getToDeliveryRegistrationPage() {

        LogInPage logInPage = basePage.clickLogInButton();

        logInPage.setEmail("seller@demo.com");
        logInPage.setPassword("abcDEF123!");
        logInPage.setUserRole("Seller");

        SellerDashboardPage sellerDashboardPage = logInPage.clickLogInButtonOnLogInPage(SellerDashboardPage.class);

        deliveryRegistrationPage = sellerDashboardPage.clickRegisterDeliveryButton();

    }

    public void logOut() {

        deliveryRegistrationPage.clickLogOutButton();

    }

}
