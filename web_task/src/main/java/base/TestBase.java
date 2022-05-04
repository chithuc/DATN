package base;

import core.drivers.DriverManager;
import core.exceptions.WrongDriverException;
import org.testng.annotations.*;

public class TestBase {
//	public WebDriver driver;
//	public static ThreadLocal<WebDriver> tdriver = new ThreadLocal<WebDriver>();
//
//	public WebDriver initialize_driver() {
//
//		WebDriverManager.chromedriver().setup();
//		driver = new ChromeDriver();
//		driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
//		driver.manage().window().maximize();
//		tdriver.set(driver);
//		return getDriver();
//	}
//
//	public static synchronized WebDriver getDriver() {
//		return tdriver.get();
//	}

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
