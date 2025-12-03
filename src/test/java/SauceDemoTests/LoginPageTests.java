package SauceDemoTests;

import Base.BaseTest;
import DataDrivenTest.TestDataProvider;
import SauceDemoPages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginPageTests extends BaseTest {

    @Test(dataProvider = "validLoginData", dataProviderClass = TestDataProvider.class)
    public void testValidLogin(String username, String password) {
        LoginPage loginPage = new LoginPage(bot);
        loginPage.navigateToLoginPage()
                .enterUsername(username)
                .enterPassword(password)
                .clickLoginButton();

        Assert.assertEquals(loginPage.getPageTitle(), "Swag Labs");
        Assert.assertTrue(loginPage.getCurrentUrl().contains("/inventory.html"));
    }

    @Test(dataProvider = "validLoginData", dataProviderClass = TestDataProvider.class, dependsOnMethods = "testValidLogin")
    public void testSuccessfulLoginVerifyProductsPage(String username, String password) {
        LoginPage loginPage = new LoginPage(bot);
        loginPage.navigateToLoginPage()
                .enterUsername(username)
                .enterPassword(password)
                .clickLoginButton();

        Assert.assertEquals(loginPage.getPageTitle(), "Swag Labs");
        Assert.assertTrue(loginPage.isProductsPageTitleDisplayed());
    }

    @Test(dataProvider = "invalidLoginData", dataProviderClass = TestDataProvider.class, dependsOnMethods = "testSuccessfulLoginVerifyProductsPage")
    public void testInvalidLogin(String username, String password) {
        LoginPage loginPage = new LoginPage(bot);
        loginPage.navigateToLoginPage()
                .enterUsername(username)
                .enterPassword(password)
                .clickLoginButton();

        Assert.assertEquals(
                loginPage.getErrorMessage(),
                "Epic sadface: Username and password do not match any user in this service");
    }

    @Test(dataProvider = "emptyFieldsData", dataProviderClass = TestDataProvider.class, dependsOnMethods = "testInvalidLogin")
    public void testEmptyFields(String username, String password) {
        LoginPage loginPage = new LoginPage(bot);
        loginPage.navigateToLoginPage()
                .enterUsername(username)
                .enterPassword(password)
                .clickLoginButton();

        String errorMsg = loginPage.getErrorMessage();
        Assert.assertFalse(errorMsg.isEmpty());
    }

    @Test(dataProvider = "lockedUserData", dataProviderClass = TestDataProvider.class, dependsOnMethods = "testEmptyFields")
    public void testLockedOutUser(String username, String password) {
        LoginPage loginPage = new LoginPage(bot);
        loginPage.navigateToLoginPage()
                .enterUsername(username)
                .enterPassword(password)
                .clickLoginButton();

        Assert.assertEquals(
                loginPage.getErrorMessage(),
                "Epic sadface: Sorry, this user has been locked out.");
    }
}
