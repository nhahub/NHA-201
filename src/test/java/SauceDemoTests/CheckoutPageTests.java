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
import SauceDemoPages.CheckoutPage;
import SauceDemoPages.LoginPage;

public class CheckoutPageTests {

    private WebDriver driver;
    private LoginPage loginPage;
    private CartPage cartPage;
    private CheckoutPage checkoutPage;

    @BeforeMethod
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized", "--guest", "--disable-notifications");
        options.setPageLoadStrategy(PageLoadStrategy.NORMAL);
        driver = new ChromeDriver(options);
        loginPage = new LoginPage(driver);

        // Login
        loginPage.navigateToLoginPage()
                .enterUsername("standard_user")
                .enterPassword("secret_sauce")
                .clickLoginButton();

        // Add item and go to checkout
        driver.findElement(By.id("add-to-cart-sauce-labs-backpack")).click();
        cartPage = new CartPage(driver);
        cartPage.openCartLink();
        cartPage.clickCheckoutButton();
        checkoutPage = new CheckoutPage(driver);
    }

    @Test(priority = 1)
    public void Checkout_TC1_proceedToFinishOrder_withValidData() {
        checkoutPage.fillFirstName("First Name");
        checkoutPage.fillLastName("Last Name");
        checkoutPage.fillZipCode("10000");
        checkoutPage.clickContinue();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/checkout-step-two.html",
                "User did not proceed to checkout step two!");
    }

    @Test(priority = 2)
    public void Checkout_TC2_FirstNameEmpty() {
        checkoutPage.fillFirstName("");
        checkoutPage.fillLastName("Last Name");
        checkoutPage.fillZipCode("10000");
        checkoutPage.clickContinue();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        Assert.assertNotEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/checkout-step-two.html",
                "User was able to proceed with empty first name!");
        String errorMsg = driver.findElement(By.xpath("//h3[@data-test='error']")).getText();
        Assert.assertTrue(errorMsg.contains("Error"), "Error message not displayed!");
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}