package SauceDemoTests;

import org.openqa.selenium.By;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import SauceDemoPages.CartPage;
import SauceDemoPages.LoginPage;

public class CartPageTests {

    private WebDriver driver;
    private LoginPage loginPage;
    private CartPage cartPage;

    @BeforeMethod
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized", "--guest", "--disable-notifications");
        options.setPageLoadStrategy(PageLoadStrategy.NORMAL);
        driver = new ChromeDriver(options);
        loginPage = new LoginPage(driver);

        // Login and add item to cart
        loginPage.navigateToLoginPage()
                .enterUsername("standard_user")
                .enterPassword("secret_sauce")
                .clickLoginButton();

        driver.findElement(By.id("add-to-cart-sauce-labs-backpack")).click();
        cartPage = new CartPage(driver);
    }

    @Test(priority = 1)
    public void Cart_Tc1_openCartAfterAddingItems() {
        cartPage.openCartLink();
        Assert.assertTrue(cartPage.isCartPageOpened(), "Cart page was not opened successfully!");
        Assert.assertEquals(cartPage.countCartItems(), 1, "Items count mismatch in cart!");
        Assert.assertEquals(cartPage.getFirstItemName(), "Sauce Labs Backpack", "Product name not found in cart!");
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/cart.html", "Cart URL is not correct!");
    }

    @Test(priority = 2)
    public void Cart_Tc2_removeItemFromCart() {
        cartPage.openCartLink();
        int itemsBefore = cartPage.countCartItems();
        cartPage.removeFirstItemFromCart();

        // Wait for the page to update
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        int itemsAfter = cartPage.countCartItems();
        Assert.assertEquals(itemsAfter, itemsBefore - 1, "Item was not removed from cart!");
    }

    @Test(priority = 3)
    public void Cart_Tc3_checkoutWithItems() {
        cartPage.openCartLink();
        Assert.assertTrue(cartPage.isCartPageOpened(), "Cart page was not opened!");
        Assert.assertTrue(cartPage.countCartItems() > 0, "No items in cart!");

        cartPage.clickCheckoutButton();

        // Wait for navigation
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        Assert.assertEquals(
                driver.getCurrentUrl(),
                "https://www.saucedemo.com/checkout-step-one.html",
                "User was not able to navigate to checkout!"
        );
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}