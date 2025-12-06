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
            DriverFactory.setupDriver("chrome");
            WebDriver driver = DriverFactory.getDriver();
            bot = new FluentBot(driver);
        }

        @AfterMethod
        public void tearDown() {
            //close Browser
            DriverFactory.quitDriver();
        }
    }
