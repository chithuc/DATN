package pages.base;

import core.constants.Constants;
import core.drivers.DriverManager;
import core.exceptions.*;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utilities.Utils;

import java.time.Duration;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class PageObjectBase {
    Actions action;
    public WebDriver driver;
    WebElement element;
    WebDriverWait waitExplicit;

    public PageObjectBase() {
        try {
            this.driver = DriverManager.getDriver();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        PageFactory.initElements(driver, this);
    }

    public static void sleep(int second) {
        try {
            int mlSecond = second * 1000;
            Thread.sleep(mlSecond);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * wait util element to be able to display
     *
     * @param element element waiting for
     */
    public boolean waitDisplayed(WebElement element, int second) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, second);
            wait.until(ExpectedConditions.visibilityOf(element));
            return true;
        } catch (Exception e) {
            return false;
        }

    }
    //Get title page
    public String get_page_title() {
        return driver.getTitle();
    }

    /**
     * wait util element to be able to click
     *
     * @param element element waiting for
     */
    public boolean waitClickable(WebElement element, int second) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, second);
            wait.until(ExpectedConditions.elementToBeClickable(element));
            return true;
        } catch (Exception e) {
            return false;
        }

    }

    /**
     * wait util element to be able to click
     *
     * @param value The value
     * @param element send key to element
     */
    public void sendKeys(WebElement element, String value) {
        waitDisplayed(element, Constants.LONG_TIME);
        element.clear();
        element.sendKeys(value);
    }

    /**
     * Send key to element
     *
     * @param value The value
     * @param element send key to element
     */
    public void sendKey(WebElement element, Keys value) {
        waitDisplayed(element, Constants.LONG_TIME);
        element.sendKeys(value);
    }

    /**
     * Click to element
     *
     * @param element click to element
     */
    public void click(WebElement element) {
        waitClickable(element, Constants.LONG_TIME);
        element.click();
    }
    public void doubleClickToElement(WebElement element) {
//        WebElement element = driver.findElement(By.xpath(locator));
        action = new Actions(this.driver);
        action.doubleClick(element);
    }
    public void clearTextElement(String locator, String... values) {
        locator = String.format(locator, (Object[])values);
        WebElement element = driver.findElement(By.xpath(locator));
        element.clear();
    }
    public void clearTextElement(WebElement element) {
        element.clear();
    }

    public void pressCtrlA() {
        action = new Actions(this.driver);
        action.keyDown(Keys.CONTROL).sendKeys("a").keyUp(Keys.CONTROL).build().perform();
    }

    public void pressBackspace() {
        action = new Actions(this.driver);
        action.sendKeys(Keys.BACK_SPACE);
    }
    public void pressDelete() {
        action = new Actions(this.driver);
        action.sendKeys(Keys.DELETE);
    }
    /**
     * Click to element
     *
     * @param locator locator of element
     * @param values value of locator
     */
    public void click(String locator,int values) {
        String xpath = String.format(locator, values);
        WebElement element = driver.findElement(By.xpath(xpath));
        element.click();
    }
    /**
     * Click to element
     *
     * @param locator locator of element
     * @param values value of locator
     */
    public void click(String locator,String values) {
        String xpath = String.format(locator, values);
        WebElement element = driver.findElement(By.xpath(xpath));
        element.click();
    }
    /**
     * Scroll to element using JavaScript
     *
     * @param element element scroll to
     */
    protected void scrollTo(WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
    }

    /**
     * Return element's text value
     *
     * @param element get text of element
     */
    public String getText(WebElement element) {
        try {
            scrollTo(element);
        } catch (Exception e) {
            e.printStackTrace();
        }
        String value = element.getText();
        if (Utils.isNullOrEmpty(value)) {
            value = element.getAttribute("value");
        }
        if (Utils.isNullOrEmpty(value)) {
            value = element.getAttribute("textContent");
        }
        return value.trim();
    }
    /**
     * get attribute of element
     *
     * @param element get attributes of element
     */
    //get attribute
    public String getAttributes(WebElement element,String attributes){
            String value = element.getAttribute(attributes);
            return value.trim();
    }
    /**
     * Open to url
     *
     * @param url open url
     */
    protected void OpenUrl(String url) {
        driver.manage().timeouts().implicitlyWait(Constants.LONG_TIME, TimeUnit.SECONDS);
        driver.get(url);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
    }
    /**
     * get Current page url
     *
     */
    public String getCurrentPageUrl() {
        return this.driver.getCurrentUrl();
    }
    /**
     * refresh Current page
     *
     */
    public void refreshCurrentPage() {
        this.driver.navigate().refresh();
    }
    /**
     * return boolean that element displayed
     * @param element is displayed
     */
    public boolean isControlDisplayed(WebElement element) {
        try {
            element = this.waitForElementVisible(element, 2);
            return element.isDisplayed();
        } catch (Exception var5) {
            return false;
        }
    }
    public boolean isControlUnDisplayed(String locator) {
        List<WebElement> elements = this.driver.findElements(By.xpath(locator));
        if (elements.isEmpty()) {
            return true;
        } else if (!((WebElement)elements.get(0)).isDisplayed()) {
            return true;
        } else {
            return false;
        }
    }
    public boolean isControlUnDisplayed(String locator, String... values) {
        locator = String.format(locator, (Object[])values);
        List<WebElement> elements = this.driver.findElements(By.xpath(locator));
        if (elements.isEmpty()) {
            return true;
        } else if (!((WebElement)elements.get(0)).isDisplayed()) {
            return true;
        } else {
            return false;
        }
    }

    public WebElement waitForElementVisible(WebElement element, long timeout) {
        this.waitExplicit = new WebDriverWait(this.driver, timeout);

        try {
            return (WebElement)this.waitExplicit.until(ExpectedConditions.visibilityOf(element));
        } catch (Exception var5) {
            return null;
        }
    }
    /**
     * return boolean that element displayed
     * @param locator
     * @param values
     */
    public boolean isControlDisplayed(String locator, String values) {
        locator = String.format(locator, values);
        this.element = this.driver.findElement(By.xpath(locator));
        return this.element.isDisplayed();
    }
    public boolean isControlDisplayed(String locator, String... values) {
        locator = String.format(locator, (Object[])values);
        this.element = this.driver.findElement(By.xpath(locator));
        return this.element.isDisplayed();
    }
    /**
     * return boolean that element displayed
     * @param element
     */
    public String getTextElement(WebElement element) {
        element = this.waitForElementVisible(element,1);
        return element.getText();
    }


}
