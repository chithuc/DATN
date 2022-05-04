package testcases.tests.Tiki;

import base.TestBase;
import core.config.PageFactoryManager;
import core.constants.Constants;
import data.Tiki_data.TikiData;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.Tiki.LoginPage;
import pages.Tiki.ShippingPage;
import pages.Tiki.TikiHomePage;
import utilities.ReportListener;
import utilities.Utils;

import java.util.List;

@Listeners({ReportListener.class})
@Epic("Automation test")
public class ShippingTests extends TestBase {
    private ShippingPage shippingPage;
    private TikiHomePage tikiHomePage;
    @BeforeMethod
    public void initializePage()  {
        tikiHomePage = new TikiHomePage();
        shippingPage = new ShippingPage();
    }

    @Test(description = "Scenario: Add new address successful",groups = {"Shipping"})
    @Severity(SeverityLevel.BLOCKER)
    @Description("Search and add item into cart")
    public void Test_14_Add_And_Edit_Address_Successfull() throws Exception {
        String addressFile = "src/main/java/data/Tiki_data/AddressData.csv";
        List<String> addressList = Utils.readRecordFromLine(1,Utils.readFile(addressFile));
        tikiHomePage.navigate_Tiki_Url(Constants.TIKI_URL)
                .clickLoginOrSignUpButton();
        PageFactoryManager.get(LoginPage.class)
                .inputPhoneNumber(TikiData.phoneNumber)
                .clickContinueButton()
                .inputPassword(TikiData.passWord)
                .clickLoginButton();
        String fullName= addressList.get(0)+ Utils.getRandomFiveCharsString(10);
        String phone= addressList.get(1);
        String address= addressList.get(6);
        String city= addressList.get(2);
        String district= addressList.get(3);
        String commune= addressList.get(4);
        //add new address
        shippingPage.navigateShippingUrl()
                .clickAddNewAddressButton()
                .inputFullName(fullName)
                .inputPhoneNumber(phone)
                .chooseCity(city)
                .inputAddress(address)
                .chooseDistrict(district)
                .chooseTypeAddress()
                .chooseCommune(commune)

                .clickDefaultAddress()
                .clickCreateAddress()
                .verifyNavigatePaymentPage();
                //edit address
        String fullNameEdit = "Edit" + fullName;
        shippingPage.navigateShippingUrl()
                .clickEditAddress(fullName)
                .inputFullName(fullNameEdit)
                .clickUpdateButton()
                .verifyAddress(fullNameEdit,phone,city,district,commune,address);
    }
}
