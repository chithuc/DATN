package testcases.tests.Tiki;

import base.TestBase;
import core.config.PageFactoryManager;
import core.constants.Constants;
import data.Tiki_data.TikiData;
import io.qameta.allure.*;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import pages.Tiki.TikiHomePage;
import pages.Tiki.LoginPage;
import utilities.ReportListener;

@Listeners({ReportListener.class})
@Epic("Automation test")
@Feature("Login account")
public class LoginTests extends TestBase {
    boolean isLogin = false;
    private TikiHomePage tikiHomePage;

    @BeforeTest
    public void initializePage()  {
        tikiHomePage = new TikiHomePage();
    }

    @Test(description = "Login | Happy case: Login with valid value",groups = {"Login"})
    @Severity(SeverityLevel.BLOCKER)
    @Description("Login successful with phone number and password is correct")
    public void Test_01_loginWithUserNameAndPasswordIsValid()  {
        tikiHomePage.navigate_Tiki_Url(Constants.TIKI_URL)
                .clickLoginOrSignUpButton();
        PageFactoryManager.get(LoginPage.class)
                .verifyUiWelcomeLogin()
                .inputPhoneNumber(TikiData.phoneNumber)
                .clickContinueButton()
                .verifyUiEnterPasswordModal()
                .inputPassword(TikiData.passWord)
                .clickLoginButton()
                .verifyLoginSuccessful(TikiData.userName);
        isLogin = true;
    }

    @Test(description = "Login | validate phone and password: Empty value")
    @Severity(SeverityLevel.BLOCKER)
    @Description("Login successful with phone number and password is empty")
    public void Test_02_loginWithUserNameAndPasswordIsEmpty()  {
        tikiHomePage.navigate_Tiki_Url(Constants.TIKI_URL)
                .clickLoginOrSignUpButton();
        PageFactoryManager.get(LoginPage.class)
                .inputPhoneNumber(TikiData.phoneNumberInvalid)
                .clickContinueButton()
                .verifyInputInvalidValue(Constants.VALIDATE_MESSAGE[0])
                .inputPhoneNumber(TikiData.phoneNumber)
                .clickContinueButton()
                .inputPassword(TikiData.passWordInvalid)
                .clickLoginButton()
                .verifyInputInvalidValue(Constants.VALIDATE_MESSAGE[2]);
        isLogin = false;
    }
    @AfterMethod()
    public void cleanup(){
        tikiHomePage.clickLogoutButton(isLogin);
    }
}
