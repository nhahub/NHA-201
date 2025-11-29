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

    public void clicking(By locator) {
        WebElement element = waitBots.waitForElement(locator);
        scrollToElement(element);
        element.click();
    }

    public void typing(By locator, String text) {
        WebElement element = waitBots.waitForElement(locator);
        scrollToElement(element);
        element.clear();
        element.sendKeys(text);
    }

    public String getText(By locator) {
        WebElement element = waitBots.waitForElement(locator);
        scrollToElement(element);
        return element.getText();
    }

    public boolean isElementDisplayed(By locator) {
        try {
            WebElement element = waitBots.waitForElement(locator);
            return element.isDisplayed();
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

    private void scrollToElement(WebElement element) {
        new Actions(driver).scrollToElement(element).perform();
    }
}