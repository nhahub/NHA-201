
package Project;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import SauceDemoPages.CartPage;
import SauceDemoPages.CheckoutPage;

public class CheckoutPageTests {

    private WebDriver driver;
    private CartPage cartPage;
    private CheckoutPage checkoutPage;

    @BeforeMethod
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
        cartPage.openCartLink();
        cartPage.clickCheckoutButton();
        checkoutPage = new CheckoutPage(driver);
    }

    @Test(priority = 1)
    public void Checkout_TC1_proceedToFinishOrder_withValidData() {
        checkoutPage.fillFirstName("TestUser");
        checkoutPage.fillLastName("Automation");
        checkoutPage.fillZipCode("11111");
        checkoutPage.clickContinue();

        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/checkout-step-two.html");
    }

    @Test(priority = 2)
    public void Checkout_TC2_FirstNameEmpty() {
        checkoutPage.fillFirstName(""); // First name empty
        checkoutPage.fillLastName("Automation");
        checkoutPage.fillZipCode("11111");
        checkoutPage.clickContinue();

        Assert.assertNotEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/checkout-step-two.html");
        String errorMsg = driver.findElement(By.xpath("//h3[@data-test='error']")).getText();
        Assert.assertTrue(errorMsg.contains("Error"), "Error message not displayed!");
    }
    //Close Browser
    /*@AfterMethod
    public void closeBrowser() {
        driver.quit();
    }*/
}