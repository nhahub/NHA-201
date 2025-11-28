
package SauceDemoTests;

import SauceDemoPages.CartPage;
import SauceDemoPages.CheckoutConfirmationPage;
import SauceDemoPages.CheckoutPage;
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

public class CheckoutConfirmationPageTests {

    private WebDriver driver;
    private LoginPage loginPage;
    private CartPage cartPage;
    private CheckoutPage checkoutPage;
    private CheckoutConfirmationPage confirmationPage;

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
    }

    // TC-1: Finish order with items
    @Test(priority = 1)
    public void Confirmation_TC1_finishOrderWithItems() {
        // Add one item to cart
        driver.findElement(By.id("add-to-cart-sauce-labs-backpack")).click();

        // Open cart and go to checkout
        cartPage = new CartPage(driver);
        cartPage.openCartLink();
        cartPage.clickCheckoutButton();

        // Fill checkout form
        checkoutPage = new CheckoutPage(driver);
        checkoutPage.fillFirstName("First Name");
        checkoutPage.fillLastName("Last Name");
        checkoutPage.fillZipCode("10000");
        checkoutPage.clickContinue();

        // Finish order
        driver.findElement(By.id("finish")).click();
        confirmationPage = new CheckoutConfirmationPage(driver);

        // Verify complete page and thank you message
        Assert.assertEquals(
                confirmationPage.getCurrentUrl(),
                "https://www.saucedemo.com/checkout-complete.html",
                "User did not reach finish order page!"
        );
        Assert.assertEquals(
                confirmationPage.getThankYouMessageText(),
                "Thank you for your order!",
                "Success message not found!"
        );
    }

    // TC-2: Try to finish order with empty cart
    @Test(priority = 2)
    public void Confirmation_TC2_finishEmptyCartOrder() {

        // Open cart with no items and go to checkout
        cartPage = new CartPage(driver);
        cartPage.openCartLink();
        cartPage.clickCheckoutButton();

        // Fill checkout form
        checkoutPage = new CheckoutPage(driver);
        checkoutPage.fillFirstName("First Name");
        checkoutPage.fillLastName("Last Name");
        checkoutPage.fillZipCode("10000");
        checkoutPage.clickContinue();

        // Try to finish order
        driver.findElement(By.id("finish")).click();
        confirmationPage = new CheckoutConfirmationPage(driver);

        // Verify user does not reach complete page
        Assert.assertNotEquals(
                confirmationPage.getCurrentUrl(),
                "https://www.saucedemo.com/checkout-complete.html",
                "User wrongly navigated to finish order page with empty cart!"
        );
    }

    // TC-3: Back to Home from checkout complete page
    @Test(priority = 3)
    public void Confirmation_TC3_backToHomeAfterCompletion() {

        // Add one item to cart
        driver.findElement(By.id("add-to-cart-sauce-labs-backpack")).click();

        // Open cart and go to checkout
        cartPage = new CartPage(driver);
        cartPage.openCartLink();
        cartPage.clickCheckoutButton();

        // Fill checkout form
        checkoutPage = new CheckoutPage(driver);
        checkoutPage.fillFirstName("First Name");
        checkoutPage.fillLastName("Last Name");
        checkoutPage.fillZipCode("10000");
        checkoutPage.clickContinue();

        // Finish order
        driver.findElement(By.id("finish")).click();
        confirmationPage = new CheckoutConfirmationPage(driver);

        // Click Back Home button
        driver.findElement(By.id("back-to-products")).click();

        // Verify user redirected to inventory page
        Assert.assertEquals(
                driver.getCurrentUrl(),
                "https://www.saucedemo.com/inventory.html",
                "User is not redirected back to inventory page after Back Home!"
        );
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}