package Engine.Bot;

import io.qameta.allure.Allure;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.List;

import static java.awt.SystemColor.text;

public class Bot {
    private final WebDriver driver;
    private final Wait<WebDriver> wait;

    public Bot() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized", "--guest", "--disable-notifications");
        driver = new ChromeDriver(options);

        wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(15))
                .pollingEvery(Duration.ofMillis(300))
                .ignoring(ElementNotInteractableException.class)
                .ignoring(NoSuchElementException.class);
    }

    public void navigateTo(String url) {
        driver.navigate().to(url);
        BotLogs.info("Navigated to: " + url);
    }

    public void click(By locator) {
        wait.until(d -> {
            d.findElement(locator).click();
            return true;
        });
        BotLogs.info("Clicked on element: " + locator);
    }

    public void type(By locator, String text) {
        wait.until(d -> {
            WebElement element = d.findElement(locator);
            element.clear();
            element.sendKeys(text);
            return true;
        });
        BotLogs.info("Typed text: " + text + "' into element with locator: " + locator.toString());

    }

    public String getText(By locator) {
        return wait.until(d -> {
            WebElement element = d.findElement(locator);
            new Actions(d).scrollToElement(element).perform();
            String text = element.getText();
            return (text != null && !text.trim().isEmpty()) ? text : null;
        });

    }

    public boolean isElementDisplayed(By locator) {
        Boolean result = wait.until(d -> {
            try {
                return d.findElement(locator).isDisplayed();
            } catch (Exception e) {
                return false;
            }
        });
        BotLogs.info("Element displayed status for locator " + locator +": " + result);
        return result != null && result;
    }

    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    public String getPageTitle() {
        return driver.getTitle();
    }

    public void scrollToElement(By locator) {
        wait.until(d -> {
            WebElement element = d.findElement(locator);
            new Actions(d).scrollToElement(element).perform();
            return true;
        });
    }
    public WebElement find(By locator) {
        return wait.until(d -> d.findElement(locator));
    }

    public List<WebElement> findAll(By locator) {
        return wait.until(d -> d.findElements(locator));
    }


    public static String getTimestamp() {
        return new SimpleDateFormat("yyyy-MM-h-m-ssa").format(new Date());
    }
    private static final String SCREENSHOTS_PATH = "test-outputs/Screenshots/";
    public void takeScreenShot(WebDriver driver, String screenshotName) {
        try {
            // Capture screenshot using TakeScreenshot
            File screenshotSrc = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            // Save screenshot to a file if needed
            File screenshotFile = new File(SCREENSHOTS_PATH + screenshotName + "-" + getTimestamp() + ".png");
            FileUtils.copyFile(screenshotSrc, screenshotFile);
            // Attach the screenshot to Allure
            Allure.addAttachment(screenshotName, Files.newInputStream(Path.of(screenshotFile.getPath())));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public List<WebElement> getElements(By locator) {
        return wait.until(driver -> driver.findElements(locator));
    }
    public WebDriver getDriver() {
        return driver;
    }

    public void quit() {
        driver.quit();
    }
}