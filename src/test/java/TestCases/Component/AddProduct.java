package TestCases.Component;

import PageObjects.AddProductPage;
import PageObjects.LogInPage;
import PageObjects.SellerDashboardPage;
import TestCases.Base;
import Utilities.ExcelDataSourceInfo;
import Utilities.ExcelReader;
import org.openqa.selenium.Keys;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;

public class AddProduct extends Base {

    private AddProductPage addProductPage;

    @Test(description = "Check if all input fields are displayed")
    public void TC_MVS_AP_001() {

        goToAddProductPage();

        SoftAssert softAssert = new SoftAssert();

        softAssert.assertTrue(addProductPage.isElementDisplayed(addProductPage.getProductTitleFieldLocator()), "Product title field is not displayed");
        softAssert.assertTrue(addProductPage.isElementDisplayed(addProductPage.getProductDescriptionFieldLocator()), "Product description field is not displayed");
        softAssert.assertTrue(addProductPage.isElementDisplayed(addProductPage.getCategorySelectionFieldLocator()), "Product category field is not displayed");
        softAssert.assertTrue(addProductPage.isElementDisplayed(addProductPage.getQuantityFieldLocator()), "Product quantity field is not displayed");
        softAssert.assertTrue(addProductPage.isElementDisplayed(addProductPage.getPriceFieldLocator()), "Product price field is not displayed");
        softAssert.assertTrue(addProductPage.isElementDisplayed(addProductPage.getFirstImageFieldLocator()), "Product first image field is not displayed");
        softAssert.assertTrue(addProductPage.isElementDisplayed(addProductPage.getSecondImageFieldLocator()), "Product second image field is not displayed");
        softAssert.assertTrue(addProductPage.isElementDisplayed(addProductPage.getThirdImageFieldLocator()), "Product third image field is not displayed");

        softAssert.assertAll();

        logOut();

    }

    @Test(description = "Check add product button is displayed")
    public void TC_MVS_AP_002() {

        goToAddProductPage();

        Assert.assertTrue(addProductPage.isElementDisplayed(addProductPage.getAddProductButtonLocator()), "Add product button is not displayed");

        logOut();

    }

    @ExcelDataSourceInfo(sheetName = "Product Title", filepath = "src/test/java/TestData/Excel/Component/Add Product.xlsx")
    @Test(dataProvider = "data-provider")
    public void TC_MVS_AP_003__008(String productTitle, String productDescription, String productCategory, String productQuantity, String productPrice, String firstImage, String secondImage, String thirdImage, String type, String message) {

        goToAddProductPage();

        File image1 = new File(firstImage);
        File image2 = new File(secondImage);
        File image3 = new File(thirdImage);

        addProductPage.setAllFields(productTitle, productDescription, productCategory, productQuantity, productPrice, image1, image2, image3);

        addProductPage.clickAddProduct();

        if (type.equals("Positive")) {

            Assert.assertFalse(addProductPage.isElementDisplayed(addProductPage.getProductTitleFieldErrorLocator()), message);

        } else {

            Assert.assertTrue(addProductPage.isElementDisplayed(addProductPage.getProductTitleFieldErrorLocator()), message);

        }

        logOut();

    }

    @ExcelDataSourceInfo(sheetName = "Product Description", filepath = "src/test/java/TestData/Excel/Component/Add Product.xlsx")
    @Test(dataProvider = "data-provider")
    public void TC_MVS_AP_009__014(String productTitle, String productDescription, String productCategory, String productQuantity, String productPrice, String firstImage, String secondImage, String thirdImage, String type, String message) {

        goToAddProductPage();

        File image1 = new File(firstImage);
        File image2 = new File(secondImage);
        File image3 = new File(thirdImage);

        addProductPage.setAllFields(productTitle, productDescription, productCategory, productQuantity, productPrice, image1, image2, image3);

        addProductPage.clickAddProduct();

        if (type.equals("Positive")) {

            Assert.assertFalse(addProductPage.isElementDisplayed(addProductPage.getProductDescriptionFieldErrorLocator()), message);

        } else {

            Assert.assertTrue(addProductPage.isElementDisplayed(addProductPage.getProductDescriptionFieldErrorLocator()), message);

        }

        logOut();

    }

