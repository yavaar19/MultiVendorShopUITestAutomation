package TestCases.Integration;

import PageObjects.AddReviewPage;
import PageObjects.CustomerDashboardPage;
import PageObjects.LogInPage;
import PageObjects.ProductPage;
import TestCases.Base;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class ProductReviewIntegration extends Base {

    @Test(description = "Confirm customer can add review for product", priority = 1)
    public void TC_MVS_IT_062() {

        SoftAssert softAssert = new SoftAssert();

        LogInPage logInPage = basePage.clickLogInButton();

        logInPage.setUserRole("Customer");
        logInPage.setEmail("testcustomer@example.com");
        logInPage.setPassword("omhRbv7017#");
        CustomerDashboardPage customerDashboardPage = logInPage.clickLogInButtonOnLogInPage(CustomerDashboardPage.class);

        ProductPage productPage = customerDashboardPage.viewProduct("Updated Product");

        AddReviewPage addReviewPage = productPage.clickAddReview();

        addReviewPage.setStar("5");
        addReviewPage.setProductReview("Great Product");
        productPage = addReviewPage.clickAddReviewButton();


        addReviewPage.waitForElementToAppear(addReviewPage.getAddReviewToast());


        softAssert.assertEquals(addReviewPage.getToastMessage(), "product review added successful", "Product review success toast did not appear");

        productPage.waitForElementToAppear(productPage.getProductReview());

        softAssert.assertTrue(productPage.reviewExist("5 /5", "Great Product"), "Product review is not showing in product page");

        softAssert.assertAll();

    }

}
