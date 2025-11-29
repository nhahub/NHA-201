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
        loginPage.navigateToLoginPage()
                .enterUsername("standard_user")
                .enterPassword("secret_sauce")
                .clickLoginButton();
    }

    @Test
    public void Confirmation_TC1_finishOrderWithItems() {
        bot.getDriver().findElement(By.id("add-to-cart-sauce-labs-backpack")).click();

        cartPage = new CartPage(bot);
        cartPage.openCartLink().clickCheckoutButton();

        checkoutPage = new CheckoutPage(bot);
        checkoutPage.fillFirstName("First Name")
                .fillLastName("Last Name")
                .fillZipCode("10000")
                .clickContinue();

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
    public void Confirmation_TC2_backToHomeAfterCompletion() {
        bot.getDriver().findElement(By.id("add-to-cart-sauce-labs-backpack")).click();

        cartPage = new CartPage(bot);
        cartPage.openCartLink().clickCheckoutButton();

        checkoutPage = new CheckoutPage(bot);
        checkoutPage.fillFirstName("First Name")
                .fillLastName("Last Name")
                .fillZipCode("10000")
                .clickContinue();

        bot.click(By.id("finish"));
        confirmationPage = new CheckoutConfirmationPage(bot);

        bot.click(By.id("back-to-products"));

        Assert.assertEquals(
                bot.getCurrentUrl(),
                "https://www.saucedemo.com/inventory.html");
    }
}