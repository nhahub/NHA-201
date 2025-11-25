package SauceDemoPages;

import Engine.Bot.ActionsBot;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CartPage {

    // Locators
    private final By cartLink = By.id("shopping_cart_container");
    private final By cartItems = By.className("cart_item");
    private final By firstItemName = By.xpath("(//div[@class='inventory_item_name'])[1]");
    private final By removeFirstItemButton = By.xpath("(//button[@class='btn btn_secondary btn_small cart_button'])[1]");
    private final By checkoutButton = By.id("checkout");
    private final By cartBadgeNumber = By.xpath("//span[@class='shopping_cart_badge']");

    // Driver and ActionsBot
    private final WebDriver driver;
    private final ActionsBot actionsBot;

    // Constructor
    public CartPage(WebDriver driver) {
        this.driver = driver;
        this.actionsBot = new ActionsBot(driver);
    }

    // Actions
    public void openCartLink() {
        actionsBot.clicking(cartLink);
    }

    public boolean isCartPageOpened() {
        return actionsBot.getCurrentUrl().contains("cart.html");
    }

    public int countCartItems() {
        return driver.findElements(cartItems).size();
    }

    public String getFirstItemName() {
        return actionsBot.getText(firstItemName);
    }

    public void removeFirstItemFromCart() {
        actionsBot.clicking(removeFirstItemButton);
    }

    public void clickCheckoutButton() {
        actionsBot.clicking(checkoutButton);
    }

    public int getCartBadgeNumber() {
        String badgeText = actionsBot.getText(cartBadgeNumber);
        return Integer.parseInt(badgeText);
    }
}