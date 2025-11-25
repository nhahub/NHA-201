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
        return driver.findElement(thankYouMessage).getText();
    }

    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }
}
