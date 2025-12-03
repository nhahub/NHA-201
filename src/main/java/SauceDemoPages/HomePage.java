package SauceDemoPages;

import Engine.Bot;
import org.openqa.selenium.By;

public class HomePage {

    private final Bot Bot;

    // Locators
    private final By cartBadge = By.className("shopping_cart_badge");
    private final By addBackpackBtn = By.id("add-to-cart-sauce-labs-backpack");
    private final By footer = By.className("footer");
    private final By header = By.className("app_logo");

    // Constructor
    public HomePage(Bot bot) {
        this.Bot = bot;
    }

    // Scroll Down
    public HomePage scrollToBottom() {
        Bot.scrollToElement(footer);
        return this;
    }

    // Scroll Up
    public void scrollToTop() {
        Bot.scrollToElement(header);
    }

    // Add item to cart
    public void addBackpackToCart() {
        Bot.click(addBackpackBtn);
    }

    // Read cart badge
    public String getCartBadgeCount() {
        return Bot.getText(cartBadge);
    }
}
