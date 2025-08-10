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
public class AllCategoriesPage extends AdminHeader {

    @FindBy(css = "tbody tr")
    private List<WebElement> categoryList;

    private final By allCategoriesToast = By.xpath("//div[@role='alert']");

    private final By tableData = By.cssSelector("td");

    public AllCategoriesPage(WebDriver driver) {

        super(driver);
        PageFactory.initElements(driver, this);

    }

    public boolean categoryExist(String categoryName) {

        return categoryList.stream().anyMatch(c -> c.findElement(By.xpath(".//td[2]/b")).getText().equals(categoryName));

    }

    public AddCategoryPage updateCategory(String categoryName) {

        Optional<WebElement> element = categoryList.stream().filter(c -> c.findElement(By.xpath(".//td[2]/b")).getText().equals(categoryName))
                .findAny();

        element.ifPresent(webElement -> webElement.findElement(By.xpath(".//td[4]/button[text()='Update']")).click());

        return new AddCategoryPage(driver);

    }

    public AllCategoriesPage deleteCategory(String categoryName) {

        Optional<WebElement> element = categoryList.stream().filter(c -> c.findElement(By.xpath(".//td[2]/b")).getText().equals(categoryName))
                .findAny();

        element.ifPresent(webElement -> webElement.findElement(By.xpath(".//td[4]/button[text()='Delete']")).click());

        return new AllCategoriesPage(driver);

    }

}
