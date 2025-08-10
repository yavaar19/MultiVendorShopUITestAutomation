package PageObjects;

import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

@Getter
public class CheckOutPage extends CustomerHeader{

    @FindBy(id = "name")
    private WebElement nameField;

    @FindBy(name = "cardNumber")
    private WebElement cardNumberField;

    @FindBy(id = "validThrough")
    private WebElement validThroughField;

    @FindBy(name = "cvv")
    private WebElement cvvField;

    @FindBy(css = "input[type='submit']")
    private WebElement payButton;

    @FindBy(xpath = "//div[@role='alert']/div/following-sibling::div")
    private WebElement checkOutSuccessToast;

    private final By nameFieldLocator = By.id("name");
    private final By cardNumberFieldLocator  = By.name("cardNumber");
    private final By validThroughFieldLocator  = By.id("validThrough");
    private final By cvvFieldLocator  = By.name("cvv");
    private final By payButtonLocator  = By.cssSelector("input[type='submit']");

    private final By nameFieldErrorLocator = By.xpath("//input[@id='name']/following-sibling::div[string-length(text()) > 0]");
    private final By cardNumberFieldErrorLocator  = By.xpath("//input[@name='cardNumber']/following-sibling::div[string-length(text()) > 0]");
    private final By validThroughFieldErrorLocator  = By.xpath("//input[@id='validThrough']/following-sibling::div[string-length(text()) > 0]");
    private final By cvvFieldErrorLocator  = By.xpath("//input[@id='cvv']/following-sibling::div[string-length(text()) > 0]");

    private final By checkOutToast = By.xpath("//div[@role='alert']");

    public CheckOutPage(WebDriver driver) {

        super(driver);
        PageFactory.initElements(driver, this);

    }

    public void setNameField(String name) {

        nameField.sendKeys(name);

    }

    public void setCardNumberField(String cardNumber) {

        cardNumberField.sendKeys(cardNumber);

    }

    public void setValidThroughField(String validThrough) {

        validThroughField.sendKeys(validThrough);

    }

    public void setCvvField(String cvv) {

        cvvField.sendKeys(cvv);

    }

    public void setAllFields(String name, String cardNumber, String validThrough, String CVV) {

        nameField.sendKeys(name);
        cardNumberField.sendKeys(cardNumber);
        validThroughField.sendKeys(validThrough);
        cvvField.sendKeys(CVV);

    }

    public CustomerDashboardPage clickPayButton() {

        payButton.click();
        return new CustomerDashboardPage(driver);

    }

    public String getCheckOutSuccessToastMessage() {

        return checkOutSuccessToast.getText();

    }

}
