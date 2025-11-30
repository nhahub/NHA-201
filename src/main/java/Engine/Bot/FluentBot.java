package Engine.Bot;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import java.time.Duration;

public class FluentBot {
    private final WebDriver driver;
    private final Wait<WebDriver> wait;

    public FluentBot() {
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--start-maximized", "--guest", "--disable-notifications");
            driver = new ChromeDriver(options);
            wait = new FluentWait<>(driver)
                    .withTimeout(Duration.ofSeconds(15))
                    .pollingEvery(Duration.ofMillis(300))
                    .ignoring(ElementNotInteractableException.class)
                    .ignoring(NoSuchElementException.class);
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

        public WebDriver getDriver() {
            return driver;
        }
//
        public void quit() {
            driver.quit();
        }
}