package PageObjects;

import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

@Getter
public class CustomerDashboardPage extends CustomerHeader {

    @FindBy(xpath = "//div[@class='col']")
    private List<WebElement> products;

    private final By productFindBy = By.xpath(".//div[@class='col']");
    private final By productTitleFindBy = By.xpath(".//div/b");
    private final By productAddToCartFindBy = By.xpath(".//div/div/a");
    private final By searchButton = By.xpath("//button[text()='Search']");

    public CustomerDashboardPage(WebDriver driver) {

        super(driver);
        PageFactory.initElements(driver, this);

    }

    public ProductPage viewProduct(String productName) {

        waitForElementToAppear(productFindBy);
        waitForElementToAppear(productAddToCartFindBy);

        WebElement addToCart = products.stream().filter(p -> p.findElement(productTitleFindBy).getText().equals(productName)).findFirst().get()
                .findElement(productAddToCartFindBy);

        scrollDown();

        addToCart.click();

        return new ProductPage(driver);

    }

}
