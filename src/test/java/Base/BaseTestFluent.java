package Base;

import Drivers.DriverFactory;
import Engine.Bot;
import Engine.BotLogger;
import Engine.FluentBot;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
@Listeners({Listener.IInvokedMethodResultListener.class, Listener.ITestResultListener.class})

public class BaseTestFluent {
        protected FluentBot bot;

        @BeforeMethod
        public void setUp() {
            //open Browser
            //
            BotLogger.info("Chrome driver is opened");
            DriverFactory.setupDriver("chrome");
            WebDriver driver = DriverFactory.getDriver();
            bot = new FluentBot(driver);
            BotLogger.info("Bot is initialized");
            BotLogger.info("Navigating to Login Page...");
        }

        @AfterMethod
        public void tearDown() {
            //close Browser
            BotLogger.info("Closing browser...");
            DriverFactory.quitDriver();
            BotLogger.info("Browser closed");
        }
    }
