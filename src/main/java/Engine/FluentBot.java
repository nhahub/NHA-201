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

public class FluentBot {
    private final WebDriver driver;
    private final Wait<WebDriver> wait;
    private static final String SCREENSHOTS_PATH = "test-output/Screenshots/";

    //Constructor
    public FluentBot(WebDriver driver) {
        this.driver = driver;
        this.wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(15))
                .pollingEvery(Duration.ofMillis(300))
                .ignoring(ElementNotInteractableException.class)
                .ignoring(NoSuchElementException.class);
    }

    public FluentBot(WebDriver driver, Wait<WebDriver> wait) {
        this.driver = driver;
        this.wait = wait;
    }

    public FluentBot navigateTo(String url) {
            driver.navigate().to(url);
            return this;
        }

        public FluentBot click(By locator) {
            wait.until(d -> {
                d.findElement(locator).click();
                return true;
            });
            return this;
        }

        public FluentBot type(By locator, String text) {
            wait.until(d -> {
                WebElement element = d.findElement(locator);
                element.clear();
                element.sendKeys(text);
                return true;
            });
            return this;
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
                    return false;  // FluentWait will retry automatically
                }
            });
            return result != null && result;
        }

        public String getCurrentUrl() {
            return driver.getCurrentUrl();
        }

        public String getPageTitle() {
            return driver.getTitle();
        }

        public static String getTimestamp() {
        return new SimpleDateFormat("yyyy-MM-h-m-ssa").format(new Date());
        }
        public FluentBot takeScreenShot(WebDriver driver, String screenshotName) {
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
        return this;
    }
}