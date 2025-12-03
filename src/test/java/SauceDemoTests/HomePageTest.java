package SauceDemoTests;

import SauceDemoPages.HomePage;
import SauceDemoPages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class HomePageTest extends BaseTest {

    private HomePage homePage;

    @BeforeMethod
    public void setUpHomePage() {
        LoginPage loginPage = new LoginPage(bot);
        loginPage.loginAsStandardUser();

        homePage = new HomePage(bot);
    }

    @Test
    public void Home_Tc1_scrollAndCheckBadge() {

        homePage
                .scrollToBottom()
                .scrollToTop();


        Assert.assertTrue(bot.getCurrentUrl().contains("inventory"),
                "User is not on Home / Inventory page!");
    }

    @Test
    public void Home_Tc2_testOpenMenu() {

        homePage.clickMenuButton();


        Assert.assertTrue(homePage.isMenuOpen(), "Menu should be visible after clicking the button");
    }

    @Test
    public void Home_Tc3_addMultipleProducts() {

        homePage.addBackpackToCart()
                .addBikeLightToCart()
                .addBoltShirtToCart();

        String badge = homePage.getCartBadgeCount();

        Assert.assertEquals(badge, "3",
                "Cart badge should be '3' after adding Multiple Products");
    }
    @Test
    public void Home_Tc4_removeProducts() {

        homePage.addBackpackToCart()
                .addBikeLightToCart()
                .addBoltShirtToCart();

        homePage.removeBackpack()
                .removeBikeLight()
                .removeBoltShirt();

        String badge = homePage.getCartBadgeCount();

        Assert.assertTrue(
                badge.isEmpty(),
                "Cart badge should be empty after removing all items"
        );
    }

}
