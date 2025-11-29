package SauceDemoTests;

import SauceDemoPages.CartPage;
import SauceDemoPages.LoginPage;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CartPageTests extends BaseTest {

    private LoginPage loginPage;
    private CartPage cartPage;

    @BeforeMethod
    public void setUpCart() {
        loginPage = new LoginPage(bot);
        loginPage.navigateToLoginPage()
                .enterUsername("standard_user")
                .enterPassword("secret_sauce")
                .clickLoginButton();

        cartPage = new CartPage(bot);
    }

    @Test
    public void Cart_Tc1_openCartAfterAddingItems() {
        bot.getDriver().findElement(By.id("add-to-cart-sauce-labs-backpack")).click();
        cartPage.openCartLink();

        Assert.assertTrue(cartPage.isCartPageOpened());
        Assert.assertEquals(cartPage.countCartItems(), 1);
        Assert.assertEquals(cartPage.getFirstItemName(), "Sauce Labs Backpack");
        Assert.assertEquals(bot.getCurrentUrl(), "https://www.saucedemo.com/cart.html");
    }

    @Test(dependsOnMethods = "Cart_Tc1_openCartAfterAddingItems")
    public void Cart_Tc2_removeItemFromCart() {
        bot.getDriver().findElement(By.id("add-to-cart-sauce-labs-backpack")).click();
        cartPage.openCartLink();
        int itemsBefore = cartPage.countCartItems();

        cartPage.removeFirstItemFromCart();

        int itemsAfter = cartPage.countCartItems();
        Assert.assertTrue(itemsAfter == itemsBefore - 1 || itemsAfter == 0);
    }

    @Test(dependsOnMethods = "Cart_Tc2_removeItemFromCart")
    public void Cart_Tc3_checkoutWithOneItem() {
        bot.getDriver().findElement(By.id("add-to-cart-sauce-labs-backpack")).click();
        cartPage.openCartLink();
        Assert.assertTrue(cartPage.isCartPageOpened());
        Assert.assertEquals(cartPage.countCartItems(), 1);

        cartPage.clickCheckoutButton();

        Assert.assertEquals(
                bot.getCurrentUrl(),
                "https://www.saucedemo.com/checkout-step-one.html");
    }

    @Test(dependsOnMethods = "Cart_Tc3_checkoutWithOneItem")
    public void Cart_Tc4_checkoutWithMultipleItems() {
        bot.getDriver().findElement(By.id("add-to-cart-sauce-labs-backpack")).click();
        bot.getDriver().findElement(By.id("add-to-cart-sauce-labs-bike-light")).click();
        bot.getDriver().findElement(By.id("add-to-cart-sauce-labs-bolt-t-shirt")).click();

        cartPage.openCartLink();
        Assert.assertTrue(cartPage.isCartPageOpened());
        Assert.assertEquals(cartPage.countCartItems(), 3);

        cartPage.clickCheckoutButton();

        Assert.assertEquals(
                bot.getCurrentUrl(),
                "https://www.saucedemo.com/checkout-step-one.html");
    }
}