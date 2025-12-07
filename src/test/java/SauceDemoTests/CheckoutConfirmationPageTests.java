package SauceDemoTests;

import Base.BaseTest;
import SauceDemoPages.CartPage;
import SauceDemoPages.CheckoutConfirmationPage;
import SauceDemoPages.CheckoutPage;
import SauceDemoPages.LoginPage;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CheckoutConfirmationPageTests extends BaseTest {
    private CartPage cartPage;
    private CheckoutPage checkoutPage;
    private CheckoutConfirmationPage confirmationPage;

    @BeforeMethod
    public void setUpConfirmation() {
        new LoginPage(bot)
                .loginAsStandardUser();
    }

    private CheckoutConfirmationPage finishOrderWithBackpack() {
        new CartPage(bot)
           .addBackpackAndGoToCheckout();
        new CheckoutPage(bot)
            .completeCheckoutForm("First Name", "Last Name", "10000");
        bot.click(By.id("finish"));
        return confirmationPage;
    }

    @Test
    public void Confirmation_TC1_finishOrderWithItems() {
        // ???
        confirmationPage = finishOrderWithBackpack();
        Assert.assertEquals(
                new CheckoutConfirmationPage(bot).getCurrentUrl(),
                "https://www.saucedemo.com/checkout-complete.html");
        Assert.assertEquals(
                new CheckoutConfirmationPage(bot).getThankYouMessageText(),
                "Thank you for your order!");
    }

    @Test
    public void Confirmation_TC2_finishEmptyCartOrder() {
        new CartPage(bot)
           .goToCheckout();
        new CheckoutPage(bot)
            .completeCheckoutForm("First Name", "Last Name", "10000");
        bot.click(By.id("finish"));
        Assert.assertNotEquals(
                new CheckoutConfirmationPage(bot).getCurrentUrl(),
                "https://www.saucedemo.com/checkout-complete.html");
    }

    @Test(dependsOnMethods = "Confirmation_TC1_finishOrderWithItems")
    public void Confirmation_TC3_backToHomeAfterCompletion() {
        // ???
        confirmationPage = finishOrderWithBackpack();
        new CheckoutConfirmationPage(bot)
            .clickBackHomeButton();
        Assert.assertEquals(
                new CheckoutConfirmationPage(bot).getCurrentUrl(),
                "https://www.saucedemo.com/inventory.html");
    }
}
