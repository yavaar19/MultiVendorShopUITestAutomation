package TestCases.Integration;

import PageObjects.CustomerDashboardPage;
import PageObjects.LogInPage;
import PageObjects.MyCartPage;
import PageObjects.ProductPage;
import TestCases.Base;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.sql.SQLException;

import static Utilities.Database.cleanCart;

public class ProductCartIntegration extends Base {

    private CustomerDashboardPage customerDashboardPage;
    private ProductPage productPage;
    private MyCartPage myCartPage;

    @Test(description = "Confirm created customer can add product to cart", priority = 1)
    public void TC_MVS_IT_051() {

        goToCustomerDashboardPage();

        productPage = customerDashboardPage.viewProduct("Updated Product");

        productPage.setQuantityField("1");
        myCartPage = productPage.clickAddToCart();

        myCartPage.waitForElementToAppear(myCartPage.getTableData());

        Assert.assertTrue(myCartPage.productExists("Updated Product"), "Customer was not able to add product to cart");;

        logOut();

    }

    @Test(description = "Confirm newly added product remains in cart after logging out and logging back in", priority = 2)
    public void TC_MVS_IT_052() {

        goToCustomerDashboardPage();

        myCartPage = customerDashboardPage.clickMyCartButton();

        myCartPage.waitForElementToAppear(myCartPage.getTableData());

        Assert.assertTrue(myCartPage.productExists("Updated Product"), "Product did not remain in cart after logging out and logging back in");;

        logOut();

    }

    @Test(description = "Confirm quantity of product in cart is increased by 1 when adding the same item to cart", priority = 3)
    public void TC_MVS_IT_053() throws SQLException {

        goToCustomerDashboardPage();

        productPage = customerDashboardPage.viewProduct("Updated Product");

        productPage.setQuantityField("1");
        myCartPage = productPage.clickAddToCart();

        myCartPage.waitForElementToAppear(myCartPage.getTableData());

        Assert.assertEquals(myCartPage.productQuantity("Updated Product"), "2", "Product quantity in cart did not increase by 1 when adding the same item to cart");

        clearCart();
        logOut();

    }

    @Test(description = "Confirm quantity of product in cart is increased by 1 when clicking the + button for product in cart", priority = 4)
    public void TC_MVS_IT_054() {

        SoftAssert softAssert = new SoftAssert();

        goToCustomerDashboardPage();

        productPage = customerDashboardPage.viewProduct("Mobile Phone");

        productPage.setQuantityField("1");
        myCartPage = productPage.clickAddToCart();

        myCartPage.waitForElementToAppear(myCartPage.getTableData());

        softAssert.assertEquals(myCartPage.productQuantity("Mobile Phone"), "1", "Product quantity in cart did not increase by 1 when clicking the + button for product in cart");

        myCartPage.increaseProductQuantity("Mobile Phone");

        myCartPage.waitForElementToAppear(myCartPage.getTableData());

        softAssert.assertEquals(myCartPage.productQuantity("Mobile Phone"), "2", "Product quantity in cart did not increase by 1 when clicking the + button for product in cart");

        softAssert.assertAll();

        logOut();

    }

    @Test(description = "Confirm product is removed from cart when clicking the - button for product in cart from quantity 1", priority = 5, dependsOnMethods = "TC_MVS_IT_056")
    public void TC_MVS_IT_055() {

        SoftAssert softAssert = new SoftAssert();

        goToCustomerDashboardPage();

        productPage = customerDashboardPage.viewProduct("Mobile Phone");

        productPage.setQuantityField("1");
        myCartPage = productPage.clickAddToCart();

        myCartPage.waitForElementToAppear(myCartPage.getTableData());

        softAssert.assertEquals(myCartPage.productQuantity("Mobile Phone") , "1", "Product in cart did not get removed when clicking the - button for product in cart");

        myCartPage.decreaseProductQuantity("Mobile Phone");

        myCartPage.waitForElementToAppear(myCartPage.getTableHeader());

        softAssert.assertFalse(myCartPage.productExists("Mobile Phone") , "Product in cart did not get removed when clicking the - button for product in cart");

        softAssert.assertAll();

        logOut();

    }

    @Test(description = "Confirm product can be deleted from cart page", priority = 6)
    public void TC_MVS_IT_056() {

        SoftAssert softAssert = new SoftAssert();

        goToCustomerDashboardPage();

        myCartPage = customerDashboardPage.clickMyCartButton();

        myCartPage.waitForElementToAppear(myCartPage.getTableData());

        softAssert.assertEquals(myCartPage.productQuantity("Mobile Phone") , "2", "Product did not get deleted from cart");

        myCartPage.deleteProductFromCart("Mobile Phone");

        softAssert.assertFalse(myCartPage.productExists("Mobile Phone"), "Product did not get deleted from cart");;

        logOut();

    }

    @Test(description = "Confirm customer cannot add quantity more than the stock amount available for product", priority = 7)
    public void TC_MVS_IT_057() {

        goToCustomerDashboardPage();

        productPage = customerDashboardPage.viewProduct("Updated Product");

        productPage.setQuantityField("4");
        productPage.clickAddToCart();

        productPage.waitForElementToAppear(productPage.getProductQuantityErrorToast());

        Assert.assertEquals(productPage.getToastAlertMessage(), "Only 3 Quantity is available!!!", "Customer was able to add more than available quantity to cart");;

        logOut();

    }

    @Test(description = "Confirm customer cannot add quantity of 0 for product", priority = 8)
    public void TC_MVS_IT_058() {

        goToCustomerDashboardPage();

        productPage = customerDashboardPage.viewProduct("Updated Product");

        productPage.setQuantityField("0");
        productPage.clickAddToCart();

        Assert.assertEquals(productPage.getQuantityErrorMessage(), "Quantity must be a greater than 0", "Customer was able to add quantity of 0 to cart");;

        logOut();

    }

    public void goToCustomerDashboardPage() {

        LogInPage logInPage = basePage.clickLogInButton();

        logInPage.setUserRole("Customer");
        logInPage.setEmail("testcustomer@example.com");
        logInPage.setPassword("omhRbv7017#");

        customerDashboardPage = logInPage.clickLogInButtonOnLogInPage(CustomerDashboardPage.class);

    }

    public void logOut() {

        customerDashboardPage.clickLogOutButton();

    }

    @AfterClass
    public void clearCart() throws SQLException {

        cleanCart();

    }

}
