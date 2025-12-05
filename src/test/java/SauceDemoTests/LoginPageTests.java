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
        LoginPage loginPage = new LoginPage(bot);
        BotLogger.info("Test Started: loginTest");
        loginPage.navigateToLoginPage();
        loginPage.enterUsername(username);
        loginPage.enterPassword(password);
        loginPage.clickLoginButton();
        Assert.assertEquals(loginPage.getPageTitle(), "Swag Labs");
        Assert.assertTrue(loginPage.getCurrentUrl().contains("/inventory.html"));
        BotLogger.info("Test Finished: loginTest");
        }

    @Test(dataProvider = "validLoginData", dataProviderClass = TestDataProvider.class, dependsOnMethods = "testValidLogin")
    public void testSuccessfulLoginVerifyProductsPage(String username, String password) {
        BotLogger.info("Test Started: loginTest");
        LoginPage loginPage = new LoginPage(bot);
        loginPage.navigateToLoginPage();
        loginPage.enterUsername(username);
        loginPage.enterPassword(password);
        loginPage.clickLoginButton();
        Assert.assertEquals(loginPage.getPageTitle(), "Swag Labs");
        Assert.assertTrue(loginPage.isProductsPageTitleDisplayed());
        BotLogger.info("Test Finished: loginTest");

    }

    @Test(dataProvider = "invalidLoginData", dataProviderClass = TestDataProvider.class, dependsOnMethods = "testSuccessfulLoginVerifyProductsPage")
    public void testInvalidLogin(String username, String password) {
        LoginPage loginPage = new LoginPage(bot);
        loginPage.navigateToLoginPage();
        loginPage.enterUsername(username);
        loginPage.enterPassword(password);
        loginPage.clickLoginButton();
        Assert.assertEquals(loginPage.getErrorMessage(),
                "Epic sadface: Username and password do not match any user in this service");
    }

    @Test(dataProvider = "emptyFieldsData", dataProviderClass = TestDataProvider.class, dependsOnMethods = "testInvalidLogin")
    public void testEmptyFields(String username, String password) {
        LoginPage loginPage = new LoginPage(bot);
        loginPage.navigateToLoginPage();
        loginPage.enterUsername(username);
        loginPage.enterPassword(password);
        loginPage.clickLoginButton();
        String errorMsg = loginPage.getErrorMessage();
        Assert.assertFalse(errorMsg.isEmpty());
    }

    @Test(dataProvider = "lockedUserData", dataProviderClass = TestDataProvider.class, dependsOnMethods = "testEmptyFields")
    public void testLockedOutUser(String username, String password) {
        LoginPage loginPage = new LoginPage(bot);
        loginPage.navigateToLoginPage();
        loginPage.enterUsername(username);
        loginPage.enterPassword(password);
        loginPage.clickLoginButton();
        Assert.assertEquals(
                loginPage.getErrorMessage(),
                "Epic sadface: Sorry, this user has been locked out.");
    }

    @Test
    public void validLoginWithJasonData() {
        String EMAIL = BotData.getJsonData("LoginData", "username");
        String PASSWORD = BotData.getJsonData("LoginData", "password");
        LoginPage loginPage = new LoginPage(bot);
        BotLogger.info("Test Started: loginTest");
        loginPage.navigateToLoginPage();
        loginPage.enterUsername(EMAIL);
        loginPage.enterPassword(PASSWORD);
        loginPage.clickLoginButton();
        Assert.assertEquals(loginPage.getPageTitle(), "Swag Labs");
        Assert.assertTrue(loginPage.getCurrentUrl().contains("/inventory.html"));
        BotLogger.info("Test Finished: loginTest");
    }

}