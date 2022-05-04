package pages;

import core.constants.Constants;
import io.qameta.allure.Step;
import model.Product;
import pages.base.PageObjectBase;
import utilities.Utils;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class CommonPage extends PageObjectBase {

    public CommonPage() {
        super();
    }

    @Step("Display search results")
    public CommonPage display_search_results(List<Product> products) throws IOException {
        String path = String.format(Constants.REPORT_FOLDER_NAME + "/Report-%s.txt", Utils.getTimeNow("yyyy-MM-dd-HHmmss"));
        StringBuilder exportData = new StringBuilder();
        if (products != null) {
            List<Product> sorted = products.stream().sorted().collect(Collectors.toList());
            for (Product product : sorted) {
                exportData.append(product.toString()).append("\n");
            }
            Utils.writeTxtFile(path, exportData.toString());
        } else {
            Utils.writeTxtFile(path, "No data found!");
        }
        return this;
    }

}
