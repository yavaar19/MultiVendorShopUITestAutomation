package TestCases.Integration;

import PageObjects.*;
import TestCases.Base;
import Utilities.Retry;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.io.File;

public class SellerProductIntegration extends Base {

    private SellerDashboardPage sellerDashboardPage;
    private AddProductPage addProductPage;
    private ViewMyProductsPage viewMyProductsPage;
    private SoftAssert softAssert;

    @Test(description = "Confirm newly created seller can add product", priority = 1, retryAnalyzer = Retry.class)
    public void TC_MVS_IT_033() {

        goToSellerDashboardPage();

        softAssert = new SoftAssert();

        addProductPage = sellerDashboardPage.clickAddProductButton();

        File image1 = new File("src/test/java/TestData/Images/TestProduct/Product1.jpg");
        File image2 = new File("src/test/java/TestData/Images/TestProduct/Product2.jpg");
        File image3 = new File("src/test/java/TestData/Images/TestProduct/Product3.jpg");

        addProductPage.setAllFields("Laptop", "32GB RAM", "Test Category", "5", "935.25", image1, image2, image3);

        sellerDashboardPage = addProductPage.clickAddProduct();

        addProductPage.waitForElementToAppear(addProductPage.getAddProductToast());

        softAssert.assertEquals(addProductPage.getToastMessage(), "Product added successful", "Seller was not able to add product");

        viewMyProductsPage = sellerDashboardPage.clickViewMyProductsButton();

        viewMyProductsPage.waitForElementToAppear(viewMyProductsPage.getTableData());

        softAssert.assertTrue(viewMyProductsPage.productExist("Laptop", "32GB RAM", "5", "935.25"), "Product does not show in all products page");

        softAssert.assertAll();

    }

    @Test(description = "Confirm newly created seller cannot add product with image files with a combination of size 15MB", priority = 2, retryAnalyzer = Retry.class)
    public void TC_MVS_IT_034() {

        goToSellerDashboardPage();

        softAssert = new SoftAssert();

        addProductPage = sellerDashboardPage.clickAddProductButton();

        File image1 = new File("src/test/java/TestData/Images/5MB.jpg");
        File image2 = new File("src/test/java/TestData/Images/5MB.jpg");
        File image3 = new File("src/test/java/TestData/Images/5MB.jpg");

        addProductPage.setAllFields("Phone", "Smart Phone", "Test Category", "2", "389.99", image1, image2, image3);

        sellerDashboardPage = addProductPage.clickAddProduct();

        addProductPage.waitForElementToAppear(addProductPage.getAddProductToast());

        softAssert.assertEquals(addProductPage.getToastMessage(), "It seems server is down", "Seller should not be allowed to add product");

        viewMyProductsPage = sellerDashboardPage.clickViewMyProductsButton();

        viewMyProductsPage.waitForElementToAppear(viewMyProductsPage.getTableData());

        softAssert.assertFalse(viewMyProductsPage.productExist("Phone", "Smart Phone", "2", "389.99"), "Seller should not be allowed to add a product with images totaling more than 5BM");

        softAssert.assertAll();

    }

    @Test(description = "Confirm seller can update product details", priority = 3, retryAnalyzer = Retry.class)
    public void TC_MVS_IT_035() {

        goToSellerDashboardPage();

        softAssert = new SoftAssert();

        viewMyProductsPage = sellerDashboardPage.clickViewMyProductsButton();

        viewMyProductsPage.waitForElementToAppear(viewMyProductsPage.getTableData());

        UpdateProductPage updateProductPage = viewMyProductsPage.updateProduct("Laptop");

        updateProductPage.clearProductTitleField();
        updateProductPage.setProductTitleField("Updated Product");
        updateProductPage.clearProductDescriptionField();
        updateProductPage.setProductDescriptionField("Product has been updated");
        updateProductPage.clearQuantityField();
        updateProductPage.setQuantityField("3");
        updateProductPage.clearPriceField();
        updateProductPage.setPriceField("850.99");
        updateProductPage.setCategorySelectionField("Test Category");

        viewMyProductsPage = updateProductPage.clickUpdateProduct();

        updateProductPage.waitForElementToAppear(updateProductPage.getUpdateProductToast());

        softAssert.assertEquals(updateProductPage.getToastMessage(), "Product added successful", "Seller was not able to update product");

        viewMyProductsPage.waitForElementToAppear(viewMyProductsPage.getTableData());

        softAssert.assertTrue(viewMyProductsPage.productExist("Updated Product", "Product has been updated", "3", "850.99"), "Product should have been updated");

        softAssert.assertAll();

    }

    @Test(description = "Confirm seller can update product's images", priority = 4, retryAnalyzer = Retry.class)
    public void TC_MVS_IT_036() {

        goToSellerDashboardPage();

        viewMyProductsPage = sellerDashboardPage.clickViewMyProductsButton();

        viewMyProductsPage.waitForElementToAppear(viewMyProductsPage.getTableData());

        UpdateProductPage updateProductPage = viewMyProductsPage.updateProduct("Updated Product");

        File image1 = new File("src/test/java/TestData/Images/Watch1.jpg");
        File image2 = new File("src/test/java/TestData/Images/Watch1.jpg");
        File image3 = new File("src/test/java/TestData/Images/Watch1.jpg");

        updateProductPage.setFirstImage(image1);
        updateProductPage.setSecondImage(image2);
        updateProductPage.setThirdImage(image3);

        updateProductPage.clickUpdateProductImageButton();

        updateProductPage.waitForElementToAppear(updateProductPage.getUpdateProductToast());

        softAssert.assertEquals(updateProductPage.getToastMessage(), "Product Image Updated Successful", "Seller was not able to update product images");

    }

    @Test(description = "Confirm another seller cannot view products created by another seller", priority = 5, retryAnalyzer = Retry.class)
    public void TC_MVS_IT_037() {

        LogInPage logInPage = basePage.clickLogInButton();
        logInPage.setUserRole("Seller");
        logInPage.setEmail("seller@demo.com");
        logInPage.setPassword("abcDEF123!");

        sellerDashboardPage = logInPage.clickLogInButtonOnLogInPage(SellerDashboardPage.class);

        viewMyProductsPage = sellerDashboardPage.clickViewMyProductsButton();

        viewMyProductsPage.waitForElementToAppear(viewMyProductsPage.getTableData());

        Assert.assertFalse(viewMyProductsPage.productExist("Updated Product", "Product has been updated", "3", "850.99"), "Seller should not be able to view other seller's products");

    }

    public void goToSellerDashboardPage() {

        LogInPage logInPage = basePage.clickLogInButton();

        logInPage.setUserRole("Seller");
        logInPage.setEmail("testseller@example.com");
        logInPage.setPassword("omhRbv7017#");

        sellerDashboardPage = logInPage.clickLogInButtonOnLogInPage(SellerDashboardPage.class);

    }

}
