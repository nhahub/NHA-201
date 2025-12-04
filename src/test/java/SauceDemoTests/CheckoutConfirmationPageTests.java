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
<<<<<<< HEAD
=======
        new CartPage(bot)
                .addBackpackAndGoToCheckout();
        new CheckoutPage(bot)
                .completeCheckoutForm("First Name", "Last Name", "10000");
        confirmationPage = new CheckoutConfirmationPage(bot);
        bot.click(By.id("finish"));
        return confirmationPage;
>>>>>>> 5bbfc0d7c87920da2377fd6a844f3ffef10ab2d5
        cartPage = new CartPage(bot);
        checkoutPage = new CheckoutPage(bot);

        cartPage.addBackpackAndGoToCheckout();
        checkoutPage.completeCheckoutForm("First Name", "Last Name", "10000");

        bot.click(By.id("finish"));
        confirmationPage = new CheckoutConfirmationPage(bot);
        return confirmationPage;
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
<<<<<<< HEAD
        cartPage = new CartPage(bot);
        checkoutPage = new CheckoutPage(bot);

        cartPage.goToCheckout();
        checkoutPage.completeCheckoutForm("First Name", "Last Name", "10000");

=======
        new CartPage(bot)
                .goToCheckout();
        new CheckoutPage(bot)
                .completeCheckoutForm("First Name", "Last Name", "10000");
>>>>>>> 5bbfc0d7c87920da2377fd6a844f3ffef10ab2d5
        bot.click(By.id("finish"));
        confirmationPage = new CheckoutConfirmationPage(bot);

        Assert.assertNotEquals(
                confirmationPage.getCurrentUrl(),
                "https://www.saucedemo.com/checkout-complete.html");
    }

    @Test(dependsOnMethods = "Confirmation_TC1_finishOrderWithItems")
    public void Confirmation_TC3_backToHomeAfterCompletion() {
        confirmationPage = finishOrderWithBackpack();
<<<<<<< HEAD

        confirmationPage.clickBackHomeButton();

=======
        confirmationPage.clickBackHomeButton();
        confirmationPage.clickBackHomeButton();
>>>>>>> 5bbfc0d7c87920da2377fd6a844f3ffef10ab2d5
        Assert.assertEquals(
                bot.getCurrentUrl(),
                "https://www.saucedemo.com/inventory.html");
    }
}
