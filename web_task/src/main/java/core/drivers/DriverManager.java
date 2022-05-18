package core.drivers;

import core.exceptions.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class DriverManager {
    private static WebDriver driver;

    public static ThreadLocal<WebDriver> tdriver = new ThreadLocal<WebDriver>();

    public static void initDriver(String browser) throws WrongDriverException {
        switch (browser.toLowerCase()) {
            case "chrome":
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
                driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20));
                driver.manage().window().maximize();
                tdriver.set(driver);
                getDriver();
                break;
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
                driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
                driver.manage().window().maximize();
                tdriver.set(driver);
                getDriver();
                break;
            case "ie":
                WebDriverManager.iedriver().setup();
                driver = new InternetExplorerDriver();
                driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
                driver.manage().window().maximize();
                tdriver.set(driver);
                getDriver();
            default:
                throw new WrongDriverException("Browser name is not correct");
        }
    }

//    public static WebDriver getDriver() throws WrongDriverException {
//        if (driver != null)
//            return driver;
//        throw new WrongDriverException("Please initialize driver first!");
//    }
    public static synchronized WebDriver getDriver() {
    return tdriver.get();
}
}
