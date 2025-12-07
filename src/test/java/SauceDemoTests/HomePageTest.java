package SauceDemoTests;

import Base.BaseTest;
import SauceDemoPages.HomePage;
import SauceDemoPages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class HomePageTest extends BaseTest {
    private HomePage homePage;

    @BeforeMethod
    public void setUpHomePage() {
        new LoginPage(bot)
            .loginAsStandardUser();
    }

    @Test
    public void Home_Tc1_scrollAndCheckBadge() {
        new HomePage(bot)
            .scrollToBottom()
            .scrollToTop();
        Assert.assertTrue(new LoginPage(bot).getCurrentUrl().contains("inventory"),
                "User is not on Home / Inventory page!");
    }

    @Test
    public void Home_Tc2_testOpenMenu() {
        new HomePage(bot)
            .clickMenuButton();
        Assert.assertTrue(new HomePage(bot).isMenuOpen(),
                "Menu should be visible after clicking the button");
    }

    @Test
    public void Home_Tc3_addMultipleProducts() {
        new HomePage(bot)
            .addBackpackToCart()
            .addBikeLightToCart()
            .addBoltShirtToCart();
        //String badge = homePage.getCartBadgeCount();
        Assert.assertEquals(new HomePage(bot).getCartBadgeCount() , "3",
                "Cart badge should be '3' after adding Multiple Products");
    }
    @Test
    public void Home_Tc4_removeProducts() {
        new HomePage(bot)
            .addBackpackToCart()
            .addBikeLightToCart()
            .addBoltShirtToCart()
            .removeBackpack()
            .removeBikeLight()
            .removeBoltShirt();
        //String badge = homePage.getCartBadgeCount();
        Assert.assertTrue(new HomePage(bot).getCartBadgeCount().isEmpty(),
                "Cart badge should be empty after removing all items"
        );
    }

}
