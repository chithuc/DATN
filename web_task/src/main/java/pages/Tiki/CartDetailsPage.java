package pages.Tiki;

import core.constants.Constants;
import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;
import pages.base.PageObjectBase;

public class CartDetailsPage extends PageObjectBase {
    @FindBy(xpath = "//div[text()='Xác Nhận']")
    private WebElement CONFIRM_DELETE_BUTTON;
    @FindBy(xpath = "//button[contains(text(),'Mua Hàng')]")
    private WebElement ORDER_BUTTON;

    private static final String NOTE_EMPTY_CART = "//p[@class='empty__note' and text()='Không có sản phẩm nào trong giỏ hàng của bạn.']";
    private String CART_URL = Constants.TIKI_URL + "checkout/cart?src=header_cart";
    private static final String DELETE_ITEM_BUTTON = "//a[contains(text(),'%s')]/ancestor::div[@class='col-1']/following-sibling::div[@class='col-5']/span[@class='product__delete']";
    private static final String ITEM_NAME_SELECTOR = "//a[contains(text(),'%s')]";
    private static final String ITEMS_CHECKBOX = ITEM_NAME_SELECTOR + "/preceding::div[@class='product__checkbox']";
    @Step("Navigate to Cart detail url")
    public CartDetailsPage navigate_Cart_Details_Url() {
        OpenUrl(CART_URL);
        return this;
    }

    @Step("delete item into cart")
    public CartDetailsPage delete_Item_On_Cart(String itemName) {
        sleep(2);
        if(isControlUnDisplayed(NOTE_EMPTY_CART)) {
            sleep(2);
            click(DELETE_ITEM_BUTTON, itemName);
            click_Confirm_Delete_Button();
        }
        return this;
    }

    @Step("click confirm delete button into cart")
    public CartDetailsPage click_Confirm_Delete_Button() {
        click(CONFIRM_DELETE_BUTTON);
        return this;
    }

    @Step("verify item is deleted on the cart")
    public CartDetailsPage verify_Delete_Item(String itemName) {
        sleep(5);
        Assert.assertTrue(isControlUnDisplayed(ITEM_NAME_SELECTOR,itemName));
        return this;
    }

    @Step("click item checkbox")
    public CartDetailsPage chooseAItem(String itemName) {
        click(ITEMS_CHECKBOX,itemName);
        return this;
    }

    @Step("click Order button")
    public CartDetailsPage clickOrderButton() {
        click(ORDER_BUTTON);
        return this;
    }
}
