package TestCases;

import PageObjects.BasePage;
import Utilities.ReadConfig;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.annotations.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import static Utilities.Database.*;

public class Base {

    protected WebDriver driver;
    private String baseUrl;
    private String browser;
    private ReadConfig readConfig;
    protected BasePage basePage;

    public WebDriver initializeDriver() {

        if (browser.contains("chrome")) {

            ChromeOptions options = new ChromeOptions();
            Map<String, Object> prefs = new HashMap<>();
            prefs.put("credentials_enable_service", false);
            prefs.put("profile.password_manager_enabled", false);
            prefs.put("profile.password_manager_leak_detection", false);
            options.setExperimentalOption("prefs", prefs);

            if (browser.contains("headless")) options.addArguments("headless");

            driver = new ChromeDriver(options);
            driver.manage().window().setSize(new Dimension(1440, 900));

        } else if (browser.contains("firefox")) {

            FirefoxOptions options = new FirefoxOptions();

            if (browser.contains("headless")) options.addArguments("headless");

            driver = new FirefoxDriver(options);

        } else if (browser.contains("safari")) {

            driver = new SafariDriver();

        }

        driver.manage().window().maximize();

        return driver;

    }

    public void setConfiguration() throws IOException {

        readConfig = new ReadConfig();
        baseUrl = System.getProperty("BASEURL") != null ? System.getProperty("BASE_URL").toLowerCase() : readConfig.getBaseUrl();
        browser = System.getProperty("BROWSER") != null ? System.getProperty("BROWSER").toLowerCase() : readConfig.getBrowser();

    }

    @BeforeMethod(alwaysRun = true)
    public BasePage launchApplication() throws IOException {

        setConfiguration();
        driver = initializeDriver();
        basePage = new BasePage(driver);
        basePage.goTo(baseUrl);

        return basePage;

    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {

        driver.quit();

    }

    @AfterSuite
    public void cleanDatabase() throws SQLException {

        cleanTables();

    }

}
