package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static PageObjects.NeverStaleWebElement.click;

public class DeliveryHeader extends BasePage {

    @FindBy(xpath = "//b[text()=\"My Delivery Orders\"]")
    private WebElement myDeliveryOrders;

    @FindBy(xpath = "//b[text()='Logout']")
    private WebElement logOutButton;

    private final By logOutButtonLocator =  By.xpath("//b[text()='Logout']");

    public DeliveryHeader(WebDriver driver) {

        super(driver);
        PageFactory.initElements(driver, this);

    }

    public MyDeliveryOrdersPage clickMyDeliveryOrders() {

        waitForElementToAppear(myDeliveryOrders);
        myDeliveryOrders.click();
        return new MyDeliveryOrdersPage(driver);

    }

    public BasePage clickLogOutButton() {

        click(driver, logOutButton, logOutButtonLocator);
        return new BasePage(driver);

    }

}
