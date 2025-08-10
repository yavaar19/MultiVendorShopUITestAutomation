package PageObjects;

import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

@Getter
public class LogInPage extends BasePage {

    @FindBy(name = "role")
    private WebElement userRoleField;

    @FindBy(xpath = "//input[@type=\"email\"]")
    private WebElement emailField;

    @FindBy(id = "password")
    private WebElement passwordField;

    @FindBy(css = "button[type=\"Submit\"]")
    private WebElement logInButton;

    @FindBy(xpath = "//div[@role='alert']/div/following-sibling::div")
    private WebElement toastAlertMessage;

    private final By emailIdFieldErrorLocator = By.xpath("//input[@name=\"emailId\"]/following-sibling::div[string-length(text()) > 0]");
    private final By passwordFieldErrorLocator = By.xpath("//input[@name=\"password\"]/following-sibling::div[string-length(text()) > 0]");

    private final By logInSuccessToast = By.xpath("//div[text()='Logged in sucessful']");
    private final By logInErrorToast = By.xpath("//div[text()='Invalid email or password.']");
    private final By logInToast = By.xpath("//div[@role='alert']");

    public LogInPage(WebDriver driver) {

        super(driver);
        PageFactory.initElements(driver, this);

    }

    public void setUserRole(String role) {

        selection = new Select(userRoleField);

        selection.selectByValue(role);

    }

    public void setEmail(String email) {

        emailField.sendKeys(email);

    }

    public void setPassword(String password) {

        passwordField.sendKeys(password);

    }

    public String getToastMessage() {

        waitForElementToAppear(logInToast);

        return toastAlertMessage.getText();

    }

    public <T extends BasePage> T clickLogInButtonOnLogInPage(Class<T> className) {

        logInButton.click();

        try {

            return className.getDeclaredConstructor(WebDriver.class).newInstance(this.driver);

        } catch (Exception e) {

            throw new RuntimeException(e);

        }

    }

}
