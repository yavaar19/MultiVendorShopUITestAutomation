package PageObjects;

import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

@Getter
public class RegistrationPage extends BasePage {

    @FindBy(id = "firstName")
    private WebElement firstNameField;

    @FindBy(name = "lastName")
    private WebElement lastNameField;

    @FindBy(xpath = "//input[@name=\"emailId\"]")
    private WebElement emailField;

    @FindBy(id = "password")
    private WebElement passwordField;

    @FindBy(xpath = "//div[5]/input")
    private WebElement contactNoField;

    @FindBy(css = "textarea")
    private WebElement streetField;

    @FindBy(id = "city")
    private WebElement cityField;

    @FindBy(name = "pincode")
    private WebElement pinCodeField;

    @FindBy(css = "input[value=\"Register User\"]")
    private WebElement registerUserButton;

    @FindBy(xpath = "//input[@name=\"firstName\"]/following-sibling::div")
    private WebElement firstNameFieldError;

    @FindBy(xpath = "//input[@name=\"lastName\"]/following-sibling::div")
    private WebElement lastNameFieldError;

    @FindBy(xpath = "//input[@name=\"emailId\"]/following-sibling::div")
    private WebElement emailIdFieldError;

    @FindBy(xpath = "//input[@name=\"password\"]/following-sibling::div")
    private WebElement passwordFieldError;

    @FindBy(xpath = "//input[@name=\"phoneNo\"]/following-sibling::div")
    private WebElement phoneNoFieldError;

    @FindBy(xpath = "//textarea[@name=\"street\"]/following-sibling::div")
    private WebElement streetFieldError;

    @FindBy(xpath = "//input[@name=\"city\"]/following-sibling::div")
    private WebElement cityFieldError;

    @FindBy(xpath = "//input[@name=\"pincode\"]/following-sibling::div")
    private WebElement pinCodeFieldError;

    @FindBy(xpath = "//div[@role='alert']/div/following-sibling::div")
    private WebElement toastMessage;

    private final By firstNameErrorLocator = By.xpath( "//input[@name=\"firstName\"]/following-sibling::div[string-length(text()) > 0]");
    private final By lastNameErrorLocator = By.xpath( "//input[@name=\"lastName\"]/following-sibling::div[string-length(text()) > 0]");
    private final By emailIdErrorLocator = By.xpath( "//input[@name=\"emailId\"]/following-sibling::div[string-length(text()) > 0]");
    private final By passwordErrorLocator = By.xpath( "//input[@name=\"password\"]/following-sibling::div[string-length(text()) > 0]");
    private final By phoneNoErrorLocator = By.xpath( "//input[@name=\"phoneNo\"]/following-sibling::div[string-length(text()) > 0]");
    private final By streetErrorLocator = By.xpath( "//input[@name=\"street\"]/following-sibling::div[string-length(text()) > 0]");
    private final By cityErrorLocator = By.xpath( "//input[@name=\"city\"]/following-sibling::div[string-length(text()) > 0]");
    private final By pinCodeErrorLocator = By.xpath( "//input[@name=\"pincode\"]/following-sibling::div[string-length(text()) > 0]");

    private final By firstNameFieldLocator = By.id( "firstName" );
    private final By lastNameFieldLocator = By.name( "lastName" );
    private final By emailIdFieldLocator = By.xpath( "//input[@name=\"emailId\"]");
    private final By passwordFieldLocator = By.id( "password");
    private final By phoneNoFieldLocator = By.xpath( "//div[5]/input");
    private final By streetFieldLocator = By.cssSelector( "textarea");
    private final By cityFieldLocator = By.id( "city");
    private final By pinCodeFieldLocator = By.name( "pincode");

    private final By registerUserButtonLocator = By.cssSelector( "input[value=\"Register User\"]");

    private final By registrationToast = By.xpath("//div[@role='alert']");

    public RegistrationPage(WebDriver driver) {

        super(driver);
        PageFactory.initElements(driver, this);

    }

    public void setFirstName(String firstName) {

        firstNameField.sendKeys(firstName);

    }

    public void setLastName(String lastName) {

        lastNameField.sendKeys(lastName);

    }

    public void setEmail(String email) {

        emailField.sendKeys(email);

    }

    public void setPassword(String password) {

        passwordField.sendKeys(password);

    }

    public void setContactNo(String contactNo) {

        contactNoField.sendKeys(contactNo);

    }

    public void setStreetField(String street) {

        streetField.sendKeys(street);

    }

    public void setCityField(String city) {

        cityField.sendKeys(city);

    }

    public void setPinCodeField(String pinCode) {

        pinCodeField.sendKeys(pinCode);

    }

    public LogInPage registerUser() {

        registerUserButton.click();
        return new LogInPage(driver);

    }

    public void setAllFields(String firstName, String lastName, String email, String password, String contactNo, String street, String city, String pinCode) {

        firstNameField.sendKeys(firstName);

        lastNameField.sendKeys(lastName);

        emailField.sendKeys(email);

        passwordField.sendKeys(password);

        contactNoField.sendKeys(contactNo);

        streetField.sendKeys(street);

        cityField.sendKeys(city);

        pinCodeField.sendKeys(pinCode);

    }

    public String getToastMessage() {

        waitForElementToAppear(registrationToast);

        return toastMessage.getText();

    }

}
