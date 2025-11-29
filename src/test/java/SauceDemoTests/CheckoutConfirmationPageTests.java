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

    @Test
    public void Confirmation_TC1_finishOrderWithItems() {
        driver.findElement(By.id("add-to-cart-sauce-labs-backpack")).click();

        cartPage = new CartPage(driver);
        cartPage.openCartLink();
        cartPage.clickCheckoutButton();

        checkoutPage = new CheckoutPage(driver);
        checkoutPage.fillFirstName("First Name");
        checkoutPage.fillLastName("Last Name");
        checkoutPage.fillZipCode("10000");
        checkoutPage.clickContinue();

        driver.findElement(By.id("finish")).click();
        confirmationPage = new CheckoutConfirmationPage(driver);

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

    @Test(dependsOnMethods = "Confirmation_TC1_finishOrderWithItems")
    public void Confirmation_TC2_finishEmptyCartOrder() {
        cartPage = new CartPage(driver);
        cartPage.openCartLink();
        cartPage.clickCheckoutButton();

        checkoutPage = new CheckoutPage(driver);
        checkoutPage.fillFirstName("First Name");
        checkoutPage.fillLastName("Last Name");
        checkoutPage.fillZipCode("10000");
        checkoutPage.clickContinue();

        driver.findElement(By.id("finish")).click();
        confirmationPage = new CheckoutConfirmationPage(driver);

        Assert.assertNotEquals(
                confirmationPage.getCurrentUrl(),
                "https://www.saucedemo.com/checkout-complete.html",
                "User wrongly navigated to finish order page with empty cart!"
        );
    }

    @Test(dependsOnMethods = "Confirmation_TC2_finishEmptyCartOrder")
    public void Confirmation_TC3_backToHomeAfterCompletion() {
        driver.findElement(By.id("add-to-cart-sauce-labs-backpack")).click();

        cartPage = new CartPage(driver);
        cartPage.openCartLink();
        cartPage.clickCheckoutButton();

        checkoutPage = new CheckoutPage(driver);
        checkoutPage.fillFirstName("First Name");
        checkoutPage.fillLastName("Last Name");
        checkoutPage.fillZipCode("10000");
        checkoutPage.clickContinue();

        driver.findElement(By.id("finish")).click();
        confirmationPage = new CheckoutConfirmationPage(driver);

        driver.findElement(By.id("back-to-products")).click();

        Assert.assertEquals(
                driver.getCurrentUrl(),
                "https://www.saucedemo.com/inventory.html",
                "User is not redirected back to inventory page after Back Home!"
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
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}