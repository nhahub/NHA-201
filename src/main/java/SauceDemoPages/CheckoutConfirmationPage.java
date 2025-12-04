package SauceDemoPages;

import Engine.Bot;
import org.openqa.selenium.By;

public class CheckoutConfirmationPage {

    private final Bot bot;
    private final By thankYouMessage = By.xpath("//h2[@class='complete-header']");
    private final By backHomeButton = By.xpath("//button[text()='Back Home']");

    public CheckoutConfirmationPage(Bot bot) {
        this.bot = bot;
    }

    public CheckoutConfirmationPage clickBackHomeButton() {
        bot.click(backHomeButton);
        return this;
    }

    public String getThankYouMessageText() {
        return bot.getText(thankYouMessage);
    }

    public String getCurrentUrl() {
        return bot.getCurrentUrl();
    }
}
