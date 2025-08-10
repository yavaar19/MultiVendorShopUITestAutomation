package TestCases.EndToEnd;

import PageObjects.*;
import TestCases.Base;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.io.File;

public class EndToEnd extends Base {

    private LogInPage logInPage;
    private RegistrationPage registrationPage;
    private SoftAssert softAssert;

    @Test(description = "Confirm admin can register another admin account, login and create a category", priority = 1)
    public void TC_MVS_ETE_01() {

        softAssert = new SoftAssert();

        logInPage = basePage.clickLogInButton();

        logInPage.setUserRole("Admin");
        logInPage.setEmail("demo.admin@demo.com");
        logInPage.setPassword("123456");

        AdminDashboardPage adminDashboardPage = logInPage.clickLogInButtonOnLogInPage(AdminDashboardPage.class);

        AdminRegistrationPage adminRegistrationPage = adminDashboardPage.clickRegisterAdminButton();

        adminRegistrationPage.setEmailId("testadmine2e@example.com");
        adminRegistrationPage.setPassword("KHchjg8215!");
        adminDashboardPage = adminRegistrationPage.clickRegisterButton();

        Assert.assertEquals(adminRegistrationPage.getToastMessage(), "Admin registered Successfully","Admin registration failed");

        adminDashboardPage.clickLogOutButton();


        logInPage = basePage.clickLogInButton();

        logInPage.setUserRole("Admin");
        logInPage.setEmail("testadmine2e@example.com");
        logInPage.setPassword("KHchjg8215!");

        adminDashboardPage = logInPage.clickLogInButtonOnLogInPage(AdminDashboardPage.class);

        softAssert.assertEquals(logInPage.getToastMessage(), "Logged in sucessful","Unable to log in with newly created admin account");

        AddCategoryPage addCategoryPage = adminDashboardPage.clickAddCategoryButton();

        addCategoryPage.setCategoryTitle("End To End Category");
        addCategoryPage.setCategoryDescription("Description for end to end testing");

        adminDashboardPage = addCategoryPage.clickAddCategoryButtonOnCategoryPage();

        Assert.assertEquals(addCategoryPage.getToastMessage(), "Category Added Successful", "Add category failed");

        adminDashboardPage.waitForElementToAppear(adminDashboardPage.getSearchButton());

        AllCategoriesPage allCategoriesPage = adminDashboardPage.clickAllCategoryButton();

        allCategoriesPage.waitForElementToAppear(allCategoriesPage.getTableData());

        softAssert.assertTrue(allCategoriesPage.categoryExist("End To End Category"), "Category does not exist!");

        softAssert.assertAll();

    }

    @Test(description = "Confirm seller can register an account, login and create a product", priority = 2)
    public void TC_MVS_ETE_02() {

        softAssert = new SoftAssert();

        registrationPage = basePage.clickRegisterSellerButton();

        registrationPage.setAllFields("John", "Doe", "testsellere2e@example.com", "wnhUzq3515$", "8145863215", "105 Fiction Lane", "Paradise", "1579");
        logInPage = registrationPage.registerUser();

        softAssert.assertEquals(registrationPage.getToastMessage(), "User registered Successfully", "Unable to register seller");

        registrationPage.waitForElementToDisappear(registrationPage.getRegistrationToast());

        logInPage.setUserRole("Seller");
        logInPage.setEmail("testsellere2e@example.com");
        logInPage.setPassword("wnhUzq3515$");

        SellerDashboardPage sellerDashboardPage = logInPage.clickLogInButtonOnLogInPage(SellerDashboardPage.class);

        softAssert.assertEquals(logInPage.getToastMessage(), "Logged in sucessful","Unable to log in with newly created seller account");

        AddProductPage addProductPage = sellerDashboardPage.clickAddProductButton();

        File image1 = new File("src/test/java/TestData/Images/TestProduct/Product1.jpg");
        File image2 = new File("src/test/java/TestData/Images/TestProduct/Product1.jpg");
        File image3 = new File("src/test/java/TestData/Images/TestProduct/Product1.jpg");

        addProductPage.setAllFields("End to End Product", "Test is a test", "End To End Category", "5", "125.85", image1, image2, image3);

        sellerDashboardPage = addProductPage.clickAddProduct();

        softAssert.assertEquals(addProductPage.getToastMessage(), "Product added successful", "Seller was not able to add product");

        sellerDashboardPage.waitForElementToAppear(sellerDashboardPage.getSearchButton());

        ViewMyProductsPage viewMyProductsPage = sellerDashboardPage.clickViewMyProductsButton();

        viewMyProductsPage.waitForElementToAppear(viewMyProductsPage.getTableData());

        boolean exist = viewMyProductsPage.productExist("End to End Product", "Test is a test", "5", "125.85");

        softAssert.assertTrue(exist, "Product does not exist!");

        softAssert.assertAll();

    }

