package PageObjects;

import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

@Getter
public class AddReviewPage extends BasePage {

    @FindBy(name = "locationId")
    private WebElement starField;

    @FindBy(id = "review")
    private WebElement productReviewField;

    @FindBy(css = "input[value='Add Review']")
    private WebElement addReviewButton;

    @FindBy(xpath = "//div[@role='alert']/div/following-sibling::div")
    private WebElement toastMessage;

    private final By addReviewToast = By.xpath("//div[@role='alert']");

    public AddReviewPage(WebDriver driver) {

        super(driver);
        PageFactory.initElements(driver, this);

    }

    public void setStar(String star) {

        selection = new Select(starField);

        selection.selectByValue(star);

    }

    public void setProductReview(String productReview) {

        productReviewField.sendKeys(productReview);

    }

    public ProductPage clickAddReviewButton() {

        addReviewButton.click();

        return new ProductPage(driver);

    }

    public String getToastMessage() {

        waitForElementToAppear(addReviewToast);

        return toastMessage.getText();

    }

}
