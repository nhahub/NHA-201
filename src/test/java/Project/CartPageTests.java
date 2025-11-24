
package Project;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import SauceDemoPages.CartPage;

public class CartPageTests {

    private WebDriver driver;
    private CartPage cartPage;

    @BeforeClass
    public void Login() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--incognito");
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.navigate().to("https://www.saucedemo.com/");
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();
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
        int badgeBefore = cartPage.getCartBadgeNumber();
        cartPage.removeFirstItemFromCart();
        int itemsAfter = cartPage.countCartItems();
        int badgeAfter = cartPage.getCartBadgeNumber();
        Assert.assertEquals(itemsAfter, itemsBefore - 1, "Item was not removed from cart!");
        Assert.assertEquals(badgeAfter, badgeBefore - 1, "Cart badge number did not decrease after removal!");
    }

    @Test(priority = 3)
    public void Cart_Tc3_checkoutWithEmptyCart() {
        cartPage.openCartLink();
        while (cartPage.countCartItems() > 0) {
            cartPage.removeFirstItemFromCart();
        }
        cartPage.clickCheckoutButton();
        Assert.assertNotEquals(
                driver.getCurrentUrl(),
                "https://www.saucedemo.com/checkout-step-one.html",
                "User was able to navigate to checkout with an empty cart!"
        );
    }

    // Close browser after tests
    /*@AfterClass
    public void closeBrowser() {
        driver.quit();
    }*/
}