package SauceDemoTests;

import SauceDemoPages.CartPage;
import SauceDemoPages.CheckoutConfirmationPage;
import SauceDemoPages.CheckoutPage;
import SauceDemoPages.LoginPage;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CheckoutConfirmationPageTests extends BaseTest {

    private LoginPage loginPage;
    private CartPage cartPage;
    private CheckoutPage checkoutPage;
    private CheckoutConfirmationPage confirmationPage;

    @BeforeMethod
    public void setUpConfirmation() {
        loginPage = new LoginPage(bot);
        loginPage.loginAsStandardUser();
    }

    private CheckoutConfirmationPage finishOrderWithBackpack() {
        bot.getDriver().findElement(By.id("add-to-cart-sauce-labs-backpack")).click();

        cartPage = new CartPage(bot);
        cartPage.goToCheckout();

        checkoutPage = new CheckoutPage(bot);
        checkoutPage.completeCheckoutForm("First Name", "Last Name", "10000");

        bot.click(By.id("finish"));
        return new CheckoutConfirmationPage(bot);
    }

    @Test
    public void Confirmation_TC1_finishOrderWithItems() {
        confirmationPage = finishOrderWithBackpack();

        Assert.assertEquals(
                confirmationPage.getCurrentUrl(),
                "https://www.saucedemo.com/checkout-complete.html");
        Assert.assertEquals(
                confirmationPage.getThankYouMessageText(),
                "Thank you for your order!");
    }

    @Test
    public void Confirmation_TC2_finishEmptyCartOrder() {
        cartPage = new CartPage(bot);
        cartPage.goToCheckout();

        checkoutPage = new CheckoutPage(bot);
        checkoutPage.completeCheckoutForm("First Name", "Last Name", "10000");

        bot.click(By.id("finish"));
        confirmationPage = new CheckoutConfirmationPage(bot);

        Assert.assertEquals(
                confirmationPage.getCurrentUrl(),
                "https://www.saucedemo.com/checkout-complete.html");
        Assert.assertEquals(
                confirmationPage.getThankYouMessageText(),
                "Thank you for your order!");
    }

    @Test(dependsOnMethods = "Confirmation_TC1_finishOrderWithItems")
    public void Confirmation_TC3_backToHomeAfterCompletion() {
        confirmationPage = finishOrderWithBackpack();

        bot.click(By.id("back-to-products"));

        Assert.assertEquals(
                bot.getCurrentUrl(),
                "https://www.saucedemo.com/inventory.html");
    }
}
