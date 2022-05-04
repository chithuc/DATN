package pages.VatGia;

import core.constants.Constants;
import data.Tiki_data.TikiData;
import io.qameta.allure.Step;
import model.Product;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;
import pages.base.PageObjectBase;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class VatGiaPage extends PageObjectBase {

    private static final String TYPE = "VATGIA";
    @FindBy(xpath = "//input[@id='header_search_keyword']")
    WebElement SEARCH_BOX;

    @FindBy(xpath = "//button[@type='submit']")
    WebElement SEARCH_BUTTON;

    @FindBy(xpath = "//div[@class='quick_search_result fl']//div[@class='name']/a")
    List<WebElement> PRODUCT_NAME_LOCATOR;

    @FindBy(xpath = "//div[@class='quick_search_result fl']/div[not(@class='hidden')]//div[@class='price']")
    List<WebElement> PRODUCT_PRICE_LOCATOR;

    @FindBy(xpath = "//div[@class='quick_search_result fl']/div[not(@class='hidden')]//a[@class='picture_link']")
    List<WebElement> PRODUCT_LINK_LOCATOR;


    public VatGiaPage() {
        super();
    }

    @Step("Open url")
    public VatGiaPage navigate_VatGia_Url(String url) {
        driver.manage().timeouts().implicitlyWait(Constants.LONG_TIME, TimeUnit.SECONDS);
        driver.get(url);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
        return this;
    }

    @Step("Verify title")
    public VatGiaPage verify_title(String title) {
        Assert.assertTrue(get_page_title().toLowerCase().contains(title.toLowerCase()), "Wrong title!");
        return this;
    }

    @Step("Input keyword to search box")
    public VatGiaPage input_Keyword(String value) {
        sendKeys(SEARCH_BOX, value);
        return this;
    }

    @Step("Click 'Search' button")
    public VatGiaPage click_search_button() {
        click(SEARCH_BUTTON);
        return this;
    }
    @Step("Verify result is appeared")
    public VatGiaPage verify_Search_Result() {
        sleep(10);
        String[] productNameList  = PRODUCT_NAME_LOCATOR.stream().map(x -> getText(x)).collect(Collectors.toList()).toArray(new String[0]);
        for (String name : productNameList) {
            Assert.assertTrue(name.toLowerCase().contains(TikiData.SEARCH_VALUE));
        }
        return this;
    }

    @Step("Get Search product")
    public List<Product> get_searched_products() {
        String[] productNameList  = PRODUCT_NAME_LOCATOR.stream().map(x -> getText(x)).collect(Collectors.toList()).toArray(new String[0]);
        String[] productLinkList  = PRODUCT_LINK_LOCATOR.stream().map(x -> getAttributes(x,"href")).collect(Collectors.toList()).toArray(new String[0]);
        String[] productPriceList  = PRODUCT_PRICE_LOCATOR.stream().map(x -> getText(x)).collect(Collectors.toList()).toArray(new String[0]);

        List<Integer> priceList=new ArrayList<>();
        for(String price: productPriceList) {
            try {
                priceList.add(Integer.parseInt(price.replaceAll("[^0-9]", "")));
            } catch (NumberFormatException nfe) {
                priceList.add(0);
            }
        }

        List<Product> products = new ArrayList<>();
        if (productNameList.length > 0) {

            for (int i = 0; i < productNameList.length; i++) {
                Product product = new Product(TYPE,productLinkList[i], productNameList[i],priceList.get(i)) ;
                products.add(product);
            }

        }
        return products;
    }
}
