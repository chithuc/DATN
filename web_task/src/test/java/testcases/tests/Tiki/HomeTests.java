package testcases.tests.Tiki;

import base.TestBase;
import core.constants.Constants;
import data.Tiki_data.TikiData;
import io.qameta.allure.*;
import model.Product;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.Tiki.TikiHomePage;
import utilities.ReportListener;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@Listeners({ReportListener.class})
@Epic("Automation test")
@Feature("Search item")
public class HomeTests extends TestBase {
    List<Product> products = new ArrayList<>();

    private TikiHomePage tikiHomePage;

    @BeforeTest
    public void initializePage()  {
        tikiHomePage = new TikiHomePage();
    }

    @Test(description = "Verify the results when search item: Happy case",groups = {"Home"})
    @Severity(SeverityLevel.BLOCKER)
    @Description("Search and verify the results")
    public void Test_03_Search_And_Verify_Data()  {
        products = tikiHomePage.navigate_Tiki_Url(Constants.TIKI_URL).verify_title(Constants.TIKI_TITLE)
                .input_Keyword(TikiData.SEARCH_VALUE)
                .click_search_button()
                .verifySizeResult()
                .get_searched_products();
        tikiHomePage.verify_Search_Result(products);
    }
}
