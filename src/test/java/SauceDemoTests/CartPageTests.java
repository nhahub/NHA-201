package SauceDemoTests;

import Base.BaseTest;
import SauceDemoPages.CartPage;
import SauceDemoPages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CartPageTests extends BaseTest {
    private CartPage cartPage;

    @BeforeMethod
    public void setUpCart() {
        new LoginPage(bot)
             .loginAsStandardUser();
        cartPage = new CartPage(bot);
    }

    @Test
    public void Cart_Tc1_openCartAfterAddingItems() {
        cartPage.addBackpack();
        cartPage.openCartLink();
        Assert.assertTrue(cartPage.isCartPageOpened());
        Assert.assertEquals(cartPage.countCartItems(), 1);
        Assert.assertEquals(cartPage.getFirstItemName(), "Sauce Labs Backpack");
        Assert.assertEquals(cartPage.getCartBadgeNumber(), 1);
    }

    @Test
    public void Cart_Tc2_removeItemFromCart() {
        cartPage.addBackpack();
        cartPage.openCartLink();
        int itemsBefore = cartPage.countCartItems();
        cartPage.removeFirstItemFromCart();
        int itemsAfter = cartPage.countCartItems();
        Assert.assertTrue(itemsAfter == itemsBefore - 1 || itemsAfter == 0);
        if (itemsAfter > 0) {
            Assert.assertEquals(cartPage.getCartBadgeNumber(), itemsAfter);
        }
    }

    @Test
    public void Cart_Tc3_checkoutWithOneItem() {
        cartPage.addBackpack();
        cartPage.openCartLink();
        Assert.assertTrue(cartPage.isCartPageOpened());
        Assert.assertEquals(cartPage.countCartItems(), 1);
        Assert.assertEquals(cartPage.getCartBadgeNumber(), 1);
        cartPage.clickCheckoutButton();
        Assert.assertEquals(
                bot.getCurrentUrl(),
                "https://www.saucedemo.com/checkout-step-one.html");
    }

    @Test
    public void Cart_Tc4_checkoutWithMultipleItems() {
        cartPage.addMultipleItems();
        cartPage.openCartLink();
        Assert.assertTrue(cartPage.isCartPageOpened());
        Assert.assertEquals(cartPage.countCartItems(), 3);
        Assert.assertEquals(cartPage.getCartBadgeNumber(), 3);
        cartPage.clickCheckoutButton();
        Assert.assertEquals(
                bot.getCurrentUrl(),
                "https://www.saucedemo.com/checkout-step-one.html");
    }
}