    @Test(description = "Confirm customer can register an account, login and create an order", priority = 3)
    public void TC_MVS_ETE_03() {

        softAssert = new SoftAssert();

        registrationPage = basePage.clickRegisterCustomerButton();

        registrationPage.setAllFields("Jane", "Smith", "testcustomere2e@example.com", "rapBpl1748&", "2581741557", "108 Star Trek Drive", "Shutter", "1935");
        logInPage = registrationPage.registerUser();

        softAssert.assertEquals(registrationPage.getToastMessage(), "User registered Successfully", "Unable to register customer");

        registrationPage.waitForElementToDisappear(registrationPage.getRegistrationToast());

        logInPage.setUserRole("Customer");
        logInPage.setEmail("testcustomere2e@example.com");
        logInPage.setPassword("rapBpl1748&");

        CustomerDashboardPage customerDashboardPage = logInPage.clickLogInButtonOnLogInPage(CustomerDashboardPage.class);

        softAssert.assertEquals(logInPage.getToastMessage(), "Logged in sucessful","Unable to log in with newly created customer account");

        ProductPage productPage = customerDashboardPage.viewProduct("End to End Product");

        productPage.setQuantityField("1");
        MyCartPage myCartPage = productPage.clickAddToCart();

        myCartPage.waitForElementToAppear(myCartPage.getTableData());

        softAssert.assertTrue(myCartPage.productExists("End to End Product"), "Customer was not able to add product to cart");;

        CheckOutPage checkOutPage = myCartPage.clickCheckOutButton();

        checkOutPage.setAllFields("Jane Smith", "4569854547851463", "04/26", "115");

        customerDashboardPage = checkOutPage.clickPayButton();

        checkOutPage.waitForElementToAppear(checkOutPage.getCheckOutToast());

        softAssert.assertEquals(checkOutPage.getCheckOutSuccessToastMessage(),"Order Placed Successful, Check Status in Dashboard!!!");

        checkOutPage.waitForElementToDisappear(checkOutPage.getCheckOutToast());

        customerDashboardPage.waitForElementToAppear(customerDashboardPage.getSearchButton());

        MyOrdersPage myOrdersPage = customerDashboardPage.clickMyOrdersButton();

        myOrdersPage.waitForElementToAppear(myOrdersPage.getTableData());

        softAssert.assertTrue(myOrdersPage.orderExist("End to End Product", "1"), "Order does not exist in my order's page");

        softAssert.assertAll();

    }

    @Test(description = "Confirm customer can login and create a product review", priority = 4)
    public void TC_MVS_ETE_04() {

        softAssert = new SoftAssert();

        logInPage = basePage.clickLogInButton();

        logInPage.setUserRole("Customer");
        logInPage.setEmail("testcustomere2e@example.com");
        logInPage.setPassword("rapBpl1748&");
        CustomerDashboardPage customerDashboardPage = logInPage.clickLogInButtonOnLogInPage(CustomerDashboardPage.class);

        ProductPage productPage = customerDashboardPage.viewProduct("End to End Product");

        AddReviewPage addReviewPage = productPage.clickAddReview();

        addReviewPage.setStar("5");
        addReviewPage.setProductReview("End To End test product review");
        productPage = addReviewPage.clickAddReviewButton();

        addReviewPage.waitForElementToAppear(addReviewPage.getAddReviewToast());

        softAssert.assertEquals(addReviewPage.getToastMessage(), "product review added successful", "Product review success toast did not appear");

        productPage.waitForElementToAppear(productPage.getProductReview());

        softAssert.assertTrue(productPage.reviewExist("5 /5", "End To End test product review"), "Product review is not showing in product page");

        softAssert.assertAll();

    }

