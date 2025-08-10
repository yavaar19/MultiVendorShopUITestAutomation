package PageObjects;

import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

@Getter
public class AddCategoryPage extends AdminHeader {

    @FindBy(id = "title")
    private WebElement categoryTitleField;

    @FindBy(css = "textarea[placeholder='enter description..']")
    private WebElement categoryDescriptionField;

    @FindBy(xpath = "//button[@type='submit']")
    private WebElement addCategoryButton;

    @FindBy(xpath = "//div[@role='alert']/div/following-sibling::div")
    private WebElement toastMessage;

    private final By categoryTitleFieldLocator = By.id("title");
    private final By categoryDescriptionFieldLocator = By.cssSelector("textarea[placeholder='enter description..']");
    private final By addCategoryButtonLocator = By.xpath("//button[@type='submit']");

    private final By categoryTitleErrorLocator = By.xpath("//input[@id='title']//following-sibling::div[string-length(text()) > 0]");
    private final By categoryDescriptionErrorLocator = By.xpath("//textarea[@id='description']//following-sibling::div[string-length(text()) > 0]");

    private final By addCategoryToast = By.xpath("//div[@role='alert']");


    public AddCategoryPage(WebDriver driver) {

        super(driver);
        PageFactory.initElements(driver, this);

    }

    public void setCategoryTitle(String categoryTitle) {

        categoryTitleField.sendKeys(categoryTitle);

    }

    public void setCategoryDescription(String categoryDescription) {

        categoryDescriptionField.sendKeys(categoryDescription);

    }

    public void clearCategoryTitle() {

        categoryTitleField.clear();

    }

    public void clearCategoryDescription() {

        categoryDescriptionField.clear();

    }

    public AdminDashboardPage clickAddCategoryButtonOnCategoryPage() {

        addCategoryButton.click();
        return new AdminDashboardPage(driver);

    }

    public String getToastMessage() {

        waitForElementToAppear(addCategoryToast);

        return toastMessage.getText();

    }

}
