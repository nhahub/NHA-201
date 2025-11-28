
package SauceDemoPages;

import Engine.Bot.ActionsBot;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {

    // Variables

    private final WebDriver driver;
    private final ActionsBot actionsBot;

    // Locators

    private final By usernameField = By.id("user-name");
    private final By passwordField = By.id("password");
    private final By loginButton = By.id("login-button");
    private final By errorMessage = By.cssSelector("[data-test='error']");
    private final By inventoryContainer = By.id("inventory_container");
    private final By productTitle = By.className("title");

    // Constructor

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.actionsBot = new ActionsBot(driver);
    }

    // Navigation Actions

    public LoginPage navigateToLoginPage() {
        actionsBot.navigateTo("https://www.saucedemo.com/");
        return this;
    }

    // Login Actions

    public LoginPage enterUsername(String username) {
        actionsBot.typing(usernameField, username);
        return this;
    }

    public LoginPage enterPassword(String password) {
        actionsBot.typing(passwordField, password);
        return this;
    }

    public LoginPage clickLoginButton() {
        actionsBot.clicking(loginButton);
        return this;
    }

    //  Validation Helpers (no TestNG here - just state)

    // Successful Login Helpers

    public boolean isInventoryContainerDisplayed() {
        return actionsBot.isElementDisplayed(inventoryContainer);
    }

    public String getPageTitle() {
        return actionsBot.getPageTitle();
    }

    public String getCurrentUrl() {
        return actionsBot.getCurrentUrl();
    }

    public boolean isProductsPageTitleDisplayed() {
        return actionsBot.isElementDisplayed(productTitle);
    }

    // Error Message Helper

    public String getErrorMessage() {
        return actionsBot.getText(errorMessage);
    }

    public WebDriver getDriver() {
        return driver;
    }
}