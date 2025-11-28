
package SauceDemoTests;

import SauceDemoPages.CartPage;
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

        // Login with valid user
        loginPage.navigateToLoginPage()
                .enterUsername("standard_user")
                .enterPassword("secret_sauce")
                .clickLoginButton();

        // Add item and navigate to checkout step one
        driver.findElement(By.id("add-to-cart-sauce-labs-backpack")).click();
        cartPage = new CartPage(driver);
        cartPage.openCartLink();
        cartPage.clickCheckoutButton();
        checkoutPage = new CheckoutPage(driver);
    }

    // TC-1: Proceed to checkout step two with valid data
    @Test(priority = 1)
    public void Checkout_TC1_proceedToFinishOrder_withValidData() {
        // Fill all fields with valid data
        checkoutPage.fillFirstName("First Name");
        checkoutPage.fillLastName("Last Name");
        checkoutPage.fillZipCode("10000");
        checkoutPage.clickContinue();

        // Verify user moved to step two
        Assert.assertEquals(driver.getCurrentUrl(),
                "https://www.saucedemo.com/checkout-step-two.html",
                "User did not proceed to checkout step two!");
    }

    // TC-2: Try to proceed to checkout step two with first name empty
    @Test(priority = 2)
    public void Checkout_TC2_FirstNameEmpty() {
        // Leave first name empty
        checkoutPage.fillFirstName("");
        checkoutPage.fillLastName("Last Name");
        checkoutPage.fillZipCode("10000");
        checkoutPage.clickContinue();

        // Verify user did not move to step two
        Assert.assertNotEquals(driver.getCurrentUrl(),
                "https://www.saucedemo.com/checkout-step-two.html",
                "User was able to proceed with empty first name!");

        // Verify error message is shown
        String errorMsg = driver.findElement(By.xpath("//h3[@data-test='error']")).getText();
        Assert.assertTrue(errorMsg.contains("Error"), "Error message not displayed!");
    }

    // TC-3: Try to proceed to checkout step two with ZipCode empty
    @Test(priority = 3)
    public void Checkout_TC3_ZipCodeEmpty() {
        // Leave zip code empty
        checkoutPage.fillFirstName("First Name");
        checkoutPage.fillLastName("Last Name");
        checkoutPage.fillZipCode("");
        checkoutPage.clickContinue();

        // Verify user stays on step one
        Assert.assertEquals(
                driver.getCurrentUrl(),
                "https://www.saucedemo.com/checkout-step-one.html",
                "User should stay on step one with empty postal code!"
        );

        // Verify specific error message for postal code
        String emptyZipError = driver.findElement(By.xpath("//h3[@data-test='error']")).getText();
        Assert.assertTrue(emptyZipError.contains("Postal Code is required"),
                "Error message for empty postal code not displayed!");
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}