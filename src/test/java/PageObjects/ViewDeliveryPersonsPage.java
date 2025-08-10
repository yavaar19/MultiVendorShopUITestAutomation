package PageObjects;

import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

@Getter
public class ViewDeliveryPersonsPage extends SellerHeader{

    @FindBy(css = "tbody tr")
    private List<WebElement> deliveryPersons;

    private final By tableData = By.cssSelector("td");

    public ViewDeliveryPersonsPage(WebDriver driver) {

        super(driver);
        PageFactory.initElements(driver, this);

    }

    public boolean deliveryPersonExists(String email) {

        return deliveryPersons.stream().anyMatch(p -> p.findElement(By.xpath(".//td[3]/b")).getText().equals(email));

    }

}
