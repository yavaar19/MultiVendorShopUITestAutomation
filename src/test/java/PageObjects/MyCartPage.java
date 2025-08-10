package PageObjects;

import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;
import java.util.Optional;

@Getter
public class MyCartPage extends CustomerHeader {

    @FindBy(css = "tbody tr")
    private List<WebElement> cartItems;

    @FindBy(css = "button[type='Submit']")
    private WebElement checkOutButton;

    @FindBy(xpath = "//div[@role='alert']/div/following-sibling::div")
    private WebElement toastMessage;

    private final By tableData = By.cssSelector("td");
    private final By tableHeader = By.cssSelector("th");

    private final By minusButton = By.xpath(".//button[text()='-']");
    private final By plusButton = By.xpath(".//button[text()='+']");
    private final By deleteButton = By.xpath(".//button[text()='Delete']");

    private final By cartToast = By.xpath("//div[@role='alert']");

    public MyCartPage(WebDriver driver) {

        super(driver);
        PageFactory.initElements(driver, this);

    }

    public CheckOutPage clickCheckOutButton() {

        waitForElementToAppear(checkOutButton);

        scrollDown();

        checkOutButton.click();

        return new CheckOutPage(driver);

    }

    public boolean productExists(String productName) {

        return cartItems.stream().anyMatch(p -> p.findElement(By.xpath(".//td[2]/b")).getText().equals(productName));

    }

    public String productQuantity(String productName) {

        Optional<WebElement> element = cartItems.stream().filter(p -> p.findElement(By.xpath(".//td[2]/b")).getText().equals(productName))
                .findAny();

        return element.map(webElement -> webElement.findElement(By.xpath(".//td[6]/b")).getText()).orElse("");

    }

    public void increaseProductQuantity(String productName) {

        Optional<WebElement> element = cartItems.stream().filter(p -> p.findElement(By.xpath(".//td[2]/b")).getText().equals(productName))
                .findAny();

        element.ifPresent(webElement -> webElement.findElement(plusButton).click());

        waitForElementToAppear(cartToast);
        waitForElementToDisappear(cartToast);

    }

    public void decreaseProductQuantity(String productName) {

        Optional<WebElement> element = cartItems.stream().filter(p -> p.findElement(By.xpath(".//td[2]/b")).getText().equals(productName))
                .findAny();


        element.ifPresent(webElement -> webElement.findElement(minusButton).click());

        waitForElementToAppear(cartToast);
        waitForElementToDisappear(cartToast);

    }

    public void deleteProductFromCart(String productName) {

        Optional<WebElement> element = cartItems.stream().filter(p -> p.findElement(By.xpath(".//td[2]/b")).getText().equals(productName))
                .findAny();

        element.ifPresent(webElement -> webElement.findElement(deleteButton).click());

        waitForElementToAppear(cartToast);
        waitForElementToDisappear(cartToast);

    }

    public String getToastMessage() {

        waitForElementToAppear(cartToast);
        return toastMessage.getText();

    }

}
