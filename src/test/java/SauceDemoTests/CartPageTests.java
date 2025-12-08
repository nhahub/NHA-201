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

        Assert.assertEquals(new CartPage(bot).assertIsCartPageOpened(), true);
        Assert.assertEquals(new CartPage(bot).assertCartItemsCount(1), true);
        Assert.assertEquals(new CartPage(bot).assertFirstItemName("Sauce Labs Backpack"), true);
        Assert.assertEquals(new CartPage(bot).assertCartBadgeNumber(1), true);
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
            Assert.assertEquals(new CartPage(bot).assertCartBadgeNumber(itemsAfter), true);
        }
    }

    @Test
    public void Cart_Tc3_checkoutWithOneItem() {
        new CartPage(bot)
                .addBackpack()
                .openCartIcon();

        Assert.assertEquals(new CartPage(bot).assertIsCartPageOpened(), true);
        Assert.assertEquals(new CartPage(bot).assertCartItemsCount(1), true);
        Assert.assertEquals(new CartPage(bot).assertCartBadgeNumber(1), true);

        new CartPage(bot).clickCheckoutButton();

        Assert.assertEquals(new CartPage(bot).assertCheckoutStepOneUrl(), true);
    }

    @Test
    public void Cart_Tc4_checkoutWithMultipleItems() {
        new CartPage(bot)
                .addMultipleItems()
                .openCartIcon();

        Assert.assertEquals(new CartPage(bot).assertIsCartPageOpened(), true);
        Assert.assertEquals(new CartPage(bot).assertCartItemsCount(3), true);
        Assert.assertEquals(new CartPage(bot).assertCartBadgeNumber(3), true);

        new CartPage(bot).clickCheckoutButton();

        Assert.assertEquals(new CartPage(bot).assertCheckoutStepOneUrl(), true);
    }
}
