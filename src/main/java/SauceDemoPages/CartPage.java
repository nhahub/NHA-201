package SauceDemoPages;

import Engine.Bot;
import org.openqa.selenium.By;

public class CartPage {

    private final Bot bot;

    // Locators
    private final By cartIcon = By.id("shopping_cart_container");
    private final By cartItems = By.className("cart_item");
    private final By firstItemName = By.xpath("(//div[@class='inventory_item_name'])[1]");
    private final By removeFirstItemButton = By.xpath("(//button[@class='btn btn_secondary btn_small cart_button'])[1]");
    private final By checkoutButton = By.id("checkout");
    private final By cartBadgeNumber = By.xpath("//span[@class='shopping_cart_badge']");
    private final By addBackpackButton = By.id("add-to-cart-sauce-labs-backpack");
    private final By addBikeLightButton = By.id("add-to-cart-sauce-labs-bike-light");
    private final By addBoltTShirtButton = By.id("add-to-cart-sauce-labs-bolt-t-shirt");

    // Constructor
    public CartPage(Bot bot) {
        this.bot = bot;
    }

    // Actions
    public CartPage openCartIcon() {
        bot.click(cartIcon);
        return this;
    }

    public CartPage removeFirstItemFromCart() {
        bot.click(removeFirstItemButton);
        return this;
    }

    public CartPage addBackpack() {
        bot.click(addBackpackButton);
        return this;
    }

    public CartPage addMultipleItems() {
        bot.click(addBackpackButton);
        bot.click(addBikeLightButton);
        bot.click(addBoltTShirtButton);
        return this;
    }

    public CartPage addBackpackAndGoToCheckout() {
        addBackpack();
        return goToCheckout();
    }

    public CartPage goToCheckout() {
        openCartIcon();
        clickCheckoutButton();
        return this;
    }

    // Checks for assertions
    public boolean isCartPageOpened() {
        return bot.getCurrentUrl().contains("cart.html");
    }

    public int countCartItems() {
        return bot.countElements(cartItems);
    }

    public String getFirstItemName() {
        return bot.getText(firstItemName);
    }

    public int getCartBadgeNumber() {
        String badgeText = bot.getText(cartBadgeNumber);
        if (badgeText == null || badgeText.isEmpty()) {
            return 0;
        }
        return Integer.parseInt(badgeText);
    }

    public String getCurrentUrl() {
        return bot.getCurrentUrl();
    }

    // Navigation
    public CheckoutPage clickCheckoutButton() {
        bot.click(checkoutButton);
        return new CheckoutPage(bot);
    }
}
