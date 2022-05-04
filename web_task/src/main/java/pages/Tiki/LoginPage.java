package pages.Tiki;

import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;
import pages.base.PageObjectBase;

public class LoginPage extends PageObjectBase {
    // Entering phone number modal
    @FindBy(xpath = "//div[contains(@class,'input ')]/input[@type='tel']")
    WebElement PHONE_TEXT_BOX;
    @FindBy(xpath = "//button[text()='Tiếp Tục']")
    WebElement CONTINUE_BUTTON;
    @FindBy(xpath = "//div[@class='heading']/h4[text()='Xin chào,']")
    WebElement WELCOME_TITLE;
    @FindBy(xpath = "//div[@class='heading']/p[text()='Đăng nhập hoặc Tạo tài khoản']")
    WebElement WELCOME_LABEL;
    @FindBy(xpath = "//p[text()='Đăng nhập bằng email']")
    WebElement LOGIN_BY_EMAIL;
    // Entering password modal
    @FindBy(xpath = "//div[@class='input ']/input[@type='password']")
    WebElement PASSWORD_TEXT_BOX;
    @FindBy(xpath = "//div[@class='heading']/h4[text()='Nhập mật khẩu']")
    WebElement ENTER_PASSWORD_TITLE;
    @FindBy(xpath = "//div[@class='heading']/p[text()='Vui lòng nhập mật khẩu Tiki của số điện thoại ']")
    WebElement ENTER_PASSWORD_LABEL;
    @FindBy(xpath = "//p[@class='forgot-pass' and text()='Quên mật khẩu?']")
    WebElement FORGOT_PASSWORD;
    @FindBy(xpath = "//p[@class='login-with-sms' and text()='Đăng nhập bằng SMS']")
    WebElement LOGIN_BY_SMS;
    @FindBy(xpath = "//button[text()='Đăng Nhập']")
    WebElement LOGIN_BUTTON;

    //social element
    @FindBy(xpath = "//li[@class='social__item']/img[@alt='facebook']")
    WebElement FACEBOOK_BUTTON;
    @FindBy(xpath = "//li[@class='social__item']/img[@alt='google']")
    WebElement GOOGLE_BUTTON;

    private static final String NAME_PROFILE="//span[@class='account-label']/span[text()='%s']";
    private static final String VALIDATE_MESSAGE= "//span[@class='error-mess' and text()='%s' ]";

    public LoginPage(){super();}

    @Step("Input email or phone number")
    public LoginPage inputPhoneNumber(String userName) {
        waitDisplayed(PHONE_TEXT_BOX,2);
        sendKeys(PHONE_TEXT_BOX,userName);
        sleep(2);
        return this;
    }

    @Step("Input password")
    public LoginPage inputPassword(String passWord) {
        sendKeys(PASSWORD_TEXT_BOX,passWord);
        return this;
    }

    @Step("click continue button")
    public LoginPage clickContinueButton() {
        click(CONTINUE_BUTTON);
        return this;
    }

    @Step("Click 'Đăng nhập' button")
    public LoginPage clickLoginButton() {
        click(LOGIN_BUTTON);
        sleep(2);
        return this;
    }

    @Step("Verify Ui of welcome login page")
    public LoginPage verifyUiWelcomeLogin() {
        Assert.assertTrue(isControlDisplayed(WELCOME_TITLE));
        Assert.assertTrue(isControlDisplayed(WELCOME_LABEL));
        Assert.assertTrue(isControlDisplayed(PHONE_TEXT_BOX));
        Assert.assertTrue(isControlDisplayed(CONTINUE_BUTTON));
        Assert.assertTrue(isControlDisplayed(LOGIN_BY_EMAIL));
        Assert.assertTrue(isControlDisplayed(FACEBOOK_BUTTON));
        Assert.assertTrue(isControlDisplayed(GOOGLE_BUTTON));
        return this;
    }

    @Step("Verify Ui of Enter password modal")
    public LoginPage verifyUiEnterPasswordModal() {
        Assert.assertTrue(isControlDisplayed(ENTER_PASSWORD_TITLE));
        Assert.assertTrue(isControlDisplayed(ENTER_PASSWORD_LABEL));
        Assert.assertTrue(isControlDisplayed(PASSWORD_TEXT_BOX));
        Assert.assertTrue(isControlDisplayed(LOGIN_BUTTON));
        Assert.assertTrue(isControlDisplayed(FORGOT_PASSWORD));
        Assert.assertTrue(isControlDisplayed(LOGIN_BY_SMS));
        return this;
    }

    @Step("Verify login successful")
    public LoginPage verifyLoginSuccessful(String userNameExpect) {
        sleep(5);
        Assert.assertTrue(isControlDisplayed(NAME_PROFILE, userNameExpect));
        return this;
    }

    @Step("Verify when input phone number is empty")
    public LoginPage verifyInputInvalidValue(String validateMessage) {
        Assert.assertTrue(isControlDisplayed(VALIDATE_MESSAGE,validateMessage));
        return this;
    }
}
