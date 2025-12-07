package SauceDemoPages;

import Engine.Bot;
import org.openqa.selenium.By;

public class CheckoutPage {

    private final Bot bot;

    private final By firstName = By.id("first-name");
    private final By lastName = By.id("last-name");
    private final By zipCode = By.id("postal-code");
    private final By continueButton = By.id("continue");
    private final By cancelButton = By.id("cancel");
    private final By finishButton = By.id("finish");
    private final By errorMessage = By.cssSelector("h3[data-test='error']");


    public CheckoutPage(Bot bot) {
        this.bot = bot;
    }

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
    public String getCurrentUrl() {
        return bot.getCurrentUrl();
    }
    //TODO: Back to CartPage
    public CartPage clickCancel() {
        bot.click(cancelButton);
        return new CartPage(bot);
    }
    //TODO: Navigate to CheckoutConfirmationPage
    public CheckoutConfirmationPage clickFinish(){
        bot.click(finishButton);
        return new CheckoutConfirmationPage(bot);
    }
}
