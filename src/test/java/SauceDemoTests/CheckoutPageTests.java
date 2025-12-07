package SauceDemoTests;

import Base.BaseTest;
import DataDrivenTest.TestDataProvider;
import SauceDemoPages.CartPage;
import SauceDemoPages.CheckoutPage;
import SauceDemoPages.LoginPage;
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

        checkoutPage = new CheckoutPage(bot);
    }

    @Test(dataProvider = "checkoutValidData", dataProviderClass = TestDataProvider.class)
    public void testCheckoutWithValidData(String firstName, String lastName, String zipCode) {
        checkoutPage
                .fillFirstName(firstName)
                .fillLastName(lastName)
                .fillZipCode(zipCode)
                .clickContinue();

        Assert.assertEquals(
                checkoutPage.getCurrentUrl(),
                "https://www.saucedemo.com/checkout-step-two.html"
        );
    }

    @Test(dataProvider = "checkoutEmptyFirstName", dataProviderClass = TestDataProvider.class)
    public void testCheckoutWithEmptyFirstName(String firstName, String lastName, String zipCode) {
        checkoutPage
                .fillFirstName(firstName)
                .fillLastName(lastName)
                .fillZipCode(zipCode)
                .clickContinue();

        Assert.assertNotEquals(
                checkoutPage.getCurrentUrl(),
                "https://www.saucedemo.com/checkout-step-two.html"
        );

        String errorMsg = checkoutPage.getErrorMessageText();
        Assert.assertTrue(errorMsg.contains("Error"));
    }

    @Test(dataProvider = "checkoutEmptyZip", dataProviderClass = TestDataProvider.class)
    public void testCheckoutWithEmptyZip(String firstName, String lastName, String zipCode) {
        checkoutPage
                .fillFirstName(firstName)
                .fillLastName(lastName)
                .fillZipCode(zipCode)
                .clickContinue();

        Assert.assertNotEquals(
                checkoutPage.getCurrentUrl(),
                "https://www.saucedemo.com/checkout-step-two.html"
        );

        String errorMsg = checkoutPage.getErrorMessageText();
        Assert.assertTrue(errorMsg.contains("Error"));
    }
}
