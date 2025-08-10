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
public class ViewMyProductsPage extends SellerHeader {

    @FindBy(css = "tbody tr")
    private List<WebElement> products;

    private final By tableData = By.cssSelector("td");

    public ViewMyProductsPage(WebDriver driver) {

        super(driver);
        PageFactory.initElements(driver, this);
    }

    public boolean productExist(String productName, String productDescription, String productQuantity, String productPrice) {

        return products.stream().filter(p -> p.findElement(By.xpath(".//td[2]/b")).getText().equals(productName))
                .findAny().filter(p -> p.findElement(By.xpath(".//td[3]/b")).getText().equals(productDescription))
                .filter(p -> p.findElement(By.xpath(".//td[5]/b")).getText().equals(productQuantity))
                        .filter(p -> p.findElement(By.xpath(".//td[6]/b")).getText().equals(productPrice)).isPresent();
    }

    public UpdateProductPage updateProduct(String productName) {

        Optional<WebElement> element = products.stream().filter(p -> p.findElement(By.xpath(".//td[2]/b")).getText().equals(productName))
                .findAny();

        element.ifPresent(webElement -> webElement.findElement(By.xpath(".//button[text()='Update']")).click());

        return new UpdateProductPage(driver);

    }

}
