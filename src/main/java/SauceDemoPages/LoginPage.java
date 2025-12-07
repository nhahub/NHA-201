package SauceDemoPages;

import Engine.Bot;
import org.openqa.selenium.By;

public class LoginPage {

    private final Bot bot;

    private final By usernameField = By.id("user-name");
    private final By passwordField = By.id("password");
    private final By loginButton = By.id("login-button");
    private final By errorMessage = By.cssSelector("[data-test='error']");
    private final By inventoryContainer = By.id("inventory_container");
    private final By productTitle = By.className("title");

    public LoginPage(Bot bot) {
        this.bot = bot;
    }

    public LoginPage navigateToLoginPage() {
        bot.navigateTo("https://www.saucedemo.com/");
        return this;
    }

    public LoginPage enterUsername(String username) {
        bot.type(usernameField, username);
        return this;
    }

    public LoginPage enterPassword(String password) {
        bot.type(passwordField, password);
        return this;
    }
    //TODO: Navigate to HomePage
    public HomePage clickLoginButton() {
        bot.click(loginButton);
        return new HomePage(bot);
    }

    public String getPageTitle() {
        return bot.getPageTitle();
    }

    public String getCurrentUrl() {
        return bot.getCurrentUrl();
    }

    public boolean isProductsPageTitleDisplayed() {
        return bot.isElementDisplayed(productTitle);
    }

    public String getErrorMessage() {
        return bot.getText(errorMessage);
    }
    //TODO: Assertion
    // AssertEquals use String Methods
    // AssertTrue use boolean Methods
    public boolean assertLoginTc(String expectedTitle) {
        return bot.getPageTitle().equals(expectedTitle);
    }

    public String ActualLoginTitle() {
        return bot.getPageTitle();
    }

    public boolean assertErrorMessage(String expectedMessage) {
        return getErrorMessage().equals(expectedMessage);
    }
    //TODO: login by user
    public void loginAsStandardUser() {
        navigateToLoginPage()
                .enterUsername("standard_user")
                .enterPassword("secret_sauce")
                .clickLoginButton();
    }
}
