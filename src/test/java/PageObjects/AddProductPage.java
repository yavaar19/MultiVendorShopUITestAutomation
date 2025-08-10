package PageObjects;

import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.io.File;

@Getter
public class AddProductPage extends SellerHeader {

    @FindBy(id = "title")
    private WebElement productTitleField;

    @FindBy(id = "description")
    private WebElement productDescriptionField;

    @FindBy(css = "select")
    private WebElement categorySelectionField;

    @FindBy(id = "quantity")
    private WebElement quantityField;

    @FindBy(id = "price")
    private WebElement priceField;

    @FindBy(xpath = "//button[text()='Add Product']")
    private WebElement addProductButton;

    @FindBy(name = "image1")
    private WebElement firstImage;

    @FindBy(name = "image2")
    private WebElement secondImage;

    @FindBy(name = "image3")
    private WebElement thirdImage;

    @FindBy(xpath = "//div[@role='alert']/div/following-sibling::div")
    private WebElement toastMessage;

    private final By productTitleFieldLocator = By.id("title");
    private final By productDescriptionFieldLocator  = By.id("description");
    private final By categorySelectionFieldLocator  = By.cssSelector("select");
    private final By categorySelectionFirstOption  = By.xpath("//option[2]");
    private final By quantityFieldLocator  = By.id("quantity");
    private final By priceFieldLocator  = By.id("price");
    private final By addProductButtonLocator  = By.xpath("//button[text()='Add Product']");
    private final By firstImageFieldLocator  = By.name("image1");
    private final By secondImageFieldLocator  = By.name("image2");
    private final By thirdImageFieldLocator  = By.name("image3");

    private final By productTitleFieldErrorLocator = By.xpath("//input[@id='title']/following-sibling::div[string-length(text()) > 0]");
    private final By productDescriptionFieldErrorLocator  = By.xpath("//textarea[@id='description']/following-sibling::div[string-length(text()) > 0]");
    private final By categorySelectionFieldErrorLocator  = By.xpath("//select[@name='categoryId']/following-sibling::div[string-length(text()) > 0]");
    private final By quantityFieldErrorLocator  = By.xpath("//input[@id='quantity']/following-sibling::div[string-length(text()) > 0]");
    private final By priceFieldErrorLocator  = By.xpath("//input[@id='price']/following-sibling::div[string-length(text()) > 0]");
    private final By firstImageFieldErrorLocator  = By.xpath("//input[@name='image1']/following-sibling::div[string-length(text()) > 0]");
    private final By secondImageFieldErrorLocator  = By.xpath("//input[@name='image2']/following-sibling::div[string-length(text()) > 0]");
    private final By thirdImageFieldErrorLocator  = By.xpath("//input[@name='image3']/following-sibling::div[string-length(text()) > 0]");

    private final By addProductToast = By.xpath("//div[@role='alert']");


    public AddProductPage(WebDriver driver) {

        super(driver);
        PageFactory.initElements(driver, this);

    }

    public void setProductTitleField(String productTitle) {

        productTitleField.sendKeys(productTitle);

    }

    public void clearProductTitleField() {

        productTitleField.clear();

    }

    public void setProductDescriptionField(String productDescription) {

        productDescriptionField.sendKeys(productDescription);

    }

    public void clearProductDescriptionField() {

        productDescriptionField.clear();

    }

    public void setCategorySelectionField(String category) {

        selection = new Select(categorySelectionField);
        waitForElementToAppear(categorySelectionFirstOption);
        selection.selectByVisibleText(category);

    }

    public void setQuantityField(String quantity) {

        quantityField.sendKeys(quantity);

    }

    public void clearQuantityField() {

        quantityField.clear();

    }

    public void setQuantityField(Keys key) {

        quantityField.sendKeys(key);

    }

    public void setPriceField(String price) {

        priceField.sendKeys(price);

    }

    public void clearPriceField() {

        priceField.clear();

    }

    public void setPriceField(Keys key) {

        priceField.sendKeys(key);

    }

    public void setFirstImage(File imageFile){

        firstImage.sendKeys(imageFile.getAbsolutePath());

    }

    public void setSecondImage(File imageFile){

        secondImage.sendKeys(imageFile.getAbsolutePath());

    }

    public void setThirdImage(File imageFile){

        thirdImage.sendKeys(imageFile.getAbsolutePath());

    }

    public void setAllFields(String productTitle, String productDescription, String category, String quantity, String price, File fImage, File sImage, File tImage) {

        waitForElementToAppear(categorySelectionFirstOption);

        selection = new Select(categorySelectionField);

        productTitleField.sendKeys(productTitle);
        productDescriptionField.sendKeys(productDescription);
        selection.selectByVisibleText(category);
        quantityField.sendKeys(quantity);
        priceField.sendKeys(price);
        firstImage.sendKeys(fImage.getAbsolutePath());
        secondImage.sendKeys(sImage.getAbsolutePath());
        thirdImage.sendKeys(tImage.getAbsolutePath());

    }

    public SellerDashboardPage clickAddProduct() {

        addProductButton.click();
        return new SellerDashboardPage(driver);

    }

    public String getToastMessage() {

        waitForElementToAppear(addProductToast);

        return toastMessage.getText();

    }

}
