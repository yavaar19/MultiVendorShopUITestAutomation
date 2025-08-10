package TestCases.Integration;

import PageObjects.DeliveryDashboardPage;
import PageObjects.LogInPage;
import PageObjects.MyDeliveryOrdersPage;
import TestCases.Base;
import org.testng.Assert;
import org.testng.annotations.Test;

public class DeliveryOrderIntegration extends Base {

    @Test(description = "Confirm delivery person can update order delivery status", priority = 1)
    public void TC_MVS_IT_066() {

        LogInPage logInPage = basePage.clickLogInButton();

        logInPage.setUserRole("Delivery");
        logInPage.setEmail("testdelivery@example.com");
        logInPage.setPassword("uqbVCeD27$");

        DeliveryDashboardPage deliveryDashboardPage = logInPage.clickLogInButtonOnLogInPage(DeliveryDashboardPage.class);

        MyDeliveryOrdersPage myDeliveryOrdersPage = deliveryDashboardPage.clickMyDeliveryOrders();

        myDeliveryOrdersPage.updateDeliveryStatus("Updated Product");

        myDeliveryOrdersPage.setDeliveryDate("12/12/2025");
        myDeliveryOrdersPage.setDeliveryTime("Afternoon");
        myDeliveryOrdersPage.setDeliveryStatus("Delivered");

        myDeliveryOrdersPage.updateStatus();

        basePage = myDeliveryOrdersPage.clickLogOutButton();

        basePage.clickLogInButton();

        logInPage.setUserRole("Delivery");
        logInPage.setEmail("testdelivery@example.com");
        logInPage.setPassword("uqbVCeD27$");

        deliveryDashboardPage = logInPage.clickLogInButtonOnLogInPage(DeliveryDashboardPage.class);

        myDeliveryOrdersPage = deliveryDashboardPage.clickMyDeliveryOrders();

        boolean status = myDeliveryOrdersPage.confirmDelivery("Updated Product", "Delivered", "2025-12-12 Afternoon", "Delivered");

        Assert.assertTrue(status);

    }

}
