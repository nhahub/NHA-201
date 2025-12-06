package Drivers;

import Engine.BotLogger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

public class DriverFactory {

    private static ThreadLocal<WebDriver> driverThreadLocal = new ThreadLocal<>();

    public static void setupDriver(String browser) {
        switch (browser.toLowerCase()) {
            case "chrome":
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("--incognito");
                chromeOptions.addArguments("--start-maximized");
                driverThreadLocal.set(new ChromeDriver(chromeOptions));
                break;

            case "firefox":
                driverThreadLocal.set(new FirefoxDriver());
                break;

            default:
                EdgeOptions options = new EdgeOptions();
                options.addArguments("--start-maximized");
                driverThreadLocal.set(new EdgeDriver(options));
        }
    }

    public static WebDriver getDriver() {
        BotLogger.info("Browser is opened");
        return driverThreadLocal.get();
    }

    public static void quitDriver() {
        getDriver().quit();
        BotLogger.info("Browser closed");
        driverThreadLocal.remove();
    }
}