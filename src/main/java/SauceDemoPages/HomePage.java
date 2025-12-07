package SauceDemoPages;

import Engine.Bot;
import org.openqa.selenium.By;

public class HomePage {

    private final Bot bot;

    private final By cartBadge = By.xpath("//span[@class='shopping_cart_badge']");
    private final By addBackpackBtn = By.id("add-to-cart-sauce-labs-backpack");
    private final By addBikeLight = By.id("add-to-cart-sauce-labs-bike-light");
    private final By addBoltShirt = By.id("add-to-cart-sauce-labs-bolt-t-shirt");
    private final By removeBackpack = By.id("remove-sauce-labs-backpack");
    private final By removeBikeLight = By.id("remove-sauce-labs-bike-light");
    private final By removeBoltShirt = By.id("remove-sauce-labs-bolt-t-shirt");
    private final By footer = By.className("footer");
    private final By header = By.className("app_logo");
    private final By menuButton = By.xpath("//button[@id='react-burger-menu-btn']");
    private final By sideMenu = By.id("menu_button_container");
    private final By cartIcon = By.id("shopping_cart_container");

    public HomePage(Bot bot) {
        this.bot = bot;
    }
    public HomePage scrollToBottom() {
        bot.scrollToElement(footer);
        return this;
    }

    public void scrollToTop() {
        bot.scrollToElement(header);
    }

    public HomePage addBackpackToCart() {
        bot.click(addBackpackBtn);
        return this;

    }
    public HomePage addBikeLightToCart() {
        bot.click(addBikeLight);
        return this;
    }

    public HomePage addBoltShirtToCart() {
        bot.click(addBoltShirt);
        return this;
    }

    public HomePage removeBackpack() {
        bot.click(removeBackpack);
        return this;
    }

    public HomePage removeBikeLight() {
        bot.click(removeBikeLight);
        return this;
    }

    public void removeBoltShirt() {
        bot.click(removeBoltShirt);

    }
    public void clickMenuButton() {
        bot.click(menuButton);
    }

    public boolean isMenuOpen() {
        return bot.isElementDisplayed(sideMenu);
    }
    public String getCartBadgeCount() {
        if (bot.isPresent(cartBadge)) {
            return bot.getText(cartBadge);
        }
        return "";
    }
    //TODO: Navigate to CartPage
    public CartPage openCartIcon() {
        bot.click(cartIcon);
        return new CartPage(bot);
    }
}
