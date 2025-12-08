package SauceDemoPages;

import Engine.Bot;
import org.openqa.selenium.By;

public class CheckoutPage {

    private final Bot bot;

    // Locators
    private final By firstName = By.id("first-name");
    private final By lastName = By.id("last-name");
    private final By zipCode = By.id("postal-code");
    private final By continueButton = By.id("continue");
    private final By cancelButton = By.id("cancel");
    private final By finishButton = By.id("finish");
    private final By errorMessage = By.xpath("//h3[@data-test='error']");

    public CheckoutPage(Bot bot) {
        this.bot = bot;
    }

    // Actions
    public CheckoutPage fillFirstName(String userFirstName) {
        bot.type(firstName, userFirstName);
        return this;
    }

    public CheckoutPage fillLastName(String userLastName) {
        bot.type(lastName, userLastName);
        return this;
    }

    public CheckoutPage fillZipCode(String userZipCode) {
        bot.type(zipCode, userZipCode);
        return this;
    }

    public CheckoutPage clickContinue() {
        bot.click(continueButton);
        return this;
    }

    public CheckoutPage completeCheckoutForm(String userFirstName, String userLastName, String userZipCode) {
        return fillFirstName(userFirstName)
                .fillLastName(userLastName)
                .fillZipCode(userZipCode)
                .clickContinue();
    }

    // Assertions
    public String getCurrentUrl() {
        return bot.getCurrentUrl();
    }

    public String getErrorMessageText() {
        return bot.getText(errorMessage);
    }

    public boolean assertOnStepTwoPage() {
        return getCurrentUrl().equals("https://www.saucedemo.com/checkout-step-two.html");
    }

    public boolean assertNotOnStepTwoPage() {
        return !getCurrentUrl().equals("https://www.saucedemo.com/checkout-step-two.html");
    }

    public boolean assertErrorContains(String text) {
        String error = getErrorMessageText();
        return error != null && error.contains(text);
    }

    // Navigation
    public CartPage clickCancel() {
        bot.click(cancelButton);
        return new CartPage(bot);
    }

    public CheckoutConfirmationPage clickFinish() {
        bot.click(finishButton);
        return new CheckoutConfirmationPage(bot);
    }
}
