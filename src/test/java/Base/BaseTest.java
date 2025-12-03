package Base;

import Drivers.DriverFactory;
import Engine.Bot;
import Engine.BotLogger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;

import java.time.Duration;

import static Drivers.DriverFactory.getDriver;
@Listeners({Listener.IInvokedMethodResultListener.class, Listener.ITestResultListener.class})
public class BaseTest {
    protected Bot bot;

    @BeforeMethod
    public void setUp() {
        //open Browser
        BotLogger.info("Chrome driver is opened");
        DriverFactory.setupDriver("chrome");
        WebDriver driver = DriverFactory.getDriver();
        bot = new Bot(driver);
        BotLogger.info("Bot is initialized");
        BotLogger.info("Navigating to Login Page...");
        //driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @AfterMethod
    public void tearDown() {
        //close Browser
        BotLogger.info("Closing browser...");
        DriverFactory.quitDriver();
        BotLogger.info("Browser closed");
    }
}