package PageObjects;

import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.io.File;

@Getter
public class UpdateProductPage extends BasePage {

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

    @FindBy(name = "image1")
    private WebElement firstImage;

    @FindBy(name = "image2")
    private WebElement secondImage;

    @FindBy(name = "image3")
    private WebElement thirdImage;

    @FindBy(xpath = "//button[text()='Update Product']")
    private WebElement updateProductButton;

    @FindBy(xpath = "//button[text()='Update Image']")
    private WebElement updateProductImageButton;

    @FindBy(xpath = "//div[@role='alert']/div/following-sibling::div")
    private WebElement toastMessage;

    private final By categorySelectionFirstOption  = By.xpath("//option[2]");

    private final By updateProductToast = By.xpath("//div[@role='alert']");

    public UpdateProductPage(WebDriver driver) {

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

    public String  getFirstImagePath(){

        return firstImage.getText();

    }

    public String  getSecondImagePath(File imageFile){

        return secondImage.getText();

    }

    public String getThirdImagePath(File imageFile){

        return thirdImage.getText();

    }

    public ViewMyProductsPage clickUpdateProduct() {

        updateProductButton.click();
        return new ViewMyProductsPage(driver);

    }

    public ViewMyProductsPage clickUpdateProductImageButton() {

        updateProductImageButton.click();
        return new ViewMyProductsPage(driver);

    }

    public String getToastMessage() {

        waitForElementToAppear(updateProductToast);

        return toastMessage.getText();

    }

}
