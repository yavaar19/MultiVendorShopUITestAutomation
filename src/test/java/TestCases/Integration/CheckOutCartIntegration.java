package TestCases.Integration;

import PageObjects.*;
import TestCases.Base;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class CheckOutCartIntegration extends Base {

    private CheckOutPage checkOutPage;
    private CustomerDashboardPage customerDashboardPage;
    private MyCartPage myCartPage;


    @Test(description = "Confirm customer can place order with valid checkout data", priority = 1)
    public void TC_MVS_IT_059() {

        SoftAssert softAssert = new SoftAssert();

        goToCartPage("Updated Product");

        checkOutPage.setAllFields("John Doe", "4569854547851463", "04/26", "115");
        customerDashboardPage = checkOutPage.clickPayButton();

        checkOutPage.waitForElementToAppear(checkOutPage.getCheckOutToast());

        softAssert.assertEquals(checkOutPage.getCheckOutSuccessToastMessage(),"Order Placed Successful, Check Status in Dashboard!!!");

        MyOrdersPage myOrdersPage = customerDashboardPage.clickMyOrdersButton();

        myOrdersPage.waitForElementToAppear(myOrdersPage.getTableData());

        softAssert.assertTrue(myOrdersPage.orderExist("Updated Product", "1"), "Order does not exist in my order's page");

        softAssert.assertAll();

    }

    @Test(description = "Confirm customer cannot place order with expired credit card", priority = 2)
    public void TC_MVS_IT_060() {

        goToCartPage("Mobile Phone");

        checkOutPage.setCardNumberField("4569854547851463");
        checkOutPage.setValidThroughField("04/23");
        checkOutPage.setCvvField("115");

       checkOutPage.clickPayButton();

        Assert.assertTrue(checkOutPage.isElementDisplayed(checkOutPage.getValidThroughFieldErrorLocator()), "Customer was able to place order with expired credit card");

    }

    @Test(description = "Confirm customer cannot go to checkout with empty cart ", priority = 3)
    public void TC_MVS_IT_061() {

        LogInPage logInPage = basePage.clickLogInButton();

        logInPage.setUserRole("Customer");
        logInPage.setEmail("testcustomer@example.com");
        logInPage.setPassword("omhRbv7017#");

        customerDashboardPage = logInPage.clickLogInButtonOnLogInPage(CustomerDashboardPage.class);

        myCartPage = customerDashboardPage.clickMyCartButton();

        myCartPage.waitForElementToAppear(myCartPage.getTableHeader());
        myCartPage.clickCheckOutButton();

        Assert.assertEquals(myCartPage.getToastMessage(), "No Products In Cart To Order!!!", "Customer was able to go to checkout with no products in cart");

    }

    public void goToCartPage(String productName) {

        LogInPage logInPage = basePage.clickLogInButton();

        logInPage.setUserRole("Customer");
        logInPage.setEmail("testcustomer@example.com");
        logInPage.setPassword("omhRbv7017#");

        customerDashboardPage = logInPage.clickLogInButtonOnLogInPage(CustomerDashboardPage.class);

        ProductPage productPage = customerDashboardPage.viewProduct(productName);

        productPage.setQuantityField("1");
        myCartPage = productPage.clickAddToCart();

        myCartPage.waitForElementToAppear(myCartPage.getTableData());

        checkOutPage = myCartPage.clickCheckOutButton();

    }

}
