package testcases.tests.Tiki;

import base.TestBase;
import core.config.PageFactoryManager;
import core.constants.Constants;
import data.Tiki_data.TikiData;
import io.qameta.allure.*;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.Tiki.ItemDetailPage;
import pages.Tiki.TikiHomePage;
import utilities.ReportListener;

@Listeners({ReportListener.class})
@Epic("Automation test")
@Feature("Item details")
public class ItemDetailTests extends TestBase {
    private TikiHomePage tikiHomePage;
    static WebDriver driver;

    @BeforeTest
    public void initializePage()  {
        tikiHomePage = new TikiHomePage();
        TestBase testBase = new TestBase();
        testBase.initialize();
        driver = getDriver();
        driver.get("https://tiki.vn/");
    }

    @Test(description = "View item detail successful",groups = {"Item_detail"})
    @Severity(SeverityLevel.BLOCKER)
    @Description("Able to view item detail page")
    public void Test_04_viewItemDetailSuccessful()  {
        tikiHomePage.navigate_Tiki_Url(Constants.TIKI_URL).verify_title(Constants.TIKI_TITLE)
                .input_Keyword(TikiData.SEARCH_VALUE)
                .click_search_button();
        String nameItemExpect = tikiHomePage.getName(1);
        String priceItemExpect = tikiHomePage.getFullPrice(1);
        String quantitySoldExpect = tikiHomePage.getQuantitySold(1);
        tikiHomePage.clickItemOnResultList(1);

        PageFactoryManager.get(ItemDetailPage.class)
                .verifyUiItemDetailPage()
                .verifyDataItemDetailPage(nameItemExpect,priceItemExpect,quantitySoldExpect);
    }
    @Test(description = "Scenario: Validate quantity of item when entering value is empty",groups = {"Item_detail"})
    @Severity(SeverityLevel.BLOCKER)
    @Description("Verify when input value of quantity is invalid : Empty")
    public void Test_06_Entering_Value_Of_Quantity_Field_Is_Empty()  {
        tikiHomePage.navigate_Tiki_Url(Constants.TIKI_URL)
                .input_Keyword(TikiData.SEARCH_VALUE)
                .click_search_button();
        tikiHomePage.clickItemOnResultList(1);
        PageFactoryManager.get(ItemDetailPage.class)
                //input value of field is empty
                .clearQuantityValue()
                .verifyValueQuantityField("1");
    }
    @Test(description = "Scenario: Validate quantity of item when entering value is negative number ",groups = {"Item_detail"})
    @Severity(SeverityLevel.BLOCKER)
    @Description("input value of quantity is negative number")
    public void Test_07_Entering_Value_Of_Quantity_Field_Is_Negative()  {
        tikiHomePage.navigate_Tiki_Url(Constants.TIKI_URL)
                .input_Keyword(TikiData.SEARCH_VALUE)
                .click_search_button();
        tikiHomePage.clickItemOnResultList(1);
        PageFactoryManager.get(ItemDetailPage.class)
                .clickQuantityButton()
                .clearQuantityValue()
                //input of field is negative value
                .inputQuantityOfItem("-1")
                .verifyValueQuantityField("1");
    }
    @Test(description = "Scenario: Validate quantity of item when entering value is zero number ",groups = {"Item_detail"})
    @Severity(SeverityLevel.BLOCKER)
    @Description("Verify when input value of quantity equal to zero")
    public void Test_08_Entering_Value_Of_Quantity_Field_Is_Zero()  {
        tikiHomePage.navigate_Tiki_Url(Constants.TIKI_URL)
                .input_Keyword(TikiData.SEARCH_VALUE)
                .click_search_button();
        tikiHomePage.clickItemOnResultList(1);
        PageFactoryManager.get(ItemDetailPage.class)
                .clickQuantityButton()
                .clearQuantityValue()
                .inputQuantityOfItem("0")
                .verifyValueQuantityField("1");
    }
    @Test(description = "Scenario: Validate quantity of item when entering value is characters",groups = {"Item_detail"})
    @Severity(SeverityLevel.BLOCKER)
    @Description("Verify when input value of quantity is characters")
    public void Test_09_Entering_Value_Of_Quantity_Field_Is_Characters()  {
        tikiHomePage.navigate_Tiki_Url(Constants.TIKI_URL)
                .input_Keyword(TikiData.SEARCH_VALUE)
                .click_search_button();
        tikiHomePage.clickItemOnResultList(1);
        PageFactoryManager.get(ItemDetailPage.class)
                .clickQuantityButton()
                .clearQuantityValue()
                .inputQuantityOfItem("Demo")
                .verifyValueQuantityField("1");
    }
    @Test(description = "Scenario: Increase or decrease the number of items",groups = {"Item_detail"})
    @Severity(SeverityLevel.BLOCKER)
    @Description("Verify increase or decrease the quantity")
    public void Test_10_Verify_Increase_Decrease_Quantity_Of_Item()  {
        int numberItems =2;
        tikiHomePage.navigate_Tiki_Url(Constants.TIKI_URL)
                .input_Keyword(TikiData.SEARCH_VALUE)
                .click_search_button();
        tikiHomePage.clickItemOnResultList(1);
        PageFactoryManager.get(ItemDetailPage.class)
                .clickQuantityButton()
                .blackedOutValue()
                .inputQuantityOfItem(String.valueOf(numberItems))
                .clickPlusButton()
                .verifyValueQuantityField(String.valueOf(numberItems+1))
                .clickMinusButton()
                .verifyValueQuantityField(String.valueOf(numberItems));
    }
    @Test(description = "Scenario: Purchase quantity is more than stock quantity",groups = {"Item_detail"})
    @Severity(SeverityLevel.BLOCKER)
    @Description("Verify not allow to add item into cart when purchase quantity is more than inventory quantity")
    public void Test_11_Verify_Purchase_Quantity_More_Than_Stock_Quantity()  {
        int numberItems = 15;
        tikiHomePage.navigate_Tiki_Url(Constants.TIKI_URL)
                .input_Keyword(TikiData.SEARCH_VALUE)
                .click_search_button();
        tikiHomePage.clickItemOnResultList(1);
        PageFactoryManager.get(ItemDetailPage.class)
                .clickQuantityButton()
                .inputQuantityOfItem(String.valueOf(numberItems))
                .clickAddItemIntoCart()
                .verifyShowErrorMessage();
    }
}
