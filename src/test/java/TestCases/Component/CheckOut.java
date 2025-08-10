package TestCases.Component;

import PageObjects.*;
import TestCases.Base;
import Utilities.ExcelDataSourceInfo;
import Utilities.ExcelReader;
import Utilities.Retry;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.io.IOException;
import java.lang.reflect.Method;

public class CheckOut extends Base {

     private CheckOutPage checkOutPage;

    @Test(description = "Check if all input fields are displayed", retryAnalyzer = Retry.class)
    public void TC_MVS_PD_001() {

        goToPaymentDetails();

        SoftAssert softAssert = new SoftAssert();

        softAssert.assertTrue(checkOutPage.isElementDisplayed(checkOutPage.getNameFieldLocator()), "Name field is not displayed");
        softAssert.assertTrue(checkOutPage.isElementDisplayed(checkOutPage.getCardNumberFieldLocator()), "Card number field is not displayed");
        softAssert.assertTrue(checkOutPage.isElementDisplayed(checkOutPage.getValidThroughFieldLocator()), "Valid through field is not displayed");
        softAssert.assertTrue(checkOutPage.isElementDisplayed(checkOutPage.getCvvFieldLocator()), "CVV field is not displayed");

        softAssert.assertAll();

        logOut();

    }

    @Test(description = "Check Pay button is displayed")
    public void TC_MVS_PD_002() {

        goToPaymentDetails();

        Assert.assertTrue(checkOutPage.isElementDisplayed(checkOutPage.getPayButtonLocator()), "Pay button is not displayed");

        logOut();

    }

    @ExcelDataSourceInfo(sheetName = "Name on Card", filepath = "src/test/java/TestData/Excel/Component/Check Out.xlsx")
    @Test(dataProvider = "data-provider")
    public void TC_MVS_PD_004__014(String name, String cardNumber, String validThrough, String CVV, String type, String message) {

        goToPaymentDetails();

        checkOutPage.setAllFields(name, cardNumber, validThrough, CVV);

        checkOutPage.clickPayButton();

        if (type.equals("Positive")) {

            Assert.assertFalse(checkOutPage.isElementDisplayed(checkOutPage.getNameFieldErrorLocator()), message);

        } else {

            Assert.assertTrue(checkOutPage.isElementDisplayed(checkOutPage.getNameFieldErrorLocator()), message);

        }

        logOut();

    }

    @ExcelDataSourceInfo(sheetName = "Card Number", filepath = "src/test/java/TestData/Excel/Component/Check Out.xlsx")
    @Test(dataProvider = "data-provider")
    public void TC_MVS_PD_015__024(String name, String cardNumber, String validThrough, String CVV, String type, String message) {

        goToPaymentDetails();

        checkOutPage.setAllFields(name, cardNumber, validThrough, CVV);

        checkOutPage.clickPayButton();

        if (type.equals("Positive")) {

            Assert.assertFalse(checkOutPage.isElementDisplayed(checkOutPage.getCardNumberFieldErrorLocator()), message);

        } else {

            Assert.assertTrue(checkOutPage.isElementDisplayed(checkOutPage.getCardNumberFieldErrorLocator()), message);

        }

        logOut();

    }

    @ExcelDataSourceInfo(sheetName = "Valid Through", filepath = "src/test/java/TestData/Excel/Component/Check Out.xlsx")
    @Test(dataProvider = "data-provider")
    public void TC_MVS_PD_025__042(String name, String cardNumber, String validThrough, String CVV, String type, String message) {

        goToPaymentDetails();

        checkOutPage.setAllFields(name, cardNumber, validThrough, CVV);

        checkOutPage.clickPayButton();

        if (type.equals("Positive")) {

            Assert.assertFalse(checkOutPage.isElementDisplayed(checkOutPage.getValidThroughFieldErrorLocator()), message);

        } else {

            Assert.assertTrue(checkOutPage.isElementDisplayed(checkOutPage.getValidThroughFieldErrorLocator()), message);

        }

        logOut();

    }

    @ExcelDataSourceInfo(sheetName = "CVV", filepath = "src/test/java/TestData/Excel/Component/Check Out.xlsx")
    @Test(dataProvider = "data-provider")
    public void TC_MVS_PD_043__052(String name, String cardNumber, String validThrough, String CVV, String type, String message) {

        goToPaymentDetails();

        checkOutPage.setAllFields(name, cardNumber, validThrough, CVV);

        checkOutPage.clickPayButton();

        if (type.equals("Positive")) {

            Assert.assertFalse(checkOutPage.isElementDisplayed(checkOutPage.getCvvFieldErrorLocator()), message);

        } else {

            Assert.assertTrue(checkOutPage.isElementDisplayed(checkOutPage.getCvvFieldErrorLocator()), message);

        }

        logOut();

    }

    @DataProvider(name = "data-provider")
    public Object[][] DataProvider(Method method) throws IOException {

        ExcelDataSourceInfo info = method.getAnnotation(ExcelDataSourceInfo.class);

        ExcelReader excelReader = new ExcelReader(info.filepath(), info.sheetName());

        return excelReader.getData();

    }

    public void goToPaymentDetails() {

        ProductPage productPage;
        MyCartPage myCartPage;

        LogInPage logInPage = basePage.clickLogInButton();
        logInPage.setUserRole("Customer");
        logInPage.setEmail("customer@demo.com");
        logInPage.setPassword("defABC123!");

        CustomerDashboardPage customerDashboardPage = logInPage.clickLogInButtonOnLogInPage(CustomerDashboardPage.class);

        productPage = customerDashboardPage.viewProduct("Mobile Phone");
        productPage.setQuantityField("1");
        myCartPage = productPage.clickAddToCart();
        myCartPage.waitForElementToAppear(myCartPage.getTableData());
        checkOutPage = myCartPage.clickCheckOutButton();

    }

    public void logOut() {

        checkOutPage.clickLogOutButton();

    }

}