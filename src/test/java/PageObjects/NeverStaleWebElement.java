package PageObjects;


import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class NeverStaleWebElement {


    public static void click(WebDriver driver, WebElement element, By findBy) {

        int count = 0;

        while (count < 4) {

            try {

                element.click();
                break;

            } catch (StaleElementReferenceException exception) {

                element = driver.findElement(findBy);

            }

            count++;

        }

    }

}
