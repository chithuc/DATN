package testcases.tests.Tiki;

import base.TestBase;
import core.config.PageFactoryManager;
import core.constants.Constants;
import data.Tiki_data.TikiData;
import io.qameta.allure.*;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import pages.Tiki.*;
import utilities.ReportListener;
import utilities.Utils;

@Listeners({ReportListener.class})
@Epic("Automation test")
public class CartTests extends TestBase {
    private TikiHomePage tikiHomePage;
    String itemName="";

    @BeforeTest
    public void initializePage()  {
        tikiHomePage = new TikiHomePage();
    }

    @Test(description = "Scenario: Add and delete item into cart successful",groups = {"Cart"})
    @Severity(SeverityLevel.BLOCKER)
    @Description("Add item into cart then delete")
    public void Test_05_Add_And_Delete_Item_Into_Cart_Successfull()  {
        tikiHomePage.navigate_Tiki_Url(Constants.TIKI_URL)
                .input_Keyword(TikiData.SEARCH_VALUE)
                .click_search_button();
//        itemName=tikiHomePage.getName(1);
        itemName=tikiHomePage.getName(1).split("-")[0];
        tikiHomePage.clickItemOnResultList(1);
        PageFactoryManager.get(ItemDetailPage.class)
        //input value of field is valid
                .inputQuantityOfItem("1")
                .clickAddItemIntoCart();

        PageFactoryManager.get(LoginPage.class)
                .inputPhoneNumber(TikiData.phoneNumber)
                .clickContinueButton()
                .inputPassword(TikiData.passWord)
                .clickLoginButton();
        PageFactoryManager.get(ItemDetailPage.class)
                .verifyAddItemIntoCart();
        //delete item on cart details
        PageFactoryManager.get(CartDetailsPage.class).navigate_Cart_Details_Url()
                .delete_Item_On_Cart(itemName)
                .verify_Delete_Item(itemName);
    }

    @Test(description = "Scenario: Add address when order",groups = {"Cart"})
    @Severity(SeverityLevel.BLOCKER)
    @Description("Verify add new address successfully")
    public void Test_13_Order()  {
//        tikiHomePage.navigate_Tiki_Url(Constants.TIKI_URL)
//                .input_Keyword(TikiData.SEARCH_VALUE)
//                .click_search_button();
//        itemName=tikiHomePage.getName(1).split("-")[0];
//        tikiHomePage.clickItemOnResultList(1);
//        //add item into cart
//        PageFactoryManager.get(ItemDetailPage.class)
//                .clickAddItemIntoCart();
//        //login
//        PageFactoryManager.get(LoginPage.class)
//                .inputPhoneNumber(TikiData.phoneNumber)
//                .clickContinueButton()
//                .inputPassword(TikiData.passWord)
//                .clickLoginButton();
//        //choose item to order
//        PageFactoryManager.get(CartDetailsPage.class).navigate_Cart_Details_Url()
//                .chooseAItem(itemName)
//                .clickOrderButton();
    }

    @AfterMethod(alwaysRun = true)
    public void cleanup() {

    }
}
