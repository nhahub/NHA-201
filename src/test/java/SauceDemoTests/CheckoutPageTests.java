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

    @Test(priority = 1, dataProvider = "checkoutData", dataProviderClass = TestDataProvider.class)
    public void testCheckoutWithValidData(String firstName, String lastName, String zipCode) {
        checkoutPage.fillFirstName(firstName);
        checkoutPage.fillLastName(lastName);
        checkoutPage.fillZipCode(zipCode);
        checkoutPage.clickContinue();

        Assert.assertEquals(driver.getCurrentUrl(),
                "https://www.saucedemo.com/checkout-step-two.html",
                "User did not proceed to checkout step two with: " + firstName + " " + lastName);
    }

    @Test(priority = 2, dataProvider = "emptyCheckoutFieldsData", dataProviderClass = TestDataProvider.class)
    public void testCheckoutWithEmptyFields(String firstName, String lastName, String zipCode) {
        checkoutPage.fillFirstName(firstName);
        checkoutPage.fillLastName(lastName);
        checkoutPage.fillZipCode(zipCode);
        checkoutPage.clickContinue();

        Assert.assertNotEquals(driver.getCurrentUrl(),
                "https://www.saucedemo.com/checkout-step-two.html",
                "User should not proceed with empty fields!");

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