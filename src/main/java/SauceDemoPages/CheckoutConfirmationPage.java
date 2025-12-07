package SauceDemoPages;

import Engine.Bot;
import org.openqa.selenium.By;

public class CheckoutConfirmationPage {

    private final Bot bot;

    // Locators
    private final By thankYouMessage = By.xpath("//h2[@class='complete-header']");
    private final By backHomeButton = By.xpath("//button[text()='Back Home']");

    // Constructor
    public CheckoutConfirmationPage(Bot bot) {
        this.bot = bot;
    }

    // Actions
    public HomePage clickBackHomeButton() {
        bot.click(backHomeButton);
        return new HomePage(bot);
    }

    // Checks for assertions
    public String getThankYouMessageText() {
        return bot.getText(thankYouMessage);
    }

    public String getCurrentUrl() {
        return bot.getCurrentUrl();
    }
}
