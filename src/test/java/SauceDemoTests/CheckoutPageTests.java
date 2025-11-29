package SauceDemoTests;

import SauceDemoPages.CartPage;
import SauceDemoPages.CheckoutPage;
import SauceDemoPages.LoginPage;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CheckoutPageTests extends BaseTest {

    private LoginPage loginPage;
    private CartPage cartPage;
    private CheckoutPage checkoutPage;

    @BeforeMethod
    public void setUpCheckout() {
        loginPage = new LoginPage(bot);
        loginPage.navigateToLoginPage()
                .enterUsername("standard_user")
                .enterPassword("secret_sauce")
                .clickLoginButton();

        bot.getDriver().findElement(By.id("add-to-cart-sauce-labs-backpack")).click();
        cartPage = new CartPage(bot);
        cartPage.openCartLink().clickCheckoutButton();
        checkoutPage = new CheckoutPage(bot);
    }

    @Test(dataProvider = "checkoutData", dataProviderClass = TestDataProvider.class)
    public void testCheckoutWithValidData(String firstName, String lastName, String zipCode) {
        checkoutPage.fillFirstName(firstName)
                .fillLastName(lastName)
                .fillZipCode(zipCode)
                .clickContinue();

        Assert.assertEquals(bot.getCurrentUrl(),
                "https://www.saucedemo.com/checkout-step-two.html");
    }

    @Test(dataProvider = "emptyCheckoutFieldsData", dataProviderClass = TestDataProvider.class, dependsOnMethods = "testCheckoutWithValidData")
    public void testCheckoutWithEmptyFields(String firstName, String lastName, String zipCode) {
        checkoutPage.fillFirstName(firstName)
                .fillLastName(lastName)
                .fillZipCode(zipCode)
                .clickContinue();

        Assert.assertNotEquals(bot.getCurrentUrl(),
                "https://www.saucedemo.com/checkout-step-two.html");

        String errorMsg = bot.getDriver().findElement(By.xpath("//h3[@data-test='error']")).getText();
        Assert.assertTrue(errorMsg.contains("Error"));
    }
}