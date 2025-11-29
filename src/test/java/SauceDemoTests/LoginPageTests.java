package SauceDemoTests;

import SauceDemoPages.LoginPage;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginPageTests {

    private WebDriver driver;
    private LoginPage loginPage;

    // VALID LOGIN SCENARIOS

    @Test(priority = 1, dataProvider = "validLoginData", dataProviderClass = TestDataProvider.class)
    public void testValidLogin(String username, String password) {
        loginPage.navigateToLoginPage()
                .enterUsername(username)
                .enterPassword(password)
                .clickLoginButton();

        Assert.assertEquals(loginPage.getPageTitle(), "Swag Labs",
                "Page title does not match for user: " + username);
        Assert.assertTrue(loginPage.getCurrentUrl().contains("/inventory.html"),
                "URL does not contain expected part for user: " + username);
    }

    @Test(priority = 2, dataProvider = "validLoginData", dataProviderClass = TestDataProvider.class)
    public void testSuccessfulLoginVerifyProductsPage(String username, String password) {
        loginPage.navigateToLoginPage()
                .enterUsername(username)
                .enterPassword(password)
                .clickLoginButton();

        Assert.assertEquals(loginPage.getPageTitle(), "Swag Labs",
                "Page title does not match for user: " + username);
        Assert.assertTrue(loginPage.isProductsPageTitleDisplayed(),
                "Products page title is not displayed for user: " + username);
    }

    // INVALID LOGIN SCENARIOS

    @Test(priority = 3, dataProvider = "invalidLoginData", dataProviderClass = TestDataProvider.class)
    public void testInvalidLogin(String username, String password) {
        loginPage.navigateToLoginPage()
                .enterUsername(username)
                .enterPassword(password)
                .clickLoginButton();

        Assert.assertEquals(
                loginPage.getErrorMessage(),
                "Epic sadface: Username and password do not match any user in this service",
                "Error message does not match for user: " + username);
    }

    // EMPTY FIELDS VALIDATION SCENARIOS

    @Test(priority = 4, dataProvider = "emptyFieldsData", dataProviderClass = TestDataProvider.class)
    public void testEmptyFields(String username, String password) {
        loginPage.navigateToLoginPage()
                .enterUsername(username)
                .enterPassword(password)
                .clickLoginButton();

        String errorMsg = loginPage.getErrorMessage();
        Assert.assertFalse(errorMsg.isEmpty(),
                "Error message should be displayed for empty fields");
    }

    // LOCKED USER SCENARIO

    @Test(priority = 5, dataProvider = "lockedUserData", dataProviderClass = TestDataProvider.class)
    public void testLockedOutUser(String username, String password) {
        loginPage.navigateToLoginPage()
                .enterUsername(username)
                .enterPassword(password)
                .clickLoginButton();

        Assert.assertEquals(
                loginPage.getErrorMessage(),
                "Epic sadface: Sorry, this user has been locked out.",
                "Locked user error message not correct");
    }

    // SETUP & TEARDOWN

    @BeforeMethod
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized", "--guest", "--disable-notifications");
        options.setPageLoadStrategy(PageLoadStrategy.NORMAL);
        driver = new ChromeDriver(options);
        loginPage = new LoginPage(driver);
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}