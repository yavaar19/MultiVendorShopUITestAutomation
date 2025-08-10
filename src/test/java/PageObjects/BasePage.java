package PageObjects;

import lombok.Getter;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class BasePage {

    protected WebDriver driver;
    protected WebDriverWait wait;

    @FindBy(css = "a[href=\"/user/seller/register\"]")
    private WebElement registerSellerButton;

    @FindBy(xpath = "//li/a[@href=\"/user/customer/register\"]")
    private WebElement registerCustomerButton;

    @FindBy(xpath = "//a/b[text()=\"Login User\"]")
    private WebElement logInButton;

    @FindBy(xpath = "//h5[normalize-space()='E-Commerce Multi-vendor Shop']")
    private WebElement footerTitle;

    private final Actions action;

    protected Select selection;

    private JavascriptExecutor js;

    public BasePage(WebDriver driver) {

        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        action = new Actions(driver);
        js =  (JavascriptExecutor) driver;
        PageFactory.initElements(driver, this);

    }

    public void goTo(String baseUrl) {

        driver.get(baseUrl);

    }

    public RegistrationPage clickRegisterSellerButton() {

        waitForElementToAppear(registerSellerButton);
        registerSellerButton.click();
        return new RegistrationPage(driver);

    }

    public RegistrationPage clickRegisterCustomerButton() {

        registerCustomerButton.click();
        return new RegistrationPage(driver);

    }

    public LogInPage clickLogInButton() {

        waitForElementToAppear(logInButton);
        logInButton.click();
        return new LogInPage(driver);

    }

    public void waitForElementToAppear(WebElement element) {

        wait.until(ExpectedConditions.visibilityOf(element));

    }

    public void waitForElementToAppear(By findBy) {

        wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));

    }

    public void waitForElementToDisappear(By findBy) {

        wait.until(ExpectedConditions.invisibilityOfElementLocated(findBy));

    }

    public void waitForPresenceOfNestedElementsToAppear(By findBy, By optionsFindBy) {

        wait.until(ExpectedConditions.presenceOfNestedElementsLocatedBy(findBy, optionsFindBy));

    }

    public void waitForElementToBecomeClickable(WebElement element) {

        wait.until(ExpectedConditions.elementToBeClickable(element));

    }

    public boolean isElementDisplayed(By findBy) {

        List<WebElement> elements = driver.findElements(findBy);

        return !elements.isEmpty();

    }

    public void waitForElementToBecomeVisible(WebElement element) {

        wait.until(ExpectedConditions.visibilityOf(element));

    }

    public void waitForElementsToBecomeVisible(List<WebElement> elements) {

        wait.until(ExpectedConditions.visibilityOfAllElements(elements));

    }

    public void scrollDown() {

        action.scrollByAmount(0, 1042).perform();

    }

    public void refreshPage() {

        js.executeScript("setTimeout(function(){location.reload()}, 5000);");

    }

}
