package PageObjects;

import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

@Getter
public class AdminRegistrationPage extends AdminHeader {

    @FindBy(name = "emailId")
    private WebElement emailIdField;

    @FindBy(id = "password")
    private WebElement passwordField;

    @FindBy(css = "button[type='submit']")
    private WebElement registerButton;

    @FindBy(xpath = "//div[@role='alert']/div/following-sibling::div")
    private WebElement toastMessage;

    private final By emailIdFieldLocator = By.name("emailId");
    private final By passwordFieldLocator = By.id("password");
    private final By registerButtonFieldLocator = By.cssSelector("button[type='submit']");

    private final By emailIdFieldErrorLocator = By.xpath("//input[@name=\"emailId\"]/following-sibling::div[string-length(text()) > 0]");
    private final By passwordFieldErrorLocator = By.xpath("//input[@name=\"password\"]/following-sibling::div[string-length(text()) > 0]");

    private final By registrationToast = By.xpath("//div[@role='alert']");

    public AdminRegistrationPage(WebDriver driver) {

        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void setEmailId(String emailId) {

        emailIdField.sendKeys(emailId);

    }

    public void setPassword(String password) {

        passwordField.sendKeys(password);

    }

    public AdminDashboardPage clickRegisterButton() {

        registerButton.click();

        return new AdminDashboardPage(driver);

    }

    public String getToastMessage() {

        waitForElementToAppear(registrationToast);

        return toastMessage.getText();

    }

}
