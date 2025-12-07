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
    }

    @Test
    public void Cart_Tc1_openCartAfterAddingItems() {
        new CartPage(bot)
           .addBackpack()
           .openCartIcon();
        Assert.assertTrue(new CartPage(bot).isCartPageOpened());
        Assert.assertEquals(new CartPage(bot).countCartItems(), 1);
        Assert.assertEquals(new CartPage(bot).getFirstItemName(), "Sauce Labs Backpack");
        Assert.assertEquals(new CartPage(bot).getCartBadgeNumber(), 1);
    }

    @Test
    public void Cart_Tc2_removeItemFromCart() {
        new CartPage(bot)
            .addBackpack()
            .openCartIcon();
        int itemsBefore = new CartPage(bot).countCartItems();
        new CartPage(bot).removeFirstItemFromCart();
        int itemsAfter = new CartPage(bot).countCartItems();
        Assert.assertTrue(itemsAfter == itemsBefore - 1 || itemsAfter == 0);
        if (itemsAfter > 0) {
            Assert.assertEquals(new CartPage(bot).getCartBadgeNumber(), itemsAfter);
        }
    }

    @Test
    public void Cart_Tc3_checkoutWithOneItem() {
        new CartPage(bot)
            .addBackpack()
            .openCartIcon();
        Assert.assertTrue(new CartPage(bot).isCartPageOpened());
        Assert.assertEquals(new CartPage(bot).countCartItems(), 1);
        Assert.assertEquals(new CartPage(bot).getCartBadgeNumber(), 1);
        new CartPage(bot)
            .clickCheckoutButton();
        Assert.assertEquals(
                new CartPage(bot).getCurrentUrl(),
                "https://www.saucedemo.com/checkout-step-one.html");
    }

    @Test
    public void Cart_Tc4_checkoutWithMultipleItems() {
        new CartPage(bot)
            .addMultipleItems()
            .openCartIcon();
        Assert.assertTrue(new CartPage(bot).isCartPageOpened());
        Assert.assertEquals(new CartPage(bot).countCartItems(), 3);
        Assert.assertEquals(new CartPage(bot).getCartBadgeNumber(), 3);
        new CartPage(bot)
            .clickCheckoutButton();
        Assert.assertEquals(
                new CartPage(bot).getCurrentUrl(),
                "https://www.saucedemo.com/checkout-step-one.html");
    }
}
