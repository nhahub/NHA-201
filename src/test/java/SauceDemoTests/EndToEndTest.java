package SauceDemoTests;

import Base.BaseTest;
import SauceDemoPages.*;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;


public class EndToEndTest extends BaseTest {

    @Test
    //TODO: valid end to end purchase flow
    public void validEndToEndPurchaseFlow1() {
                new LoginPage(bot)
                        .loginAsStandardUser()
                        .addBackpackToCart()
                        .addBikeLightToCart()
                        .addBoltShirtToCart()
                        .openCartIcon()
                        .clickCheckoutButton()
                        .fillFirstName("NHA")
                        .fillLastName("Testing")
                        .fillZipCode("201")
                        .clickContinue()
                        .clickFinish()
                        .getThankYouMessageText();
        Assert.assertEquals(
                new CheckoutConfirmationPage(bot).getThankYouMessageText(),
                "Thank you for your order!");
    }
    //TODO: valid end to end purchase flow , back to home page
    @Test
    public void validEndToEndPurchaseFlow2() {
            new LoginPage(bot)
                    .loginAsStandardUser()
                    .addBackpackToCart()
                    .addBikeLightToCart()
                    .addBoltShirtToCart()
                    .openCartIcon()
                    .clickCheckoutButton()
                    .fillFirstName("NHA")
                    .fillLastName("Testing")
                    .fillZipCode("201")
                    .clickContinue()
                    .clickFinish()
                    .clickBackHomeButton();
        Assert.assertEquals(
                new CheckoutConfirmationPage(bot).getCurrentUrl(),
                "https://www.saucedemo.com/inventory.html");
    }
    //TODO: invalid, missing fields in checkout page
    @Test
    public void invalidEndToEndPurchaseFlow1() {
        new LoginPage(bot)
                .loginAsStandardUser()
                .addBackpackToCart()
                .addBikeLightToCart()
                .addBoltShirtToCart()
                .openCartIcon()
                .clickCheckoutButton()
                .fillFirstName("")
                .fillLastName("Testing")
                .fillZipCode("201")
                .clickContinue();
        Assert.assertNotEquals(
                new CheckoutPage(bot).getCurrentUrl(),
                "https://www.saucedemo.com/checkout-step-two.html"
        );
        String errorMsg = bot.findAndGetText(By.xpath("//h3[@data-test='error']"));
        Assert.assertTrue(errorMsg.contains("Error: First Name is required"));
    }
    //TODO: invalid, missing fields in checkout page
    @Test
    public void invalidEndToEndPurchaseFlow2() {
        new LoginPage(bot)
                .loginAsStandardUser()
                .addBackpackToCart()
                .addBikeLightToCart()
                .addBoltShirtToCart()
                .openCartIcon()
                .clickCheckoutButton()
                .fillFirstName("NHA")
                .fillLastName("")
                .fillZipCode("201")
                .clickContinue();
        Assert.assertNotEquals(
                new CheckoutPage(bot).getCurrentUrl(),
                "https://www.saucedemo.com/checkout-step-two.html"
        );
        String errorMsg = bot.findAndGetText(By.xpath("//h3[@data-test='error']"));
        Assert.assertTrue(errorMsg.contains("Error: Last Name is required"));
    }
    //TODO: invalid, missing fields in checkout page
    @Test
    public void invalidEndToEndPurchaseFlow3() {
        new LoginPage(bot)
                .loginAsStandardUser()
                .addBackpackToCart()
                .addBikeLightToCart()
                .addBoltShirtToCart()
                .openCartIcon()
                .clickCheckoutButton()
                .fillFirstName("NHA")
                .fillLastName("Testing")
                .fillZipCode("")
                .clickContinue();
        Assert.assertNotEquals(
                new CheckoutPage(bot).getCurrentUrl(),
                "https://www.saucedemo.com/checkout-step-two.html"
        );
        String errorMsg = bot.findAndGetText(By.xpath("//h3[@data-test='error']"));
        Assert.assertTrue(errorMsg.contains("Error: Postal Code is required"));
    }

    //TODO: invalid, missing fields in checkout page
    @Test
    public void invalidEndToEndPurchaseFlow4() {
        new LoginPage(bot)
                .loginAsStandardUser()
                .addBackpackToCart()
                .addBikeLightToCart()
                .addBoltShirtToCart()
                .openCartIcon()
                .clickCheckoutButton()
                .fillFirstName("")
                .fillLastName("Testing")
                .fillZipCode("")
                .clickCancel();
        Assert.assertEquals(
               new CartPage(bot).getCurrentUrl(),
                "https://www.saucedemo.com/cart.html");
    }
    //TODO: invalid, missing fields in checkout page
    @Test
    public void invalidEndToEndPurchaseFlow5() {
        new LoginPage(bot)
                .loginAsStandardUser()
                .addBackpackToCart()
                .addBikeLightToCart()
                .addBoltShirtToCart()
                .openCartIcon()
                .clickCheckoutButton()
                .fillFirstName("")
                .fillLastName("")
                .fillZipCode("201")
                .clickContinue();
        Assert.assertNotEquals(
                new CheckoutPage(bot).getCurrentUrl(),
                "https://www.saucedemo.com/checkout-step-two.html"
        );
        String errorMsg = bot.findAndGetText(By.xpath("//h3[@data-test='error']"));
        Assert.assertTrue(errorMsg.contains("Error"));
    }
    //TODO: cancel order after filling some fields in checkout page
    @Test
    public void invalidEndToEndPurchaseFlow6() {
        new LoginPage(bot)
                .loginAsStandardUser()
                .addBackpackToCart()
                .addBikeLightToCart()
                .addBoltShirtToCart()
                .openCartIcon()
                .clickCheckoutButton()
                .fillFirstName("NHA")
                .fillLastName("")
                .fillZipCode("201")
                .clickContinue()
                .clickCancel();
        Assert.assertEquals(
                bot.getCurrentUrl(),
                "https://www.saucedemo.com/cart.html");
    }
}



