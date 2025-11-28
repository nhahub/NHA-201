
package SauceDemoTests;

import SauceDemoPages.CartPage;
import SauceDemoPages.LoginPage;
import org.openqa.selenium.By;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

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

        // Login with valid user
        loginPage.navigateToLoginPage()
                .enterUsername("standard_user")
                .enterPassword("secret_sauce")
                .clickLoginButton();

        // Init cart page
        cartPage = new CartPage(driver);
    }

    // TC-1: Open cart after adding one item
    @Test(priority = 1)
    public void Cart_Tc1_openCartAfterAddingItems() {

        // Add single item to cart
        driver.findElement(By.id("add-to-cart-sauce-labs-backpack")).click();

        // Open cart page
        cartPage.openCartLink();

        // Assertions
        Assert.assertTrue(cartPage.isCartPageOpened(), "Cart page was not opened successfully!");
        Assert.assertEquals(cartPage.countCartItems(), 1, "Items count mismatch in cart!");
        Assert.assertEquals(cartPage.getFirstItemName(), "Sauce Labs Backpack",
                "Product name not found in cart!");
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/cart.html",
                "Cart URL is not correct!");
    }

    // TC-2: Remove first item from cart
    @Test(priority = 2)
    public void Cart_Tc2_removeItemFromCart() {

        // Add single item to cart
        driver.findElement(By.id("add-to-cart-sauce-labs-backpack")).click();

        // Open cart and get count before remove
        cartPage.openCartLink();
        int itemsBefore = cartPage.countCartItems();

        // Remove first item
        cartPage.removeFirstItemFromCart();

        // Verify count after remove
        int itemsAfter = cartPage.countCartItems();
        Assert.assertTrue(itemsAfter == itemsBefore - 1 || itemsAfter == 0,
                "Items count did not decrease after removal!");
    }

    // TC-3: Checkout with 1 item in cart
    @Test(priority = 3)
    public void Cart_Tc3_checkoutWithOneItem() {

        // Add single item to cart
        driver.findElement(By.id("add-to-cart-sauce-labs-backpack")).click();

        // Open cart and verify item count
        cartPage.openCartLink();
        Assert.assertTrue(cartPage.isCartPageOpened(), "Cart page was not opened!");
        Assert.assertEquals(cartPage.countCartItems(), 1, "Cart should contain exactly 1 item!");

        // Click checkout button
        cartPage.clickCheckoutButton();

        // Verify checkout step one URL
        Assert.assertEquals(
                driver.getCurrentUrl(),
                "https://www.saucedemo.com/checkout-step-one.html",
                "User was not able to navigate to checkout step one with 1 item!"
        );
    }

    // TC-4: Checkout with multiple items in cart
    @Test(priority = 4)
    public void Cart_Tc4_checkoutWithMultipleItems() {

        // Add multiple items to cart
        driver.findElement(By.id("add-to-cart-sauce-labs-backpack")).click();
        driver.findElement(By.id("add-to-cart-sauce-labs-bike-light")).click();
        driver.findElement(By.id("add-to-cart-sauce-labs-bolt-t-shirt")).click();

        // Open cart and verify total items
        cartPage.openCartLink();
        Assert.assertTrue(cartPage.isCartPageOpened(), "Cart page was not opened!");
        Assert.assertEquals(cartPage.countCartItems(), 3,
                "Cart should contain 3 items before checkout!");

        // Click checkout button
        cartPage.clickCheckoutButton();

        // Verify checkout step one URL
        Assert.assertEquals(
                driver.getCurrentUrl(),
                "https://www.saucedemo.com/checkout-step-one.html",
                "User was not able to navigate to checkout step one with multiple items!"
        );
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}