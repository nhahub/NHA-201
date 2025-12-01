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

        Assert.assertEquals(
                bot.getCurrentUrl(),
                "https://www.saucedemo.com/inventory.html",
                "User did NOT land on Home / Inventory page after login!"
        );

        homePage = new HomePage(bot);
    }

    @Test(priority = 1)
    public void Home_Tc1_scrollAndCheckBadge() {

        homePage
                .scrollToBottom()
                .scrollToTop();


        Assert.assertTrue(bot.getCurrentUrl().contains("inventory"),
                "User is not on Home / Inventory page!");
    }

    @Test(priority = 2)
    public void Home_Tc2_addBackpackAndValidateBadge() {

        homePage.addBackpackToCart();

        String badge = homePage.getCartBadgeCount();

        Assert.assertEquals(badge, "1",
                "Cart badge should be '1' after adding the backpack!");
    }
}
