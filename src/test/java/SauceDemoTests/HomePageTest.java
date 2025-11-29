package SauceDemoTests;

import SauceDemoPages.HomePage;
import SauceDemoPages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class HomePageTest extends BaseTest {

    @Test
    public void loginAndHomeTests() {
        LoginPage loginPage = new LoginPage(bot);
        loginPage.navigateToLoginPage()
                .enterUsername("standard_user")
                .enterPassword("secret_sauce")
                .clickLoginButton();

        Assert.assertEquals(bot.getCurrentUrl(), "https://www.saucedemo.com/inventory.html");

        HomePage homePage = new HomePage(bot);
        homePage.scrollToBottom().scrollToTop();

        String badge = homePage.getCartBadgeCount();
        Assert.assertTrue(badge.isEmpty() || badge.matches("\\d+"));
    }
}