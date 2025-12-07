package SauceDemoTests;

import Base.BaseTest;
import DataDrivenTest.TestDataProvider;
import SauceDemoPages.CartPage;
import SauceDemoPages.CheckoutPage;
import SauceDemoPages.LoginPage;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CheckoutPageTests extends BaseTest {
    private CheckoutPage checkoutPage;

    @BeforeMethod
    public void setUpCheckout() {
       new LoginPage(bot)
           .loginAsStandardUser();
       new CartPage(bot)
           .addBackpackAndGoToCheckout();
    }

    @Test(dataProvider = "checkoutValidData", dataProviderClass = TestDataProvider.class)
    public void testCheckoutWithValidData(String firstName, String lastName, String zipCode) {
        new CheckoutPage(bot)
            .fillFirstName(firstName)
            .fillLastName(lastName)
            .fillZipCode(zipCode)
            .clickContinue();
        Assert.assertEquals(
                new CheckoutPage(bot).getCurrentUrl(),
                "https://www.saucedemo.com/checkout-step-two.html"
        );
    }

    @Test(dataProvider = "checkoutEmptyFirstName", dataProviderClass = TestDataProvider.class)
    public void testCheckoutWithEmptyFirstName(String firstName, String lastName, String zipCode) {
        new CheckoutPage(bot)
            .fillFirstName(firstName)
            .fillLastName(lastName)
            .fillZipCode(zipCode)
            .clickContinue();
        Assert.assertNotEquals(
                new CheckoutPage(bot).getCurrentUrl(),
                "https://www.saucedemo.com/checkout-step-two.html"
        );
        String errorMsg = bot.findAndGetText(By.xpath("//h3[@data-test='error']"));
        Assert.assertTrue(errorMsg.contains("Error"));
    }

    @Test(dataProvider = "checkoutEmptyZip", dataProviderClass = TestDataProvider.class)
    public void testCheckoutWithEmptyZip(String firstName, String lastName, String zipCode) {
        new CheckoutPage(bot)
           .fillFirstName(firstName)
           .fillLastName(lastName)
           .fillZipCode(zipCode)
           .clickContinue();
        Assert.assertNotEquals(
                new CheckoutPage(bot).getCurrentUrl(),
                "https://www.saucedemo.com/checkout-step-two.html"
        );
        String errorMsg = bot.findAndGetText(By.xpath("//h3[@data-test='error']"));
        Assert.assertTrue(errorMsg.contains("Error"));
    }
}
