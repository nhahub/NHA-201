package SauceDemoPages;
import Engine.Bot;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import java.util.List;

public class SortingFilterPage {
    private final Bot bot;

    //Locators
    private final By SortDropDown = By.className("product_sort_container");
    private final By productName =  By.className("inventory_item_name");

    public SortingFilterPage(Bot bot) {
        this.bot = bot;
    }

    public void selectSortingOption(String option) {
        WebElement drop = bot.findElement(SortDropDown);
        new Select(drop).selectByVisibleText(option);
    }

    // Get product names using Streams
    public List<String> getProductNames() {
        return bot.findElements(productName)
                .stream()
                .map(WebElement::getText)
                .toList();
    }

    // Assert A → Z
    public void assertSortedAZ(List<String> products) {
        for (int i = 0; i < products.size() - 1; i++) {
            Assert.assertTrue(products.get(i).compareTo(products.get(i + 1)) <= 0,
                    "Products not sorted A → Z");
        }
    }

    // Assert Z → A
    public void assertSortedZA(List<String> products) {
        for (int i = 0; i < products.size() - 1; i++) {
            Assert.assertTrue(products.get(i).compareTo(products.get(i + 1)) >= 0,
                    "Products not sorted Z → A");
        }
    }
}