package base;

import core.drivers.DriverManager;
import core.exceptions.WrongDriverException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.testng.annotations.*;

import java.util.concurrent.TimeUnit;

public class TestBase {

	static WebDriver driver;

	public void initialize() {
		System.setProperty(ChromeDriverService.CHROME_DRIVER_SILENT_OUTPUT_PROPERTY, "true");
		System.setProperty("webdriver.chrome.driver","./chromedriver/chromedriver.exe");

		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

	public static WebDriver getDriver() {
		return driver;
	}

	@BeforeTest
	@Parameters({"browser"})
	public void beforeTest(@Optional("chrome") String browser) throws WrongDriverException {
		DriverManager.initDriver(browser);
	}

	@BeforeMethod
	public void beforeMethod() {
	}

	@AfterMethod
	public void afterMethod() {

	}

	@AfterTest
	public void afterTest() {

	}

	@AfterSuite
	public void afterSuite() throws WrongDriverException {
		DriverManager.getDriver().quit();
	}

	
}
