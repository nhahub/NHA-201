package Engine;

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

public class Bot {
    private WebDriver driver;
    private Wait<WebDriver> wait;
    private static final String SCREENSHOTS_PATH = "test-output/Screenshots/";

    //  Constructor
    public Bot(WebDriver driver) {
        this.driver = driver;
        wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(15))
                .pollingEvery(Duration.ofMillis(300))
                .ignoring(ElementNotInteractableException.class)
                .ignoring(NoSuchElementException.class);
    }
    /*public Bot() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized", "--guest", "--disable-notifications");
        driver = new ChromeDriver(options);

        wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(15))
                .pollingEvery(Duration.ofMillis(300))
                .ignoring(ElementNotInteractableException.class)
                .ignoring(NoSuchElementException.class);
    }*/

    // Navigation Methods
    public void navigateTo(String url) {
        driver.navigate().to(url);
        BotLogger.info("Navigated to: " + url);
    }

    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    public String getPageTitle() {
        return driver.getTitle();
    }

    // Click Methods
    public void click(By locator) {
        wait.until(d -> {
            d.findElement(locator).click();
            return true;
        });
        BotLogger.info("Clicked on element: " + locator);
    }

    public void findAndClick(By locator) {
        wait.until(d -> {
            d.findElement(locator).click();
            return true;
        });
        BotLogger.info("Found and clicked on element: " + locator);
    }

    // Type / Input Methods
    public void type(By locator, String text) {
        wait.until(d -> {
            WebElement element = d.findElement(locator);
            element.clear();
            element.sendKeys(text);
            return true;
        });
        BotLogger.info("Typed text: " + text + " into element with locator: " + locator.toString());
    }

    //  Get Text Methods
    public String getText(By locator) {
        return wait.until(d -> {
            WebElement element = d.findElement(locator);
            new Actions(d).scrollToElement(element).perform();
            String text = element.getText();
            return (text != null && !text.trim().isEmpty()) ? text : null;
        });
    }

    public String findAndGetText(By locator) {
        return wait.until(d -> {
            WebElement element = d.findElement(locator);
            String text = element.getText();
            return (text != null && !text.trim().isEmpty()) ? text : null;
        });
    }

    // Element Display / Existence Methods
    public boolean isElementDisplayed(By locator) {
        Boolean result = wait.until(d -> {
            try {
                return d.findElement(locator).isDisplayed();
            } catch (Exception e) {
                return false;
            }
        });
        BotLogger.info("Element displayed status for locator " + locator + ": " + result);
        return result != null && result;
    }

    //  Scroll Methods
    public void scrollToElement(By locator) {
        wait.until(d -> {
            WebElement element = d.findElement(locator);
            new Actions(d).scrollToElement(element).perform();
            return true;
        });
        BotLogger.info("Scrolled to element: " + locator);
    }

    // Find Element Methods
    public WebElement find(By locator) {
        return wait.until(d -> d.findElement(locator));
    }

    public List<WebElement> findAll(By locator) {
        return wait.until(d -> d.findElements(locator));
    }


    public int countElements(By locator) {
        return wait.until(driver -> driver.findElements(locator).size());
    }
    public static String getTimestamp() {
        return new SimpleDateFormat("yyyy-MM-h-m-ssa").format(new Date());
    }

    // Screenshot Methods
    public static void takeScreenShot(WebDriver driver, String screenshotName) {
        try {
            File screenshotSrc = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            File screenshotFile = new File(SCREENSHOTS_PATH + screenshotName + "-" + getTimestamp() + ".png");
            FileUtils.copyFile(screenshotSrc, screenshotFile);
            Allure.addAttachment(screenshotName, Files.newInputStream(Path.of(screenshotFile.getPath())));
            BotLogger.info("Screenshot taken: " + screenshotName);
        } catch (Exception e) {
            BotLogger.error("Failed to take screenshot: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /*public WebDriver getDriver() {
        return driver;
    }

    public void quit() {
        driver.quit();
        BotLogger.info("Browser closed");
    }*/
}