package TestCases.Component;

import PageObjects.AddCategoryPage;
import PageObjects.AdminDashboardPage;
import PageObjects.LogInPage;
import TestCases.Base;
import Utilities.ExcelDataSourceInfo;
import Utilities.ExcelReader;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.lang.reflect.Method;

public class AddCategory extends Base {

    private AddCategoryPage addCategoryPage;

    @Test(description = "Check if all input fields are displayed")
    public void TC_MVS_AC_001(){

        getToAddCategoryPage();

        Assert.assertTrue(addCategoryPage.isElementDisplayed(addCategoryPage.getCategoryTitleFieldLocator()), "Title field is not displayed");
        Assert.assertTrue(addCategoryPage.isElementDisplayed(addCategoryPage.getCategoryDescriptionFieldLocator()), "Description field is not displayed");

        logOut();

    }

    @Test(description = "Check add category button is displayed")
    public void TC_MVS_AC_002(){

        getToAddCategoryPage();

        Assert.assertTrue(addCategoryPage.isElementDisplayed(addCategoryPage.getAddCategoryButtonLocator()), "Add Category Button is not displayed");

    }

    @ExcelDataSourceInfo(sheetName = "Title", filepath = "src/test/java/TestData/Excel/Component/Add Category.xlsx")
    @Test(dataProvider = "data-provider")
    public void TC_MVS_AC_003__009(String title, String description, String type, String message) {

        getToAddCategoryPage();

        addCategoryPage.setCategoryTitle(title);
        addCategoryPage.setCategoryDescription(description);
        addCategoryPage.clickAddCategoryButtonOnCategoryPage();

        if(type.equals("Positive")) {

            Assert.assertFalse(addCategoryPage.isElementDisplayed(addCategoryPage.getCategoryTitleErrorLocator()), message);

        } else {

            Assert.assertTrue(addCategoryPage.isElementDisplayed(addCategoryPage.getCategoryTitleErrorLocator()), message);

        }

        logOut();

    }

    @ExcelDataSourceInfo(sheetName = "Description", filepath = "src/test/java/TestData/Excel/Component/Add Category.xlsx")
    @Test(dataProvider = "data-provider")
    public void TC_MVS_AC_010__013(String title, String description, String type, String message) {

        getToAddCategoryPage();

        addCategoryPage.setCategoryTitle(title);
        addCategoryPage.setCategoryDescription(description);
        addCategoryPage.clickAddCategoryButtonOnCategoryPage();

        if(type.equals("Positive")) {

            Assert.assertFalse(addCategoryPage.isElementDisplayed(addCategoryPage.getCategoryDescriptionErrorLocator()), message);

        } else {

            Assert.assertTrue(addCategoryPage.isElementDisplayed(addCategoryPage.getCategoryDescriptionErrorLocator()), message);

        }

        logOut();

    }

    @DataProvider(name = "data-provider")
    public Object[][] DataProvider(Method method) throws IOException {

        ExcelDataSourceInfo info = method.getAnnotation(ExcelDataSourceInfo.class);

        ExcelReader excelReader = new ExcelReader(info.filepath(), info.sheetName());

        return excelReader.getData();

    }

    public void getToAddCategoryPage() {

        LogInPage logInPage = basePage.clickLogInButton();

        logInPage.setEmail("demo.admin@demo.com");
        logInPage.setPassword("123456");
        logInPage.setUserRole("Admin");

        AdminDashboardPage adminDashboardPage = logInPage.clickLogInButtonOnLogInPage(AdminDashboardPage.class);

        addCategoryPage = adminDashboardPage.clickAddCategoryButton();

    }

    public void logOut() {

        addCategoryPage.clickLogOutButton();

    }

}
