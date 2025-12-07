package SauceDemoPages;

import Engine.Bot;
import org.openqa.selenium.By;

public class HomePage {

    private final Bot Bot;

    // Locators
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

    public HomePage(Bot bot) {
        this.Bot = bot;
    }
    public HomePage scrollToBottom() {
        Bot.scrollToElement(footer);
        return this;
    }

    public void scrollToTop() {
        Bot.scrollToElement(header);
    }

    public HomePage addBackpackToCart() {
        Bot.click(addBackpackBtn);
        return this;

    }
    public HomePage addBikeLightToCart() {
        Bot.click(addBikeLight);
        return this;
    }

    public void addBoltShirtToCart() {
        Bot.click(addBoltShirt);

    }
    // Remove products
    public HomePage removeBackpack() {
        Bot.click(removeBackpack);
        return this;
    }

    public HomePage removeBikeLight() {
        Bot.click(removeBikeLight);
        return this;
    }

    public void removeBoltShirt() {
        Bot.click(removeBoltShirt);

    }
    public void clickMenuButton() {
        Bot.click(menuButton);
    }

    public boolean isMenuOpen() {
        return Bot.isElementDisplayed(sideMenu);
    }
    public String getCartBadgeCount() {
        if (Bot.isPresent(cartBadge)) {
            return Bot.getText(cartBadge);
        }
        return "";
    }
}
