package pages.Tiki;

import core.constants.Constants;
import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;
import pages.base.PageObjectBase;
import sun.security.timestamp.TSRequest;

import java.security.SecureRandom;

public class ShippingPage extends PageObjectBase {
    private static final String SHIPPING_URL= Constants.TIKI_URL + "checkout/shipping";
    @FindBy(xpath = "//span[text()='Thêm địa chỉ giao hàng mới']")
    private WebElement ADD_NEW_ADDRESS_BUTTON;
    private static final String EDIT_ADDRESS_BUTTON = "//div[@class='address-list']//p[text()='%s']/following-sibling::p[@class='action']/button[contains(@class,'edit-address')]";


    @FindBy(xpath = "//input[@name='full_name']")
    private WebElement FULL_NAME_TEXT_BOX;
    @FindBy(xpath = "//input[@name='telephone']")
    private WebElement PHONE_TEXT_BOX;
    @FindBy(xpath = "//textarea[@name='street']")
    private WebElement ADDRESS_TEXT_BOX;

    private static final String ADDRESS_SELECTOR = "//label[text()='%s']/following::div[@class='rc-select-selection__rendered']";
    private static final String SUGGEST_LIST_SELECTOR = "//ul[@role='listbox']/li[text()='%s']";

    private static final String TYPE_LOCATOR = "//input[@value='%s']/parent::*";
    @FindBy(xpath = "//input[@name='is_default']/parent::*")
    private WebElement SET_DEFAULTS_CHECKBOX ;

    @FindBy(xpath = "//button[text()='Cập nhật']")
    private WebElement UPDATE_BUTTON;
    @FindBy(xpath = "//div[@class='button-group']/button[@class='create-update' and text()='Giao đến địa chỉ này']")
    private WebElement CREATE_NEW_ADDRESS_BUTTON;

    //Address list infomation

    private static final String NAME_SHIPPING = "//p[@class='name' and text()='%s']";
    private static final String ADDRESS_SHIPPING = "//p[@class='address' and text()='Địa chỉ: %s, %s, %s, %s']";
    private static final String PHONE_SHIPPING = "//p[@class='phone' and text()='Điện thoại: %s']";

    private static final String edit_button = NAME_SHIPPING + "/following-sibling::p[@class='action']/button[text()='Sửa']";
    public ShippingPage() {
            super();
    }

    @Step("Navigate to shipping page")
    public ShippingPage navigateShippingUrl() {
        OpenUrl(SHIPPING_URL);
        sleep(2);
        return this;
    }

    @Step("click add new address")
    public ShippingPage clickAddNewAddressButton() {
        sleep(5);
        click(ADD_NEW_ADDRESS_BUTTON);
        sleep(2);
        return this;
    }

    @Step("input full name")
    public ShippingPage inputFullName(String fullName) {
        scrollTo(FULL_NAME_TEXT_BOX);
        click(FULL_NAME_TEXT_BOX);
        sendKeys(FULL_NAME_TEXT_BOX,fullName);
        return this;
    }
    @Step("input phone number")
    public ShippingPage inputPhoneNumber(String phoneNumber) {
        click(PHONE_TEXT_BOX);
        sendKeys(PHONE_TEXT_BOX,phoneNumber);
        sleep(5);
        return this;
    }
    @Step("input address")
    public ShippingPage inputAddress(String address) {
//        click(ADDRESS_TEXT_BOX);
        sendKeys(ADDRESS_TEXT_BOX,address);
        return this;
    }

    @Step("choose city")
    public ShippingPage chooseCity(String city) {
        click(ADDRESS_SELECTOR,Constants.ADDRESS[0]);
        sleep(5);
        click(SUGGEST_LIST_SELECTOR,city);
        sleep(5);
        return this;
    }
    @Step("choose district")
    public ShippingPage chooseDistrict(String district) {
        click(ADDRESS_SELECTOR,Constants.ADDRESS[1]);
        sleep(5);
        click(SUGGEST_LIST_SELECTOR,district);
        sleep(5);
        return this;
    }
    @Step("choose commune")
    public ShippingPage chooseCommune(String commune) {
        click(ADDRESS_SELECTOR,Constants.ADDRESS[2]);
        sleep(5);
        click(SUGGEST_LIST_SELECTOR,commune);
        sleep(5);
        return this;
    }

    @Step("choose type of address is home")
    public ShippingPage chooseTypeAddress() {
        click(TYPE_LOCATOR,Constants.TYPE_ADDRESS[0]);
        sleep(3);
        return this;
    }

    @Step("click to set address is default")
    public ShippingPage clickDefaultAddress() {
        sleep(5);
        click(SET_DEFAULTS_CHECKBOX);
        return this;
    }
    @Step("update address")
    public ShippingPage clickUpdateButton() {
        scrollTo(UPDATE_BUTTON);
        sleep(2);
        click(UPDATE_BUTTON);
        return this;
    }
    @Step("click create address button")
    public ShippingPage clickCreateAddress() {
        click(CREATE_NEW_ADDRESS_BUTTON);
        return this;
    }
    @Step("verify create address button")
    public ShippingPage verifyAddress(String name, String phone, String city, String district, String commune, String street) {
        sleep(5);
        Assert.assertTrue(isControlDisplayed(NAME_SHIPPING,name));
        Assert.assertTrue(isControlDisplayed(PHONE_SHIPPING,phone));
        Assert.assertTrue(isControlDisplayed(String.format(ADDRESS_SHIPPING,street,commune,district,city)));
        return this;
    }
    @Step("verify navigate to payment page")
    public ShippingPage verifyNavigatePaymentPage() {
        sleep(5);
        Assert.assertEquals(getCurrentPageUrl(),Constants.TIKI_URL + "checkout/payment");
        return this;
    }
    @Step("click Edit address button")
    public ShippingPage clickEditAddress(String fullName) {
        click(edit_button,fullName);
        return this;
    }
}
