package PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CustomerHeader extends BasePage {

    @FindBy(css = "a[href=\"/customer/cart\"]")
    private WebElement myCartButton;

    @FindBy(xpath = "//b[text()='My Order']")
    private WebElement myOrdersButton;

    @FindBy(xpath = "//b[text()=\"Logout\"]")
    private WebElement logOutButton;

    public CustomerHeader(WebDriver driver) {

        super(driver);
        PageFactory.initElements(driver, this);

    }

    public MyCartPage clickMyCartButton() {

        waitForElementToAppear(myCartButton);
        myCartButton.click();
        return new MyCartPage(driver);

    }

    public MyOrdersPage clickMyOrdersButton() {

        waitForElementToAppear(myOrdersButton);
        waitForElementToBecomeClickable(myOrdersButton);
        myOrdersButton.click();
        return new MyOrdersPage(driver);

    }

    public BasePage clickLogOutButton() {

        logOutButton.click();
        return new BasePage(driver);

    }


}
