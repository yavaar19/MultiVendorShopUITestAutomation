package PageObjects;

import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

@Getter
public class AdminHeader extends BasePage {

    @FindBy(xpath = "//b[text()=\"Register Admin\"]")
    private WebElement registerAdminButton;

    @FindBy(css = "a[href='/category/add'] b")
    private WebElement addCategoryButton;

    @FindBy(css = "li:nth-child(7) a")
    private WebElement allOrdersButton;

    @FindBy(xpath = "//b[text()=\"All Categories\"]")
    private WebElement allCategoriesButton;

    @FindBy(xpath = "//b[text()=\"Logout\"]")
    private WebElement logOutButton;

    public AdminHeader(WebDriver driver) {

       super(driver);
       PageFactory.initElements(driver, this);

    }

    public AdminRegistrationPage clickRegisterAdminButton() {

        waitForElementToAppear(registerAdminButton);
        registerAdminButton.click();
        return new AdminRegistrationPage(driver);

    }

    public AddCategoryPage clickAddCategoryButton() {

        waitForElementToAppear(addCategoryButton);
        addCategoryButton.click();
        return new AddCategoryPage(driver);

    }

    public AllCategoriesPage clickAllCategoryButton() {

        waitForElementToAppear(allCategoriesButton);
        allCategoriesButton.click();
        return new AllCategoriesPage(driver);

    }

    public BasePage clickLogOutButton() {

        logOutButton.click();
        return new BasePage(driver);

    }

}
