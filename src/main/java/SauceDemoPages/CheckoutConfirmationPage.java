
package SauceDemoPages;

import Engine.Bot.ActionsBot;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckoutConfirmationPage {

    // Locator
    private final By thankYouMessage = By.className("complete-header");

    // Driver and ActionsBot
    private final WebDriver driver;
    private final ActionsBot actionsBot;

    // Constructor
    public CheckoutConfirmationPage(WebDriver driver) {
        this.driver = driver;
        this.actionsBot = new ActionsBot(driver);
    }

    // Action: Get success message text
    public String getThankYouMessageText() {
        // Use ActionsBot to read thank you message (with wait + scroll)
        return actionsBot.getText(thankYouMessage);
    }

    public String getCurrentUrl() {
        // Use ActionsBot for consistent URL retrieval
        return actionsBot.getCurrentUrl();
    }
}