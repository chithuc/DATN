package pages.Tiki;

import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.base.PageObjectBase;

public class PaymentPage extends PageObjectBase {
    @FindBy(xpath = "//span[text()='Địa chỉ giao hàng']/following-sibling::a[@data-view-id='checkout.confirmation_shipping_location.change' and text()='Sửa']")
    private WebElement EDIT_ADDRESS_BUTTON;

    @Step("click edit button ")
    public PaymentPage clickEditAddress() {
        click(EDIT_ADDRESS_BUTTON);
        return this;
    }
}
