package SauceDemoPages;

import Engine.Bot;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.List;

public class SortingFilterPage {
    private final Bot bot;

    //Locators
    private final By SortDropDown = By.className("product_sort_container");
    private final By productName =  By.className("inventory_item_name");

    //Constructor
    public SortingFilterPage(Bot bot)
    {this.bot = bot;}

    // Select option from dropdown
    public void selectSortingOption(String option) {
        WebElement drop = bot.find(SortDropDown);
        Select select = new Select(drop);
        select.selectByVisibleText(option);
    }

    // Get product names as Strings
    public List<String> getProductNames() {
        List<WebElement> products = bot.findAll(productName);
        List<String> names = new ArrayList<>();
        for (WebElement product : products) {
            names.add(product.getText());
        }
        return names;
    }
}
