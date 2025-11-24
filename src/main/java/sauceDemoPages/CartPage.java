
package SauceDemoPages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import java.time.Duration;

public class CartPage {

    // Locators
    private By cartLink = By.id("shopping_cart_container");
    private By cartItems = By.className("cart_item");
    private By firstItemName = By.xpath("(//div[@class='inventory_item_name'])[1]");
    private By removeFirstItemButton = By.xpath("(//button[@class='btn btn_secondary btn_small cart_button'])[1]");
    private By checkoutButton = By.id("checkout");
    private By cartBadgeNumber = By.xpath("//span[@class='shopping_cart_badge']");

    // Driver and Wait
    private WebDriver driver;
    private Wait<WebDriver> wait;

    // Constructor
    public CartPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(10))
                .pollingEvery(Duration.ofMillis(200));
    }

    // Actions
    public void openCartLink() {
        driver.findElement(cartLink).click();
    }

    public boolean isCartPageOpened() {
        return driver.getCurrentUrl().contains("cart.html");
    }

    public int countCartItems() {
        return driver.findElements(cartItems).size();
    }

    public String getFirstItemName() {
        return driver.findElement(firstItemName).getText();
    }

    public void removeFirstItemFromCart() {
        driver.findElement(removeFirstItemButton).click();
    }

    public void clickCheckoutButton() {
        driver.findElement(checkoutButton).click();
    }

    public int getCartBadgeNumber() {

        return Integer.parseInt(driver.findElement(cartBadgeNumber).getText());
    }
}