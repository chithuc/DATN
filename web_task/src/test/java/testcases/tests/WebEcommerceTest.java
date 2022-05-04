//package testcases.tests;
//
//import core.constants.Constants;
//import data.Tiki_data.TikiData;
//import model.Product;
//import org.testng.annotations.BeforeTest;
//import org.testng.annotations.Test;
//import pages.CommonPage;
//import pages.Tiki.TikiHomePage;
//import pages.VatGia.VatGiaPage;
//import testcases.base.TestBase;
//
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.List;
//
//
//public class WebEcommerceTest extends TestBase {
//
//    List<Product> products = new ArrayList<>();
//
//    private CommonPage commonPage;
//    private TikiHomePage tikiHomePage;
//    private VatGiaPage vatGiaPage;
//
//    @BeforeTest
//    public void initializePage()  {
//        commonPage = new CommonPage();
//        tikiHomePage = new TikiHomePage();
//        vatGiaPage = new VatGiaPage();
//    }
//
//    @Test(description = "Search and sort data from websites")
//    public void Search_And_Verify_Data() throws IOException {
//        tikiHomePage.navigate_Tiki_Url(Constants.TIKI_URL).verify_title(Constants.TIKI_TITLE)
//                .input_Keyword(TikiData.SEARCH_VALUE)
//                .click_search_button().verifySizeResult()
//                .verify_Search_Result(products);
//        products = tikiHomePage.get_searched_products();
//
//        vatGiaPage.navigate_VatGia_Url(Constants.VATGIA_URL).verify_title(Constants.VATGIA_TITLE)
//                .input_Keyword(TikiData.SEARCH_VALUE)
//                .click_search_button()
//                .verify_Search_Result();
//        products.addAll(vatGiaPage.get_searched_products());
//        commonPage.display_search_results(products);
//    }
//}
