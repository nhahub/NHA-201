package Engine.Bot;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Bot {
    private final FluentBot fluentBot;

    public Bot() {
        fluentBot = new FluentBot();
    }

    public Bot navigateTo(String url) {
        fluentBot.navigateTo(url);
        return this;
    }

    public Bot click(By locator) {
        fluentBot.click(locator);
        return this;
    }

    public Bot type(By locator, String text) {
        fluentBot.type(locator, text);
        return this;
    }

    public String getText(By locator) {
        return fluentBot.getText(locator);
    }

    public boolean isElementDisplayed(By locator) {
        return fluentBot.isElementDisplayed(locator);
    }

    public String getCurrentUrl() {
        return fluentBot.getCurrentUrl();
    }

    public String getPageTitle() {
        return fluentBot.getPageTitle();
    }

    public WebDriver getDriver() {
        return fluentBot.getDriver();
    }

    public void quit() {
        fluentBot.quit();
    }
}