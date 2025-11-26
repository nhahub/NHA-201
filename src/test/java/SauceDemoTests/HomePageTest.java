package SauceDemoTests;


import org.testng.Assert;
import org.testng.annotations.Test;
import SauceDemoPages.HomePage;
import SauceDemoPages.LoginPage;

public class HomePageTest extends BaseTest {

    @Test
    public void loginAndHomeTests() throws InterruptedException {

        driver.get("https://www.saucedemo.com");

        // 2) Login
        LoginPage login = new LoginPage(driver);
        login.enterUsername("standard_user");
        login.enterPassword("secret_sauce");
        login.clickLoginButton();

        //  Inventory
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/inventory.html");

        // 3) HomePage object
        HomePage home = new HomePage(driver);

        // Scroll Down
        home.scrollToBottom();


        // Scroll Up
        home.scrollToTop();


        String badge = home.getCartBadgeCount();
        Assert.assertTrue(badge.isEmpty() || badge.matches("\\d+"));
    }
}
