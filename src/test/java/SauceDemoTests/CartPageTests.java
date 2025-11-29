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

    @Test
    public void Cart_Tc1_openCartAfterAddingItems() {
        driver.findElement(By.id("add-to-cart-sauce-labs-backpack")).click();
        cartPage.openCartLink();

        Assert.assertTrue(cartPage.isCartPageOpened(), "Cart page was not opened successfully!");
        Assert.assertEquals(cartPage.countCartItems(), 1, "Items count mismatch in cart!");
        Assert.assertEquals(cartPage.getFirstItemName(), "Sauce Labs Backpack",
                "Product name not found in cart!");
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/cart.html",
                "Cart URL is not correct!");
    }

    @Test(dependsOnMethods = "Cart_Tc1_openCartAfterAddingItems")
    public void Cart_Tc2_removeItemFromCart() {
        driver.findElement(By.id("add-to-cart-sauce-labs-backpack")).click();
        cartPage.openCartLink();
        int itemsBefore = cartPage.countCartItems();

        cartPage.removeFirstItemFromCart();

        int itemsAfter = cartPage.countCartItems();
        Assert.assertTrue(itemsAfter == itemsBefore - 1 || itemsAfter == 0,
                "Items count did not decrease after removal!");
    }

    @Test(dependsOnMethods = "Cart_Tc2_removeItemFromCart")
    public void Cart_Tc3_checkoutWithOneItem() {
        driver.findElement(By.id("add-to-cart-sauce-labs-backpack")).click();
        cartPage.openCartLink();
        Assert.assertTrue(cartPage.isCartPageOpened(), "Cart page was not opened!");
        Assert.assertEquals(cartPage.countCartItems(), 1, "Cart should contain exactly 1 item!");

        cartPage.clickCheckoutButton();

        Assert.assertEquals(
                driver.getCurrentUrl(),
                "https://www.saucedemo.com/checkout-step-one.html",
                "User was not able to navigate to checkout step one with 1 item!"
        );
    }

    @Test(dependsOnMethods = "Cart_Tc3_checkoutWithOneItem")
    public void Cart_Tc4_checkoutWithMultipleItems() {
        driver.findElement(By.id("add-to-cart-sauce-labs-backpack")).click();
        driver.findElement(By.id("add-to-cart-sauce-labs-bike-light")).click();
        driver.findElement(By.id("add-to-cart-sauce-labs-bolt-t-shirt")).click();

        cartPage.openCartLink();
        Assert.assertTrue(cartPage.isCartPageOpened(), "Cart page was not opened!");
        Assert.assertEquals(cartPage.countCartItems(), 3,
                "Cart should contain 3 items before checkout!");

        cartPage.clickCheckoutButton();

        Assert.assertEquals(
                driver.getCurrentUrl(),
                "https://www.saucedemo.com/checkout-step-one.html",
                "User was not able to navigate to checkout step one with multiple items!"
        );
    }

    @BeforeMethod
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized", "--guest", "--disable-notifications");
        options.setPageLoadStrategy(PageLoadStrategy.NORMAL);
        driver = new ChromeDriver(options);
        loginPage = new LoginPage(driver);

        loginPage.navigateToLoginPage()
                .enterUsername("standard_user")
                .enterPassword("secret_sauce")
                .clickLoginButton();

        cartPage = new CartPage(driver);
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}