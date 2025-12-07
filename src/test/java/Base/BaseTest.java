package Base;
import java.io.IOException;
import static Drivers.DriverFactory.getDriver;
import static Drivers.DriverFactory.setupDriver;
import static Engine.BotData.getProperty;
import Drivers.DriverFactory;
import Engine.Bot;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
@Listeners({Listener.IInvokedMethodResultListener.class, Listener.ITestResultListener.class})
public class BaseTest {
    protected Bot bot;

    @BeforeMethod
    public void setUp() throws IOException {
        //open Browser
        setupDriver(getProperty("environment", "Browser"));
        getDriver().get(getProperty("environment", "LOGIN_URL"));
        WebDriver driver = DriverFactory.getDriver();
        bot = new Bot(driver);
    }

    @AfterMethod
    public void tearDown() {
        //close Browser
        DriverFactory.quitDriver();
    }
}