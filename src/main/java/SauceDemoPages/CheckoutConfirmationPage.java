package SauceDemoPages;

import Engine.Bot;
import org.openqa.selenium.By;

public class CheckoutConfirmationPage {

    private final Bot bot;

    // Locators
    private final By thankYouMessage = By.xpath("//h2[@class='complete-header']");
    private final By backHomeButton = By.xpath("//button[text()='Back Home']");

    public CheckoutConfirmationPage(Bot bot) {
        this.bot = bot;
    }

    // Actions
    public HomePage clickBackHomeButton() {
        bot.click(backHomeButton);
        return new HomePage(bot);
    }

    // Assertions
    public String getThankYouMessageText() {
        return bot.getText(thankYouMessage);
    }

    public String getCurrentUrl() {
        return bot.getCurrentUrl();
    }

    public boolean assertOnCompletePage() {
        return getCurrentUrl().equals("https://www.saucedemo.com/checkout-complete.html");
    }

    public boolean assertOnStepTwoPage() {
        return getCurrentUrl().equals("https://www.saucedemo.com/checkout-step-two.html");
    }

    public boolean assertOnInventoryPage() {
        return getCurrentUrl().equals("https://www.saucedemo.com/inventory.html");
    }

    public boolean assertThankYouMessage(String expectedText) {
        return getThankYouMessageText().equals(expectedText);
    }
}
