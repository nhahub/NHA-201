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

        Assert.assertEquals(checkoutPage.assertOnStepTwoPage(), true);
    }

    @Test(dataProvider = "checkoutEmptyFirstName", dataProviderClass = TestDataProvider.class)
    public void testCheckoutWithEmptyFirstName(String firstName, String lastName, String zipCode) {
        checkoutPage
                .fillFirstName(firstName)
                .fillLastName(lastName)
                .fillZipCode(zipCode)
                .clickContinue();

        Assert.assertEquals(checkoutPage.assertNotOnStepTwoPage(), true);
        Assert.assertEquals(checkoutPage.assertErrorContains("Error"), true);
    }

    @Test(dataProvider = "checkoutEmptyZip", dataProviderClass = TestDataProvider.class)
    public void testCheckoutWithEmptyZip(String firstName, String lastName, String zipCode) {
        checkoutPage
                .fillFirstName(firstName)
                .fillLastName(lastName)
                .fillZipCode(zipCode)
                .clickContinue();

        Assert.assertEquals(checkoutPage.assertNotOnStepTwoPage(), true);
        Assert.assertEquals(checkoutPage.assertErrorContains("Error"), true);
    }
}
