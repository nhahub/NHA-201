package SauceDemoTests;

import Base.BaseTest;
import SauceDemoPages.CartPage;
import SauceDemoPages.CheckoutConfirmationPage;
import SauceDemoPages.CheckoutPage;
import SauceDemoPages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CheckoutConfirmationPageTests extends BaseTest {

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
                .completeCheckoutForm("First Name", "Last Name", "10000")
                .clickFinish();

        return new CheckoutConfirmationPage(bot);
    }

    @Test
    public void Confirmation_TC1_finishOrderWithItems() {
        confirmationPage = finishOrderWithBackpack();

        Assert.assertEquals(
                confirmationPage.getCurrentUrl(),
                "https://www.saucedemo.com/checkout-complete.html"
        );
        Assert.assertEquals(
                confirmationPage.getThankYouMessageText(),
                "Thank you for your order!"
        );
    }

    @Test
    public void Confirmation_TC2_finishEmptyCartOrder() {
        new CartPage(bot)
                .goToCheckout();

        new CheckoutPage(bot)
                .completeCheckoutForm("First Name", "Last Name", "10000")
                .clickFinish();

        confirmationPage = new CheckoutConfirmationPage(bot);

        Assert.assertNotEquals(
                confirmationPage.getCurrentUrl(),
                "https://www.saucedemo.com/checkout-complete.html"
        );
    }

    @Test
    public void Confirmation_TC3_backToHomeAfterCompletion() {
        confirmationPage = finishOrderWithBackpack();

        confirmationPage
                .clickBackHomeButton();

        Assert.assertEquals(
                confirmationPage.getCurrentUrl(),
                "https://www.saucedemo.com/inventory.html"
        );
    }
}
