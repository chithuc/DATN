package pages.Tiki;

import core.constants.Constants;
import data.Tiki_data.TikiData;
import io.qameta.allure.Step;
import model.Product;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;
import pages.base.PageObjectBase;

import java.util.ArrayList;
import java.util.List;

public class TikiHomePage extends PageObjectBase {

    private static final String TYPE = "TIKI";

    @FindBy(xpath = "//input[@data-view-id='main_search_form_input']")
    WebElement SEARCH_BOX;

    @FindBy(xpath = "//button[@data-view-id='main_search_form_button']")
    WebElement SEARCH_BUTTON;

    @FindBy(xpath = "//div[@data-qa-locator='general-products']//div[@class='RfADt']/a")
    List<WebElement> PRODUCT_NAME_LOCATOR;

    @FindBy(xpath = "//div[@data-qa-locator='general-products']//span[@class='ooOxS']")
    List<WebElement> PRODUCT_PRICE_LOCATOR;

    @FindBy(xpath = "//div[@data-qa-locator='general-products']//div[@class='RfADt']/a")
    List<WebElement> PRODUCT_LINK_LOCATOR;

    @FindBy(xpath = "//span[@class='account-label']")
    WebElement LOGIN_SIGNUP_BUTTON;
    @FindBy(xpath = "//p[text()='Thoát tài khoản']")
    WebElement LOGOUT_BUTTON;

    @FindBy(xpath = "//a[@class='product-item']")
    List<WebElement> items;

    String PRODUCT_LINK = "(//a[@class='product-item'])[%d]";
    String PRODUCT_NAME = "(//a[@class='product-item'])[%d]//div[@class='name']";
    String PRODUCT_DISCOUNT_PRICE = "(//a[@class='product-item'])[%d]//div[@class='price-discount__price']";
    String QUANTITY_SOLD = "//div[contains(@class,'StyledQtySold')]";
    String IMG_LINK = "(//picture[@class='webpimg-container'])[%d]/img";


    public TikiHomePage() {
        super();
    }
    private int count;

    @Step("Open url")
    public TikiHomePage navigate_Tiki_Url(String url) {
        OpenUrl(url);
        return this;
    }

    @Step("Verify title: {title}")
    public TikiHomePage verify_title(String title) {
        Assert.assertTrue(get_page_title().toLowerCase().contains(title.toLowerCase()), "Wrong title!");
        return this;
    }

    @Step("Send keys to 'Search' input")
    public TikiHomePage input_Keyword(String value) {
        sendKeys(SEARCH_BOX, value);
        return this;
    }

    @Step("Click 'Search' button")
    public TikiHomePage click_search_button() {
        sleep(2);
        click(SEARCH_BUTTON);
        sleep(2);
        return this;
    }

    @Step("Verify result is appeared")
    public TikiHomePage verifySizeResult() {
        sleep(10);
        count = items.size();
        Assert.assertTrue(count > 0);
        return this;
    }

    @Step("Verify result is appeared")
    public TikiHomePage verify_Search_Result(List<Product> products) {
        sleep(10);
        for(Product product: products){
            Assert.assertTrue(containString(product.getName()));
        }
        return this;
    }

    @Step("verify contains string")
    public boolean containString(String stringActual){
        boolean contain=false;
        if (stringActual.toLowerCase().contains(TikiData.SEARCH_EXPECTED_KEY[0]) == true){contain=true;}
        if (stringActual.toLowerCase().contains(TikiData.SEARCH_EXPECTED_KEY[1]) == true){contain=true;}
        if (stringActual.toLowerCase().contains(TikiData.SEARCH_EXPECTED_KEY[2]) == true){contain=true;}
        return contain;
    }

    @Step("Get Search product")
    public List<Product> get_searched_products() {
        if (count > 0) {
            List<Product> products = new ArrayList<>();
            for (int i = 1; i <= count; i++) {
                Product product = new Product(TYPE, getLink(i), getName(i), getPrice(i));
                products.add(product);
            }
            return products;
        }
        return null;
    }

    //PRIVATE methods: Get name, link, prices, remove special characters
    public double getPrice(int i) {
        try {
            String xpath = String.format(PRODUCT_DISCOUNT_PRICE, i);
            WebElement element = driver.findElement(By.xpath(xpath));
            String value = getText(element);
            return convertPrice(value);
        } catch (Exception e) {
            return 0;
        }
    }
    public String getFullPrice(int i) {
        try {
            String xpath = String.format(PRODUCT_DISCOUNT_PRICE, i);
            WebElement element = driver.findElement(By.xpath(xpath));
            String value = getText(element);
            return value;
        } catch (Exception e) {
            return "";
        }
    }

    public double convertPrice(String value) {
        value = value.replace("₫", "").replace(".", "");
        try {
            return Double.parseDouble(value);
        } catch (Exception e) {
            return 0.0;
        }
    }


    public String getName(int i) {
        try {
            String xpath = String.format(PRODUCT_NAME, i);
            WebElement element = driver.findElement(By.xpath(xpath));
            String value = getText(element);
            return value.trim();
        } catch (Exception e) {
            return "";
        }
    }

    public String getLink(int i) {
        try {
            String xpath = String.format(PRODUCT_LINK, i);
            WebElement element = driver.findElement(By.xpath(xpath));
            String value = element.getAttribute("href");
            return value.trim();
        } catch (Exception e) {
            return "";
        }
    }

    public String getQuantitySold(int i) {
        try {
            String xpath = String.format(QUANTITY_SOLD, i);
            WebElement element = driver.findElement(By.xpath(xpath));
            String value = getText(element);
            return value.trim();
        } catch (Exception e) {
            return "";
        }
    }

    public String getLinkImage(int i) {
        try {
            String xpath = String.format(IMG_LINK, i);
            WebElement element = driver.findElement(By.xpath(xpath));
            String value = getAttributes(element,"href");
            return value.trim();
        } catch (Exception e) {
            return "";
        }
    }

    @Step("click button to login or sign up ")
    public TikiHomePage clickLoginOrSignUpButton() {
        click(LOGIN_SIGNUP_BUTTON);
        sleep(2);
        return this;
    }

    @Step("click item on top of list search results  ")
    public TikiHomePage clickItemOnResultList(int i) {
        sleep(10);
        click(PRODUCT_LINK,i);
        return this;
    }

    @Step("click button logout  ")
    public TikiHomePage clickLogoutButton(boolean isLogin) {
        if(isLogin == true){
            clickLoginOrSignUpButton();
            click(LOGOUT_BUTTON);
        }
        return this;
    }

}
