package pages.Tiki;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;
import pages.base.PageObjectBase;

public class ItemDetailPage extends PageObjectBase {
    @FindBy(xpath = "//div[@class='header']/h1[@class='title']")
    private WebElement ITEM_DETAIL_NAME;
    @FindBy(xpath = "//div[@class='product-price__current-price']")
    private WebElement CURRENT_PRICE;
    @FindBy(xpath = "//div[@class='header']//div[@data-view-id='pdp_quantity_sold']")
    private WebElement QUANTITY_SOLD;
    @FindBy(xpath = "(//picture[@class='webpimg-container'])[1]/img")
    private WebElement IMAGE_SELECTOR;
    @FindBy(xpath = "//button[contains(@data-view-id,'add_to_cart_button')]")
    private WebElement ADD_CART_BUTTON;
    @FindBy(xpath = "//button[contains(@class,'installment-payment') and text()='Trả Góp Qua Thẻ Tín Dụng']")
    private WebElement INSTALLMENT_BUTTON;

    @FindBy(xpath = "//span[text()='Xem Shop']")
    private WebElement VIEW_SHOP_BUTTON;
    @FindBy(xpath = "//span[text()='Theo Dõi']")
    private WebElement FOLLOW_BUTTON;

    @FindBy(xpath = "//div[contains(@class,'CartNotification')]/p[text()='Thêm vào giỏ hàng thành công!']")
    private WebElement NOTIFICATION_LABEL_CART;
    @FindBy(xpath = "//a[@class='btn-view-cart']")
    private WebElement VIEW_CART_BUTTON;
    //quantity element
    @FindBy(xpath = "//p[text()='Số Lượng']/following-sibling::div//input[@class='input']")
    private WebElement ADD_QUANTITY_ITEM ;
    @FindBy(xpath = "//p[text()='Số Lượng']/following-sibling::div//img[@alt='add-icon']")
    private WebElement PLUS_QUANTITY_BUTTON ;
    @FindBy(xpath = "//p[text()='Số Lượng']/following-sibling::div//img[@alt='remove-icon']")
    private WebElement MINUS_QUANTITY_BUTTON ;
    //error message
    @FindBy(xpath = "//div[@class='styles__StyledToast-sc-bxwegb-0 guhHLt']")
    private WebElement ERROR_MESSAGE ;

    @Step("verify UI item detail page")
    public ItemDetailPage verifyUiItemDetailPage() {
        Assert.assertTrue(isControlDisplayed(ADD_CART_BUTTON));
        Assert.assertTrue(isControlDisplayed(INSTALLMENT_BUTTON));
        Assert.assertTrue(isControlDisplayed(VIEW_SHOP_BUTTON));
        Assert.assertTrue(isControlDisplayed(FOLLOW_BUTTON));
        return this;
    }

    @Step("verify data of item detail page")
    public ItemDetailPage verifyDataItemDetailPage(String itemNameExpect,String currentPriceExpect,String quantitySoldExpect) {
        Assert.assertEquals(getTextElement(ITEM_DETAIL_NAME),itemNameExpect);
        Assert.assertEquals(getTextElement(CURRENT_PRICE),currentPriceExpect);
        Assert.assertEquals(getTextElement(QUANTITY_SOLD),quantitySoldExpect);
        return this;
    }

    @Step("click add item into cart")
    public ItemDetailPage clickAddItemIntoCart() {
        click(ADD_CART_BUTTON);
        return this;
    }

    @Step("verify add item into cart")
    public ItemDetailPage verifyAddItemIntoCart() {
        sleep(2);
        Assert.assertTrue(isControlDisplayed(NOTIFICATION_LABEL_CART));
        Assert.assertTrue(isControlDisplayed(VIEW_CART_BUTTON));
        return this;
    }

    @Step("input the quantity of item")
    public ItemDetailPage inputQuantityOfItem(String quantity) {
        sendKeys(ADD_QUANTITY_ITEM,quantity);
        sleep(3);
        return this;
    }

    @Step("verify value of the quantity field")
    public ItemDetailPage verifyValueQuantityField(String quantityExpected) {
        Assert.assertEquals(getAttributes(ADD_QUANTITY_ITEM,"value"),quantityExpected);
        return this;
    }

    @Step("double click on the quantity button")
    public ItemDetailPage doubleClickQuantityButton() {
        doubleClickToElement(ADD_QUANTITY_ITEM);
        return this;
    }

    @Step("click on the quantity button")
    public ItemDetailPage clickQuantityButton() {
        click(ADD_QUANTITY_ITEM);
        return this;
    }

    @Step("clear value of quantity")
    public ItemDetailPage clearQuantityValue() {
        scrollTo(ADD_QUANTITY_ITEM);
        clearTextElement(ADD_QUANTITY_ITEM);
        return this;
    }

    @Step("blacked out value of quantity")
    public ItemDetailPage blackedOutValue() {
        pressCtrlA();
        return this;
    }

    @Step("click plus value of quantity")
    public ItemDetailPage clickPlusButton() {
        click(PLUS_QUANTITY_BUTTON);
        return this;
    }
    @Step("click add more value of quantity")
    public ItemDetailPage clickMinusButton() {
        click(MINUS_QUANTITY_BUTTON);
        return this;
    }
    @Step("verify show error message")
    public ItemDetailPage verifyShowErrorMessage() {
        Assert.assertTrue(isControlDisplayed(ERROR_MESSAGE));
        return this;
    }

}
