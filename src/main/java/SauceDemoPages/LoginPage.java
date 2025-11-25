package SauceDemoPages;

import Engine.Bot.ActionsBot;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

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

    //  Validation Methods
    // Successful Login Validations

    public void validateSuccessfulLogin() {
        Assert.assertTrue(actionsBot.isElementDisplayed(inventoryContainer),
                "Products page is not displayed after login!");
    }

    public LoginPage validatePageTitle(String expectedTitle) {
        Assert.assertEquals(actionsBot.getPageTitle(), expectedTitle,
                "Page title does not match!");
        return this;
    }

    public LoginPage validateUrlContains(String expectedUrlPart) {
        Assert.assertTrue(actionsBot.getCurrentUrl().contains(expectedUrlPart),
                "URL does not contain expected part: " + expectedUrlPart);
        return this;
    }


    public void validateProductsPageDisplayed() {
        Assert.assertTrue(actionsBot.isElementDisplayed(productTitle),
                "Products page title is not displayed!");
    }

    // Error Message Validations
    public void validateErrorMessage(String expectedError) {
        String actualError = actionsBot.getText(errorMessage);
        Assert.assertEquals(actualError, expectedError,
                "Error message does not match!");
    }

    public WebDriver getDriver() {
        return driver;
    }
}