    @ExcelDataSourceInfo(sheetName = "Category", filepath = "src/test/java/TestData/Excel/Component/Add Product.xlsx")
    @Test(dataProvider = "data-provider")
    public void TC_MVS_AP_015__016(String productTitle, String productDescription, String productCategory, String productQuantity, String productPrice, String firstImage, String secondImage, String thirdImage, String type, String message) {

        goToAddProductPage();

        File image1 = new File(firstImage);
        File image2 = new File(secondImage);
        File image3 = new File(thirdImage);

        addProductPage.setProductTitleField(productTitle);
        addProductPage.setProductDescriptionField(productDescription);

        if (!productCategory.isEmpty()) {

            addProductPage.setCategorySelectionField(productCategory);

        }
        addProductPage.setQuantityField(productQuantity);
        addProductPage.setPriceField(productPrice);
        addProductPage.setFirstImage(image1);
        addProductPage.setSecondImage(image2);
        addProductPage.setThirdImage(image3);
        addProductPage.clickAddProduct();

        if (type.equals("Positive")) {

            Assert.assertFalse(addProductPage.isElementDisplayed(addProductPage.getCategorySelectionFieldErrorLocator()), message);

        } else {

            Assert.assertTrue(addProductPage.isElementDisplayed(addProductPage.getCategorySelectionFieldErrorLocator()), message);

        }

        logOut();

    }

    @ExcelDataSourceInfo(sheetName = "Product Quantity", filepath = "src/test/java/TestData/Excel/Component/Add Product.xlsx")
    @Test(dataProvider = "data-provider")
    public void TC_MVS_AP_017__027(String productTitle, String productDescription, String productCategory, String productQuantity, String productPrice, String firstImage, String secondImage, String thirdImage, String type, String message) {

        goToAddProductPage();

        File image1 = new File(firstImage);
        File image2 = new File(secondImage);
        File image3 = new File(thirdImage);

        addProductPage.setAllFields(productTitle, productDescription, productCategory, productQuantity, productPrice, image1, image2, image3);
        System.out.println(productQuantity);
        addProductPage.clickAddProduct();

        if (type.equals("Positive")) {

            Assert.assertFalse(addProductPage.isElementDisplayed(addProductPage.getQuantityFieldErrorLocator()), message);

        } else {

            Assert.assertTrue(addProductPage.isElementDisplayed(addProductPage.getQuantityFieldErrorLocator()), message);

        }

        logOut();

    }

    @Test(description = "Check if number goes up by one when clicking the up arrow in the product quantity field when it is left empty")
    public void TC_MVS_AP_028() {

        goToAddProductPage();

        File image1 = new File("src/test/java/TestData/Images/5MB.jpg");
        File image2 = new File("src/test/java/TestData/Images/5MB.jpg");
        File image3 = new File("src/test/java/TestData/Images/5MB.jpg");

        addProductPage.setAllFields("IPhone 16", "Latest smart phones", "Test", "", "365.99", image1, image2, image3);

        addProductPage.setQuantityField(Keys.UP);

        String result = addProductPage.getQuantityField().getAttribute("value");

        Assert.assertEquals(result, "1", "Clicking the arrow up did not increase number by 1");

        logOut();

    }

    @Test(description = "Check if number goes down by one when clicking the down arrow in the product quantity field when it is left empty")
    public void TC_MVS_AP_029() {

        goToAddProductPage();

        File image1 = new File("src/test/java/TestData/Images/5MB.jpg");
        File image2 = new File("src/test/java/TestData/Images/5MB.jpg");
        File image3 = new File("src/test/java/TestData/Images/5MB.jpg");

        addProductPage.setAllFields("IPhone 16", "Latest smart phones", "Test", "", "365.99", image1, image2, image3);

        addProductPage.setQuantityField(Keys.DOWN);

        String result = addProductPage.getQuantityField().getAttribute("value");

        Assert.assertEquals(result, "-1", "Clicking the arrow down did not decrease number by 1");

        logOut();

    }

