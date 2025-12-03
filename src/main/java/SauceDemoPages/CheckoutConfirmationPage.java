package SauceDemoPages;

import Engine.Bot;
import org.openqa.selenium.By;

public class CheckoutConfirmationPage {

    private final Bot bot;
    private final By thankYouMessage = By.className("complete-header");

    public CheckoutConfirmationPage(Bot bot) {
        this.bot = bot;
    }

    public String getThankYouMessageText() {
        return bot.getText(thankYouMessage);
    }

    public String getCurrentUrl() {
        return bot.getCurrentUrl();
    }
}