package TestCases.Integration;

import PageObjects.*;
import TestCases.Base;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SellerDeliveryIntegration extends Base {

    private DeliveryRegistrationPage deliveryRegistrationPage;

    @Test(description = "Confirm newly created seller can register delivery", priority = 1)
    public void TC_MVS_IT_063() {

        goToDeliveryRegistration();

        deliveryRegistrationPage.setAllFields("John", "Doe", "testdelivery@example.com", "uqbVCeD27$", "4156352147", "86 John Doe Lane", "Gotham", "2817");
        deliveryRegistrationPage.registerUser();

        deliveryRegistrationPage.waitForElementToAppear(deliveryRegistrationPage.getRegistrationToast());

        Assert.assertEquals(deliveryRegistrationPage.getToastMessage(), "User registered Successfully", "User was not able to register a delivery person");

    }

    @Test(description = "Confirm seller cannot register a delivery person using an existing delivery person email id", priority = 2)
    public void TC_MVS_IT_064() {

        goToDeliveryRegistration();

        deliveryRegistrationPage.setAllFields("Mary", "Smith", "testdelivery@example.com", "uqbVCeD27$", "8251464471", "86 Paradise Lane", "New York", "2817");
        deliveryRegistrationPage.registerUser();

        deliveryRegistrationPage.waitForElementToAppear(deliveryRegistrationPage.getRegistrationToast());

        Assert.assertEquals(deliveryRegistrationPage.getToastMessage(), "User with this Email Id already resgistered!!!", "User was able to register a delivery person with already existing email id");

    }

    @Test(description = "Confirm seller can assign delivery to order", priority = 3)
    public void TC_MVS_IT_065() {

        LogInPage logInPage = basePage.clickLogInButton();

        logInPage.setUserRole("Seller");
        logInPage.setEmail("testseller@example.com");
        logInPage.setPassword("omhRbv7017#");

        SellerDashboardPage sellerDashboardPage = logInPage.clickLogInButtonOnLogInPage(SellerDashboardPage.class);

        SellerOrdersPage sellerOrdersPage = sellerDashboardPage.clickSellerOrdersButton();

        sellerOrdersPage.assignDelivery("Updated Product");

        sellerOrdersPage.setUserRole("John Doe");

        sellerOrdersPage.assign();

        sellerOrdersPage.clickLogOutButton();

        logInPage = basePage.clickLogInButton();

        logInPage.setUserRole("Seller");
        logInPage.setEmail("testseller@example.com");
        logInPage.setPassword("omhRbv7017#");

        sellerDashboardPage = logInPage.clickLogInButtonOnLogInPage(SellerDashboardPage.class);

        sellerOrdersPage = sellerDashboardPage.clickSellerOrdersButton();

        sellerOrdersPage.waitForElementToAppear(sellerOrdersPage.getTableData());

        Assert.assertEquals(sellerOrdersPage.deliveryPerson("Updated Product"), "John", "Order did not get assigned a delivery person");

    }

    public void goToDeliveryRegistration() {

        LogInPage logInPage = basePage.clickLogInButton();

        logInPage.setUserRole("Seller");
        logInPage.setEmail("testseller@example.com");
        logInPage.setPassword("omhRbv7017#");

        SellerDashboardPage sellerDashboardPage = logInPage.clickLogInButtonOnLogInPage(SellerDashboardPage.class);

        deliveryRegistrationPage = sellerDashboardPage.clickRegisterDeliveryButton();

    }

}
