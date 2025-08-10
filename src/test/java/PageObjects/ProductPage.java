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
public class ProductPage extends CustomerHeader {

    @FindBy(id = "addToCart")
    private WebElement quantityField;

    @FindBy(css = "input[value='Add to Cart']")
    private WebElement addToCart;

    @FindBy(xpath = "//b[contains(text(), 'Stock')]")
    private WebElement productQuantity;

    @FindBy(css = "input[value='Add Review']")
    private WebElement addReview ;

    @FindBy(xpath = "//input[@id='addToCart']/following-sibling::div")
    private WebElement quantityError;

    @FindBy(xpath = "//div[@role='alert']/div/following-sibling::div")
    private WebElement productQuantityErrorToast;

    @FindBy(xpath = "(//img[@alt='...'])[1]")
    private WebElement productImage;

    @FindBy(xpath = "//div[@class='list-group-item list-group-item-action text-color custom-bg']")
    private List<WebElement> productReviews;


    private final By productReview = By.xpath("//div[@class='list-group-item list-group-item-action text-color custom-bg']");

    public ProductPage(WebDriver driver) {

        super(driver);
        PageFactory.initElements(driver, this);

    }

    public void setQuantityField(String quantity) {

        quantityField.sendKeys(quantity);

    }

    public MyCartPage clickAddToCart() {

        addToCart.click();

        return new MyCartPage(driver);

    }

    public AddReviewPage clickAddReview() {

        waitForElementToAppear(productImage);
        addReview.click();

        return new AddReviewPage(driver);

    }

    public String getToastAlertMessage() {

        return productQuantityErrorToast.getText();

    }

    public String getQuantityErrorMessage() {

        return quantityError.getText();

    }

    public boolean reviewExist(String star, String review) {

        Optional<WebElement> element = productReviews.stream().filter(p -> p.findElement(By.xpath(".//p")).getText().equals(review))
                .findAny();

        return element.map(webElement -> webElement.findElement(By.xpath(".//b[2]")).getText().equals(star)).orElse(false);

    }

    public String productQuantity() {

        waitForElementToAppear(productQuantity);
        return productQuantity.getText().replaceAll("[^0-9]", "");

    }

}
