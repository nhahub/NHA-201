package Engine.Bot;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class WaitBots {
    private final WebDriver driver;

    public WaitBots(WebDriver driver) {
        this.driver = driver;
    }

    public WebElement waitForElement(By locator) {
        int attempts = 0;
        int maxAttempts = 15;

        while (attempts < maxAttempts) {
            try {
                WebElement element = driver.findElement(locator);
                if (element.isDisplayed()) {
                    return element;
                }
            } catch (Exception e) {
                // element not found or not displayed
            }

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }

            attempts++;
        }

        throw new RuntimeException("Element not found: " + locator);
    }
}