    @ExcelDataSourceInfo(sheetName = "Product Price", filepath = "src/test/java/TestData/Excel/Component/Add Product.xlsx")
    @Test(dataProvider = "data-provider")
    public void TC_MVS_AP_032__046(String productTitle, String productDescription, String productCategory, String productQuantity, String productPrice, String firstImage, String secondImage, String thirdImage, String type, String message) {

        goToAddProductPage();

        File image1 = new File(firstImage);
        File image2 = new File(secondImage);
        File image3 = new File(thirdImage);

        addProductPage.setAllFields(productTitle, productDescription, productCategory, productQuantity, productPrice, image1, image2, image3);

        addProductPage.clickAddProduct();

        if (type.equals("Positive")) {

            Assert.assertFalse(addProductPage.isElementDisplayed(addProductPage.getPriceFieldErrorLocator()), message);

        } else {

            Assert.assertTrue(addProductPage.isElementDisplayed(addProductPage.getPriceFieldErrorLocator()), message);

        }

        logOut();

    }


    @Test(description = "Check if number goes up by one when clicking the up arrow in the product price field when it is left empty")
    public void TC_MVS_AP_047() {

        goToAddProductPage();

        File image1 = new File("src/test/java/TestData/Images/5MB.jpg");
        File image2 = new File("src/test/java/TestData/Images/5MB.jpg");
        File image3 = new File("src/test/java/TestData/Images/5MB.jpg");

        addProductPage.setAllFields("IPhone 16", "Latest smart phones", "Test", "5", "", image1, image2, image3);

        addProductPage.setPriceField(Keys.UP);

        String result = addProductPage.getPriceField().getAttribute("value");

        Assert.assertEquals(result, "1", "Clicking the arrow up did not increase number by 1");

        logOut();

    }

    @Test(description = "Check if number goes down by one when clicking the down arrow in the product price field when it is left empty")
    public void TC_MVS_AP_048() {

        goToAddProductPage();

        File image1 = new File("src/test/java/TestData/Images/5MB.jpg");
        File image2 = new File("src/test/java/TestData/Images/5MB.jpg");
        File image3 = new File("src/test/java/TestData/Images/5MB.jpg");

        addProductPage.setAllFields("IPhone 16", "Latest smart phones", "Test", "5", "", image1, image2, image3);

        addProductPage.setPriceField(Keys.DOWN);

        String result = addProductPage.getPriceField().getAttribute("value");

        Assert.assertEquals(result, "-1", "Clicking the arrow down did not decrease number by 1");

        logOut();

    }

    @ExcelDataSourceInfo(sheetName = "First Image", filepath = "src/test/java/TestData/Excel/Component/Add Product.xlsx")
    @Test(dataProvider = "data-provider")
    public void TC_MVS_AP_051__058(String productTitle, String productDescription, String productCategory, String productQuantity, String productPrice, String firstImage, String secondImage, String thirdImage, String type, String message) {

        goToAddProductPage();

        File image1 = new File(firstImage);
        File image2 = new File(secondImage);
        File image3 = new File(thirdImage);

        addProductPage.setProductTitleField(productTitle);
        addProductPage.setProductDescriptionField(productDescription);
        addProductPage.setCategorySelectionField(productCategory);
        addProductPage.setQuantityField(productQuantity);
        addProductPage.setPriceField(productPrice);

        if (!firstImage.isEmpty()) {
            addProductPage.setFirstImage(image1);
        }

        addProductPage.setSecondImage(image2);
        addProductPage.setThirdImage(image3);

        addProductPage.clickAddProduct();

        if (type.equals("Positive")) {

            Assert.assertFalse(addProductPage.isElementDisplayed(addProductPage.getFirstImageFieldErrorLocator()), message);

        } else {

            Assert.assertTrue(addProductPage.isElementDisplayed(addProductPage.getFirstImageFieldErrorLocator()), message);

        }

        logOut();

    }

    @DataProvider(name = "data-provider")
    public Object[][] DataProvider(Method method) throws IOException {

        ExcelDataSourceInfo info = method.getAnnotation(ExcelDataSourceInfo.class);

        ExcelReader excelReader = new ExcelReader(info.filepath(), info.sheetName());

        return excelReader.getData();

    }

    public void goToAddProductPage(){

        LogInPage logInPage = basePage.clickLogInButton();

        logInPage.setEmail("seller@demo.com");
        logInPage.setPassword("abcDEF123!");
        logInPage.setUserRole("Seller");

        SellerDashboardPage sellerDashboardPage = logInPage.clickLogInButtonOnLogInPage(SellerDashboardPage.class);

        addProductPage = sellerDashboardPage.clickAddProductButton();

    }

    public void logOut() {

        addProductPage.clickLogOutButton();

    }

}
