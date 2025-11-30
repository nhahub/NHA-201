package SauceDemoPages;

import Engine.Bot.Bot;
import org.openqa.selenium.By;

public class CheckoutPage {

    private final Bot bot;

    private final By firstName = By.id("first-name");
    private final By lastName = By.id("last-name");
    private final By zipCode = By.id("postal-code");
    private final By continueButton = By.id("continue");

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
}
