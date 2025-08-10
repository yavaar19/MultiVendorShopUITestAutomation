package PageObjects;

import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.util.List;
import java.util.Optional;

@Getter
public class SellerOrdersPage extends SellerHeader {

    @FindBy(id = "inputPassword2")
    private WebElement orderIdField;

    @FindBy(css = "button[type='submit']")
    private WebElement searchButton;

    @FindBy(css = "tbody tr")
    private List<WebElement> orderItems;

    @FindBy(css = "div[class='modal-content']")
    private WebElement modalBox;

    @FindBy(name = "deliveryPersonId")
    private WebElement deliverySelection;

    @FindBy(xpath = "//option[2]")
    private WebElement deliveryOption;

    @FindBy(xpath = "//button[normalize-space()='Assign']")
    private WebElement assignButton;

    private final By deliveryPersonName = By.xpath("//td[11]/b[text()='Pending']");

    private final By tableData = By.cssSelector("td");

    private final By modalBoxFindBy = By.xpath("div[class='modal-content']");

    public SellerOrdersPage(WebDriver driver) {

        super(driver);
        PageFactory.initElements(driver, this);

    }

    public void setOrderIdField(String orderId) {

        orderIdField.sendKeys(orderId);

    }

    public void clickSearchButton() {

        searchButton.click();

    }

    public void assignDelivery(String productName) {

        waitForElementToAppear(tableData);

        Optional<WebElement> element = orderItems.stream().filter(p -> p.findElement(By.xpath(".//td[3]/b")).getText().equals(productName)).findAny();

        element.ifPresent(e -> e.findElement(By.xpath(".//td[14]/button")).click());

    }

    public String deliveryPerson(String productName) {

        Optional<WebElement> element = orderItems.stream().filter(p -> p.findElement(By.xpath(".//td[3]/b")).getText().equals(productName))
                .findAny();

        return element.map(webElement -> webElement.findElement(By.xpath(".//td[11]/b")).getText()).orElse("");

    }

    public void setUserRole(String deliveryPerson) {

        selection = new Select(deliverySelection);

        selection.selectByVisibleText(deliveryPerson);

    }

    public SellerOrdersPage assign() {

        assignButton.click();

        return new SellerOrdersPage(driver);

    }

}
