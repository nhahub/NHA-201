package SauceDemoPages;

import Engine.Bot.Bot;
import org.openqa.selenium.By;

public class CartPage {

    private final Bot bot;

    private final By cartLink = By.id("shopping_cart_container");
    private final By cartItems = By.className("cart_item");
    private final By firstItemName = By.xpath("(//div[@class='inventory_item_name'])[1]");
    private final By removeFirstItemButton = By.xpath("(//button[@class='btn btn_secondary btn_small cart_button'])[1]");
    private final By checkoutButton = By.id("checkout");
    private final By cartBadgeNumber = By.xpath("//span[@class='shopping_cart_badge']");

    public CartPage(Bot bot) {
        this.bot = bot;
    }

    public CartPage openCartLink() {
        bot.click(cartLink);
        return this;
    }

    public boolean isCartPageOpened() {
        return bot.getCurrentUrl().contains("cart.html");
    }

    public int countCartItems() {
        return bot.getDriver().findElements(cartItems).size();
    }

    public String getFirstItemName() {
        return bot.getText(firstItemName);
    }

    public CartPage removeFirstItemFromCart() {
        bot.click(removeFirstItemButton);
        return this;
    }

    public CartPage clickCheckoutButton() {
        bot.click(checkoutButton);
        return this;
    }

    public int getCartBadgeNumber() {
        String badgeText = bot.getText(cartBadgeNumber);
        return Integer.parseInt(badgeText);
    }
}