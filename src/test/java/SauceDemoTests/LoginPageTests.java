package SauceDemoTests;

import Base.BaseTest;
import DataDrivenTest.TestDataProvider;
import Engine.BotData;
import Engine.BotLogger;
import SauceDemoPages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginPageTests extends BaseTest {

    @Test(dataProvider = "validLoginData", dataProviderClass = TestDataProvider.class )
    public void testValidLogin(String username, String password) {
        new LoginPage(bot)
            .navigateToLoginPage()
            .enterUsername(username)
            .enterPassword(password)
            .clickLoginButton();
        Assert.assertEquals(new LoginPage(bot).getPageTitle(), "Swag Labs");
        Assert.assertTrue(new LoginPage(bot).getCurrentUrl().contains("/inventory.html")
        );
        }

    @Test(dataProvider = "validLoginData", dataProviderClass = TestDataProvider.class, dependsOnMethods = "testValidLogin")
    public void testSuccessfulLoginVerifyProductsPage(String username, String password) {
        new LoginPage(bot)
                .navigateToLoginPage()
                .enterUsername(username)
                .enterPassword(password)
                .clickLoginButton();
        Assert.assertEquals(new LoginPage(bot).ActualLoginTitle(), "Swag Labs");
        Assert.assertTrue(new LoginPage(bot).isProductsPageTitleDisplayed());
    }

    @Test(dataProvider = "invalidLoginData", dataProviderClass = TestDataProvider.class, dependsOnMethods = "testSuccessfulLoginVerifyProductsPage")
    public void testInvalidLogin(String username, String password) {
       new LoginPage(bot)
           .navigateToLoginPage()
           .enterUsername(username)
           .enterPassword(password)
           .clickLoginButton();
       Assert.assertEquals(new LoginPage(bot).getErrorMessage(),
                "Epic sadface: Username and password do not match any user in this service");
    }

    @Test(dataProvider = "emptyFieldsData", dataProviderClass = TestDataProvider.class, dependsOnMethods = "testInvalidLogin")
    public void testEmptyFields(String username, String password) {
        new LoginPage(bot)
            .navigateToLoginPage()
            .enterUsername(username)
            .enterPassword(password)
            .clickLoginButton();
        String errorMsg = new LoginPage(bot).getErrorMessage();
        Assert.assertFalse(errorMsg.isEmpty());
    }

    @Test(dataProvider = "lockedUserData", dataProviderClass = TestDataProvider.class, dependsOnMethods = "testEmptyFields")
    public void testLockedOutUser(String username, String password) {
       new LoginPage(bot)
                  .navigateToLoginPage()
                  .enterUsername(username)
                  .enterPassword(password)
                  .clickLoginButton();
        Assert.assertEquals(new LoginPage(bot).getErrorMessage(),
                "Epic sadface: Sorry, this user has been locked out.");
    }

    @Test
    public void validLoginWithJasonData() {
        String EMAIL = BotData.getJsonData("LoginData", "username");
        String PASSWORD = BotData.getJsonData("LoginData", "password");
        new LoginPage(bot)
           .navigateToLoginPage()
           .enterUsername(EMAIL)
           .enterPassword(PASSWORD)
           .clickLoginButton();
        Assert.assertEquals(new LoginPage(bot).getPageTitle(), "Swag Labs");
        Assert.assertTrue(new LoginPage(bot).getCurrentUrl().contains("/inventory.html")
        );
    }
}