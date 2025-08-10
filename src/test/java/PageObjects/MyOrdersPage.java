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
public class MyOrdersPage extends CustomerHeader {

    @FindBy(css = "tbody tr")
    private List<WebElement> orders;

    private final By tableData = By.cssSelector("td");

    public MyOrdersPage(WebDriver driver) {

        super(driver);
        PageFactory.initElements(driver, this);

    }

    public boolean orderExist(String productName, String productQuantity) {

        Optional<WebElement> element = orders.stream().filter(p -> p.findElement(By.xpath(".//td[3]/b")).getText().equals(productName))
                .findAny();

        return element.map(webElement -> webElement.findElement(By.xpath(".//td[7]/b")).getText().equals(productQuantity)).orElse(false);

    }

    public String getOrderStatus(String productName) {

        Optional<WebElement> element = orders.stream().filter(p -> p.findElement(By.xpath(".//td[3]/b")).getText().equals(productName))
                .findAny();

        return element.map(webElement -> webElement.findElement(By.xpath(".//td[9]/b")).getText()).orElse("");

    }

    public String getOrderDeliveryPerson(String productName) {

        Optional<WebElement> element = orders.stream().filter(p -> p.findElement(By.xpath(".//td[3]/b")).getText().equals(productName))
                .findAny();

        return element.map(webElement -> webElement.findElement(By.xpath(".//td[10]/b")).getText()).orElse("");

    }

}
