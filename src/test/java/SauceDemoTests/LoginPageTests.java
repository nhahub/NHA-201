
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

    // Scenario 1 (TC-1): Valid Login
    @Test(priority = 1)
    public void testValidLoginStandardUser() {
        loginPage.navigateToLoginPage()
                .enterUsername("standard_user")
                .enterPassword("secret_sauce")
                .clickLoginButton();

        Assert.assertEquals(loginPage.getPageTitle(), "Swag Labs",
                "Page title does not match!");
        Assert.assertTrue(loginPage.getCurrentUrl().contains("/inventory.html"),
                "URL does not contain expected part: /inventory.html");
    }

    // Scenario 1 (TC-2): Successful Login with Standard User
    @Test(priority = 2)
    public void testSuccessfulLoginVerifyProductsPage() {
        loginPage.navigateToLoginPage()
                .enterUsername("standard_user")
                .enterPassword("secret_sauce")
                .clickLoginButton();

        Assert.assertEquals(loginPage.getPageTitle(), "Swag Labs",
                "Page title does not match!");
        Assert.assertTrue(loginPage.isProductsPageTitleDisplayed(),
                "Products page title is not displayed!");
    }

    // INVALID LOGIN SCENARIOS

    // Scenario 2 (TC-1): Invalid Login
    @Test(priority = 3)
    public void testInvalidLogin() {
        loginPage.navigateToLoginPage()
                .enterUsername("invalid_user")
                .enterPassword("secret_sauce")
                .clickLoginButton();

        Assert.assertEquals(
                loginPage.getErrorMessage(),
                "Epic sadface: Username and password do not match any user in this service",
                "Error message does not match!");
    }

    // Scenario 2 (TC-2): Failed Login with Invalid Credentials
    @Test(priority = 4)
    public void testFailedLoginWrongCredentials() {
        loginPage.navigateToLoginPage()
                .enterUsername("wrong_user")
                .enterPassword("wrong_password")
                .clickLoginButton();

        Assert.assertEquals(
                loginPage.getErrorMessage(),
                "Epic sadface: Username and password do not match any user in this service",
                "Error message does not match!");
    }

    // EMPTY FIELDS VALIDATION SCENARIOS

    // Scenario 3 (TC-1): Empty Fields Validation - Empty Username
    @Test(priority = 5)
    public void testEmptyUsername() {
        loginPage.navigateToLoginPage()
                .enterUsername("")
                .enterPassword("secret_sauce")
                .clickLoginButton();

        Assert.assertEquals(
                loginPage.getErrorMessage(),
                "Epic sadface: Username is required",
                "Error message does not match!");
    }

    // Scenario 3 (TC-2): Login Validation - Empty Username
    @Test(priority = 6)
    public void testEmptyUsernameValidation() {
        loginPage.navigateToLoginPage()
                .enterUsername("")
                .enterPassword("secret_sauce")
                .clickLoginButton();

        Assert.assertEquals(
                loginPage.getErrorMessage(),
                "Epic sadface: Username is required",
                "Error message does not match!");
    }

    // LOCKED USER SCENARIO

    // Scenario 4 (TC-1): Locked User Login
    @Test(priority = 7)
    public void testLockedOutUser() {
        loginPage.navigateToLoginPage()
                .enterUsername("locked_out_user")
                .enterPassword("secret_sauce")
                .clickLoginButton();

        Assert.assertEquals(
                loginPage.getErrorMessage(),
                "Epic sadface: Sorry, this user has been locked out.",
                "Error message does not match!");
    }

    // Scenario 4 (TC-2): Login with Locked Out User
    @Test(priority = 8)
    public void testLoginWithLockedUser() {
        loginPage.navigateToLoginPage()
                .enterUsername("locked_out_user")
                .enterPassword("secret_sauce")
                .clickLoginButton();

        Assert.assertEquals(
                loginPage.getErrorMessage(),
                "Epic sadface: Sorry, this user has been locked out.",
                "Error message does not match!");
    }

    // SPECIAL USERS LOGIN SCENARIOS

    // Scenario 5 (TC-1): Special Users Login - problem_user
    @Test(priority = 9)
    public void testProblemUserLogin() {
        loginPage.navigateToLoginPage()
                .enterUsername("problem_user")
                .enterPassword("secret_sauce")
                .clickLoginButton();

        Assert.assertTrue(loginPage.getCurrentUrl().contains("/inventory.html"),
                "URL does not contain expected part: /inventory.html");
        Assert.assertTrue(loginPage.isInventoryContainerDisplayed(),
                "Products page is not displayed after login!");
    }

    // Scenario 5 (TC-2): Login with Performance Glitch User
    @Test(priority = 10)
    public void testPerformanceGlitchUser() {
        long startTime = System.currentTimeMillis();

        loginPage.navigateToLoginPage()
                .enterUsername("performance_glitch_user")
                .enterPassword("secret_sauce")
                .clickLoginButton();

        Assert.assertTrue(loginPage.getCurrentUrl().contains("/inventory.html"),
                "URL does not contain expected part: /inventory.html");
        Assert.assertTrue(loginPage.isInventoryContainerDisplayed(),
                "Products page is not displayed after login!");

        long duration = System.currentTimeMillis() - startTime;
        System.out.println("Performance Glitch User - Login took: " + duration + " ms");

        // Assert delay exists (should be more than 2 seconds)
        Assert.assertTrue(duration > 2000,
                "Expected delay for performance_glitch_user but login was too fast");
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