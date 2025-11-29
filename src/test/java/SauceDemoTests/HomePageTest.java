package SauceDemoTests;

import org.testng.Assert;
import org.testng.annotations.Test;
import SauceDemoPages.HomePage;
import SauceDemoPages.LoginPage;

public class HomePageTest extends BaseTest {

    @Test
    public void loginAndHomeTests() throws InterruptedException {

        driver.get("https://www.saucedemo.com");

        LoginPage login = new LoginPage(driver);
        login.enterUsername("standard_user");
        login.enterPassword("secret_sauce");
        login.clickLoginButton();

        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/inventory.html");

        HomePage home = new HomePage(driver);

        home.scrollToBottom();

        home.scrollToTop();

        String badge = home.getCartBadgeCount();
        Assert.assertTrue(badge.isEmpty() || badge.matches("\\d+"));
    }
}