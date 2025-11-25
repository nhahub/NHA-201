package Engine.Bot;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class ActionsBot {
    private final WebDriver driver;
    private final WaitBots waitBots;

    public ActionsBot(WebDriver driver) {
        this.driver = driver;
        this.waitBots = new WaitBots(driver);
    }

    // Action Methods

    public void clicking(By locator) {
        waitBots.fluentWait().until(d -> {
            try {
                WebElement element = d.findElement(locator);
                new Actions(d).scrollToElement(element).perform();
                element.click();
                return true;
            } catch (Exception e) {
                return false;
            }
        });
    }

    public void typing(By locator, String text) {
        waitBots.fluentWait().until(d -> {
            try {
                WebElement element = d.findElement(locator);
                new Actions(d).scrollToElement(element).perform();
                element.clear();
                element.sendKeys(text);
                return true;
            } catch (Exception e) {
                return false;
            }
        });
    }

    public String getText(By locator) {
        return waitBots.fluentWait().until(d -> {
            try {
                WebElement element = d.findElement(locator);
                new Actions(d).scrollToElement(element).perform();
                String textMsg = element.getText();
                return !textMsg.isEmpty() ? textMsg : null;
            } catch (Exception e) {
                return null;
            }
        });
    }

    public boolean isElementDisplayed(By locator) {
        try {
            return waitBots.fluentWait().until(d -> {
                try {
                    return d.findElement(locator).isDisplayed();
                } catch (Exception e) {
                    return false;
                }
            });
        } catch (Exception e) {
            return false;
        }
    }

    public void navigateTo(String url) {
        driver.navigate().to(url);
    }

    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    public String getPageTitle() {
        return driver.getTitle();
    }
}