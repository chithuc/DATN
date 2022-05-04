package core.drivers;

import core.exceptions.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

public class DriverManager {
    private static WebDriver driver;

    public static void initDriver(String browser) throws WrongDriverException {
        switch (browser.toLowerCase()) {
            case "chrome":
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
                break;
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
                break;
            case "ie":
                WebDriverManager.iedriver().setup();
                driver = new InternetExplorerDriver();
            default:
                throw new WrongDriverException("Browser name is not correct");
        }
    }

    public static WebDriver getDriver() throws WrongDriverException {
        if (driver != null)
            return driver;
        throw new WrongDriverException("Please initialize driver first!");
    }

}
