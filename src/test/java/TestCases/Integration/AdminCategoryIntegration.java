package TestCases.Integration;

import PageObjects.AddCategoryPage;
import PageObjects.AdminDashboardPage;
import PageObjects.AllCategoriesPage;
import PageObjects.LogInPage;
import TestCases.Base;
import org.testng.Assert;
import org.testng.annotations.Test;

public class AdminCategoryIntegration extends Base {

    private AdminDashboardPage adminDashboardPage;
    private AddCategoryPage addCategoryPage;
    private AllCategoriesPage allCategoriesPage;

    @Test(description = "Confirm admin can add a category", priority = 1)
    public void TC_MVS_IT_014() {

        getToAdminDashboardPage();
        addCategoryPage = adminDashboardPage.clickAddCategoryButton();

        addCategoryPage.setCategoryTitle("Test Category");
        addCategoryPage.setCategoryDescription("This is a test category");
        addCategoryPage.clickAddCategoryButtonOnCategoryPage();

        addCategoryPage.waitForElementToAppear(addCategoryPage.getAddCategoryToast());

        Assert.assertEquals(addCategoryPage.getToastMessage(), "Category Added Successful", "Add category failed");

        logOut();

    }

    @Test(description = "Confirm admin can add an already existing category", priority = 2)
    public void TC_MVS_IT_015() {

        getToAdminDashboardPage();
        addCategoryPage = adminDashboardPage.clickAddCategoryButton();

        addCategoryPage.setCategoryTitle("Test Category");
        addCategoryPage.setCategoryDescription("This is a test category");
        addCategoryPage.clickAddCategoryButtonOnCategoryPage();

        addCategoryPage.waitForElementToAppear(addCategoryPage.getAddCategoryToast());

        Assert.assertEquals(addCategoryPage.getToastMessage(), "Category Added Successful", "Adding existing category failed");

        logOut();

    }

    @Test(description = "Confirm admin can view category added by another admin", priority = 3)
    public void TC_MVS_IT_016() {

        LogInPage logInPage = basePage.clickLogInButton();

        logInPage.setEmail("demo.admin@demo.com");
        logInPage.setPassword("123456");
        logInPage.setUserRole("Admin");

        AdminDashboardPage adminDashboardPage = logInPage.clickLogInButtonOnLogInPage(AdminDashboardPage.class);

        allCategoriesPage = adminDashboardPage.clickAllCategoryButton();

        allCategoriesPage.waitForElementToAppear(allCategoriesPage.getTableData());

        Assert.assertTrue(allCategoriesPage.categoryExist("Test Category"), "Category does not exist");

        allCategoriesPage.clickLogOutButton();

    }

    @Test(description = "Confirm admin can update a category", priority = 4)
    public void TC_MVS_IT_017() {

        getToAdminDashboardPage();
        allCategoriesPage = adminDashboardPage.clickAllCategoryButton();
        allCategoriesPage.waitForElementToAppear(allCategoriesPage.getTableData());

        addCategoryPage = allCategoriesPage.updateCategory("Test Category");

        addCategoryPage.clearCategoryTitle();
        addCategoryPage.setCategoryTitle("Updated Category");

        addCategoryPage.clearCategoryDescription();
        addCategoryPage.setCategoryDescription("This is an updated category");

        adminDashboardPage = addCategoryPage.clickAddCategoryButtonOnCategoryPage();

        addCategoryPage.waitForElementToAppear(addCategoryPage.getAddCategoryToast());

        adminDashboardPage.clickAllCategoryButton();
        allCategoriesPage.waitForElementToAppear(allCategoriesPage.getTableData());

        Assert.assertTrue(allCategoriesPage.categoryExist("Updated Category"), "Category did not get updated");

        allCategoriesPage.clickLogOutButton();

    }

    @Test(description = "Confirm admin can delete a category", priority = 5)
    public void TC_MVS_IT_018() {

        getToAdminDashboardPage();
        allCategoriesPage = adminDashboardPage.clickAllCategoryButton();
        allCategoriesPage.waitForElementToAppear(allCategoriesPage.getTableData());

        allCategoriesPage.deleteCategory("Updated Category");

        allCategoriesPage.waitForElementToAppear(allCategoriesPage.getAllCategoriesToast());
        allCategoriesPage.waitForElementToDisappear(allCategoriesPage.getAllCategoriesToast());
        allCategoriesPage.waitForElementToAppear(allCategoriesPage.getTableData());

        Assert.assertFalse(allCategoriesPage.categoryExist("Updated Category"), "Category did not get deleted");

        allCategoriesPage.clickLogOutButton();

    }


    public void getToAdminDashboardPage() {

        LogInPage logInPage = basePage.clickLogInButton();

        logInPage.setEmail("testadmin@example.com");
        logInPage.setPassword("KHchjg8215!");
        logInPage.setUserRole("Admin");

        adminDashboardPage = logInPage.clickLogInButtonOnLogInPage(AdminDashboardPage.class);

    }

    public void logOut() {

        addCategoryPage.clickLogOutButton();

    }

}
