package PageObjects;

import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static PageObjects.NeverStaleWebElement.click;

@Getter
public class SellerHeader extends BasePage{

    @FindBy(xpath = "//b[text()=\"Seller Orders\"]")
    private WebElement sellerOrdersButton;

    @FindBy(css = "a[href='/seller/delivery/register'] b")
    private WebElement registerDeliveryButton;

    @FindBy(xpath = "//b[normalize-space()='View Delivery Persons']")
    private WebElement viewDeliveryPersonsButton;

    @FindBy(css = "a[href='/product/add'] b")
    private WebElement addProductButton;

    @FindBy(xpath = "//b[text()=\"View My Products\"]")
    private WebElement viewMyProductsButton;

    @FindBy(xpath = "//b[text()='Logout']")
    private WebElement logOutButton;

    private final By viewDeliveryPersonsLocator = By.xpath("//b[normalize-space()='View Delivery Persons']");
    private final By logOutButtonLocator = By.xpath("//b[text()='Logout']");

    public SellerHeader(WebDriver driver) {

        super(driver);
        PageFactory.initElements(driver, this);

    }

    public SellerOrdersPage clickSellerOrdersButton() {

        waitForElementToAppear(sellerOrdersButton);
        sellerOrdersButton.click();
        return new SellerOrdersPage(driver);

    }

    public DeliveryRegistrationPage clickRegisterDeliveryButton() {

        waitForElementToAppear(registerDeliveryButton);
        registerDeliveryButton.click();
        return new DeliveryRegistrationPage(driver);

    }

    public ViewDeliveryPersonsPage clickViewDeliveryPersonsButton() {

        click(driver, viewDeliveryPersonsButton, viewDeliveryPersonsLocator);
        return new ViewDeliveryPersonsPage(driver);

    }

    public AddProductPage clickAddProductButton() {

        waitForElementToAppear(addProductButton);
        addProductButton.click();
        return new AddProductPage(driver);

    }

    public ViewMyProductsPage clickViewMyProductsButton() {

        waitForElementToAppear(viewMyProductsButton);
        viewMyProductsButton.click();
        return new ViewMyProductsPage(driver);

    }

    public BasePage clickLogOutButton() {

        click(driver, logOutButton, logOutButtonLocator);
        return new BasePage(driver);

    }

}
