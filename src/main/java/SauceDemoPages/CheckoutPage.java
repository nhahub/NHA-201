package SauceDemoPages;

import Engine.Bot.ActionsBot;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckoutPage {

    // Locators
    private final By firstName = By.id("first-name");
    private final By lastName = By.id("last-name");
    private final By zipCode = By.id("postal-code");
    private final By continueButton = By.id("continue");

    // Driver and ActionsBot
    private final WebDriver driver;
    private final ActionsBot actionsBot;

    // Constructor
    public CheckoutPage(WebDriver driver) {
        this.driver = driver;
        this.actionsBot = new ActionsBot(driver);
    }

    // Actions
    public void fillFirstName(String userFirstName) {
        actionsBot.typing(firstName, userFirstName);
    }

    public void fillLastName(String userLastName) {
        actionsBot.typing(lastName, userLastName);
    }

    public void fillZipCode(String userZipCode) {
        actionsBot.typing(zipCode, userZipCode);
    }

    public void clickContinue() {
        actionsBot.clicking(continueButton);
    }
}