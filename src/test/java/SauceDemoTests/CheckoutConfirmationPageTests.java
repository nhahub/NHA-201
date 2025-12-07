package SauceDemoTests;

import Base.BaseTest;
import DataDrivenTest.TestDataProvider;
import SauceDemoPages.CartPage;
import SauceDemoPages.CheckoutConfirmationPage;
import SauceDemoPages.CheckoutPage;
import SauceDemoPages.LoginPage;
import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CheckoutConfirmationPageTests extends BaseTest {

    private CheckoutConfirmationPage confirmationPage;

    @BeforeMethod
    @Step("Login as standard user before test")
    public void setUpConfirmation() {
        new LoginPage(bot)
                .loginAsStandardUser();
    }

    @Step("Finish order with backpack item")
    private CheckoutConfirmationPage finishOrderWithBackpack(String firstName,
                                                             String lastName,
                                                             String zipCode) {
        new CartPage(bot)
                .addBackpackAndGoToCheckout();

        new CheckoutPage(bot)
                .completeCheckoutForm(firstName, lastName, zipCode)
                .clickFinish();

        return new CheckoutConfirmationPage(bot);
    }

    @Step("Go to checkout with empty cart")
    private void goToCheckoutWithEmptyCart() {
        new CartPage(bot)
                .goToCheckout();
    }

    @Step("Complete checkout form and click Finish")
    private CheckoutConfirmationPage completeCheckoutAndFinish(String firstName,
                                                               String lastName,
                                                               String zipCode) {
        new CheckoutPage(bot)
                .completeCheckoutForm(firstName, lastName, zipCode)
                .clickFinish();

        return new CheckoutConfirmationPage(bot);
    }

    @Step("Verify user stays on checkout step-two page")
    private void verifyStaysOnCheckoutStepTwoPage() {
        Assert.assertEquals(
                confirmationPage.getCurrentUrl(),
                "https://www.saucedemo.com/checkout-step-two.html"
        );
    }

    @Test(dataProvider = "checkoutValidData", dataProviderClass = TestDataProvider.class)
    public void Confirmation_TC1_finishOrderWithItems(String firstName,
                                                      String lastName,
                                                      String zipCode) {

        confirmationPage = finishOrderWithBackpack(firstName, lastName, zipCode);

        Assert.assertEquals(
                confirmationPage.getCurrentUrl(),
                "https://www.saucedemo.com/checkout-complete.html"
        );
        Assert.assertEquals(
                confirmationPage.getThankYouMessageText(),
                "Thank you for your order!"
        );
    }

    @Test(dataProvider = "checkoutValidData", dataProviderClass = TestDataProvider.class)
    public void Confirmation_TC2_finishEmptyCartOrder(String firstName,
                                                      String lastName,
                                                      String zipCode) {

        Allure.step("Navigate to checkout page with an empty cart");
        goToCheckoutWithEmptyCart();

        Allure.step("Fill checkout form with DataProvider values and click Finish");
        confirmationPage = completeCheckoutAndFinish(firstName, lastName, zipCode);

        Allure.step("Assert that user stays on checkout-step-two page (order not completed)");
        verifyStaysOnCheckoutStepTwoPage();
    }

    @Test(dataProvider = "checkoutValidData", dataProviderClass = TestDataProvider.class)
    public void Confirmation_TC3_backToHomeAfterCompletion(String firstName,
                                                           String lastName,
                                                           String zipCode) {

        confirmationPage = finishOrderWithBackpack(firstName, lastName, zipCode);

        confirmationPage.clickBackHomeButton();

        Assert.assertEquals(
                confirmationPage.getCurrentUrl(),
                "https://www.saucedemo.com/inventory.html"
        );
    }
}
