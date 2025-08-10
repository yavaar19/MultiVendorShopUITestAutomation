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
public class MyDeliveryOrdersPage extends DeliveryHeader {

    @FindBy(css = "tbody tr")
    private List<WebElement> orderItems;

    @FindBy(css = "div[class='modal-content']")
    private WebElement modalBox;

    @FindBy(id = "deliveryDate")
    private WebElement deliveryDate;

    @FindBy(name = "deliveryTime")
    private WebElement deliveryTimeSelection;

    @FindBy(name = "deliveryStatus")
    private WebElement deliveryStatusSelection;

    @FindBy(xpath = "//button[@type='submit'][normalize-space()='Update Status']")
    private WebElement updateStatusButton;

    private final By action = By.xpath("//td[14]/b[text()='Processing']");

    private final By tableData = By.cssSelector("td");

    public MyDeliveryOrdersPage(WebDriver driver) {

        super(driver);
        PageFactory.initElements(driver, this);

    }

    public void updateDeliveryStatus(String productName) {

        waitForElementToAppear(tableData);

        Optional<WebElement> element = orderItems.stream().filter(p -> p.findElement(By.xpath(".//td[3]/b")).getText().equals(productName))
                .findFirst();

        element.ifPresent(webElement -> webElement.findElement(By.xpath(".//td[14]/button")).click());

    }

    public void setDeliveryDate(String date) {

        deliveryDate.sendKeys(date);

    }

    public void setDeliveryTime(String deliveryTime) {

        selection = new Select(deliveryTimeSelection);

        selection.selectByVisibleText(deliveryTime);

    }

    public void setDeliveryStatus(String deliveryStatus) {

        selection = new Select(deliveryStatusSelection);

        selection.selectByVisibleText(deliveryStatus);

    }

    public MyDeliveryOrdersPage updateStatus() {

        updateStatusButton.click();
        waitForElementToAppear(tableData);

        return new MyDeliveryOrdersPage(driver);

    }

    public boolean confirmDelivery(String productName, String status, String deliveryTime,  String action) {

        waitForElementToAppear(By.xpath(".//td[14]/b"));

        return orderItems.stream().filter(p -> p.findElement(By.xpath(".//td[3]/b")).getText().equals(productName)).findFirst()
                .filter(p -> p.findElement(By.xpath(".//td[10]/b")).getText().equals(status) &&
                        p.findElement(By.xpath(".//td[13]/b")).getText().equals(deliveryTime) &&
                        p.findElement(By.xpath(".//td[14]/b")).getText().equals(action)).isPresent();

    }

}
