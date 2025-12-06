package Base;
import Engine.BotLogger;
import java.time.Duration;
import static Drivers.DriverFactory.getDriver;
import Drivers.DriverFactory;
import Engine.Bot;
import Engine.BotLogger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
@Listeners({Listener.IInvokedMethodResultListener.class, Listener.ITestResultListener.class})
public class BaseTest {
    protected Bot bot;

    @BeforeMethod
    public void setUp() {
        //open Browser
        DriverFactory.setupDriver("chrome");
        WebDriver driver = DriverFactory.getDriver();
        bot = new Bot(driver);
    }

    @AfterMethod
    public void tearDown() {
        //close Browser
        DriverFactory.quitDriver();
    }
}