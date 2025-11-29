package SauceDemoPages;

import Engine.Bot.Bot;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;

public class HomePage {

    private final Bot bot;
    private final By cartBadge = By.xpath("//span[@class='shopping_cart_badge']");

    public HomePage(Bot bot) {
        this.bot = bot;
    }

    public String getCartBadgeCount() {
        try {
            return bot.getText(cartBadge);
        } catch (Exception e) {
            return "";
        }
    }

    public HomePage scrollToBottom() {
        ((JavascriptExecutor) bot.getDriver())
                .executeScript("window.scrollTo(0, document.body.scrollHeight)");
        return this;
    }

    public HomePage scrollToTop() {
        ((JavascriptExecutor) bot.getDriver())
                .executeScript("window.scrollTo(0, 0)");
        return this;
    }
}