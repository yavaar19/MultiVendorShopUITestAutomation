package Utilities;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;

public class Screenshot {

    private int i= 0;

    public String getScreenshotPath(String testCase, WebDriver driver) throws IOException {

        i++;

        TakesScreenshot ts = (TakesScreenshot) driver;
        File src = ts.getScreenshotAs(OutputType.FILE);
        File destination = new File("src/test/java/Screenshots/" + testCase + "_"  + i + ".png");

        FileUtils.copyFile(src, destination);

        return destination.getAbsolutePath();

    }

}
