package PageObjects;

import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

@Getter
public class AdminDashboardPage extends AdminHeader {

    private final By searchButton = By.xpath("//button[text()='Search']");

    public AdminDashboardPage(WebDriver driver) {

        super(driver);
        PageFactory.initElements(driver, this);

    }

}