    @Test(description = "Confirm seller can register a delivery person, view the order and assign the delivery person to the order", priority = 5)
    public void TC_MVS_ETE_05() {

        softAssert = new SoftAssert();

        logInPage = basePage.clickLogInButton();

        logInPage.setUserRole("Seller");
        logInPage.setEmail("testsellere2e@example.com");
        logInPage.setPassword("wnhUzq3515$");

        SellerDashboardPage sellerDashboardPage = logInPage.clickLogInButtonOnLogInPage(SellerDashboardPage.class);

        DeliveryRegistrationPage deliveryRegistrationPage = sellerDashboardPage.clickRegisterDeliveryButton();

        deliveryRegistrationPage.setAllFields("Mark", "Jasper", "testdeliverye2e@example.com", "unfKHqV19*", "4157981524", "98 Washington Drive", "Ozone", "8225");
        deliveryRegistrationPage.registerUser();

        deliveryRegistrationPage.waitForElementToAppear(deliveryRegistrationPage.getRegistrationToast());

        softAssert.assertEquals(deliveryRegistrationPage.getToastMessage(), "User registered Successfully", "User was not able to register a delivery person");

        deliveryRegistrationPage.waitForElementToDisappear(deliveryRegistrationPage.getRegistrationToast());


        SellerOrdersPage sellerOrdersPage = sellerDashboardPage.clickSellerOrdersButton();

        sellerOrdersPage.assignDelivery("End to End Product");

        sellerOrdersPage.setUserRole("Mark Jasper");

        sellerOrdersPage = sellerOrdersPage.assign();

        ViewDeliveryPersonsPage viewDeliveryPersonsPage = sellerOrdersPage.clickViewDeliveryPersonsButton();

        sellerOrdersPage = viewDeliveryPersonsPage.clickSellerOrdersButton();

        sellerOrdersPage.waitForElementToAppear(sellerOrdersPage.getTableData());

        softAssert.assertEquals(sellerOrdersPage.deliveryPerson("End to End Product"), "Mark", "Order did not get assigned a delivery person");

        softAssert.assertAll();

    }

    @Test(description = "Confirm delivery person can log in, assign delivery date, delivery time and delivery status to order and update status", priority = 6)
    public void TC_MVS_ETE_06() {

        logInPage = basePage.clickLogInButton();

        logInPage.setUserRole("Delivery");
        logInPage.setEmail("testdeliverye2e@example.com");
        logInPage.setPassword("unfKHqV19*");

        DeliveryDashboardPage deliveryDashboardPage = logInPage.clickLogInButtonOnLogInPage(DeliveryDashboardPage.class);

        MyDeliveryOrdersPage myDeliveryOrdersPage = deliveryDashboardPage.clickMyDeliveryOrders();

        myDeliveryOrdersPage.updateDeliveryStatus("End to End Product");

        myDeliveryOrdersPage.setDeliveryDate("12/12/2025");
        myDeliveryOrdersPage.setDeliveryTime("Afternoon");
        myDeliveryOrdersPage.setDeliveryStatus("Delivered");

        myDeliveryOrdersPage = myDeliveryOrdersPage.updateStatus();

        basePage = myDeliveryOrdersPage.clickLogOutButton();

        basePage.clickLogInButton();

        logInPage.setUserRole("Delivery");
        logInPage.setEmail("testdeliverye2e@example.com");
        logInPage.setPassword("unfKHqV19*");

        deliveryDashboardPage = logInPage.clickLogInButtonOnLogInPage(DeliveryDashboardPage.class);

        myDeliveryOrdersPage = deliveryDashboardPage.clickMyDeliveryOrders();

        boolean status = myDeliveryOrdersPage.confirmDelivery("End to End Product", "Delivered", "2025-12-12 Afternoon", "Delivered");

        Assert.assertTrue(status);

    }

}
