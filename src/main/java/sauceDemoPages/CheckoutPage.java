
package SauceDemoPages;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import java.time.Duration;

public class CheckoutPage {
    // Locators
    private By firstName = By.id("first-name");
    private By lastName = By.id("last-name");
    private By zipCode = By.id("postal-code");
    private By continueButton = By.id("continue");

    private WebDriver driver;
    private Wait<WebDriver> wait;

    // Constructor
    public CheckoutPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(10))
                .pollingEvery(Duration.ofMillis(200));
    }

    public void fillFirstName(String userFirstName) {
        wait.until(d -> d.findElement(firstName)).sendKeys(userFirstName);
    }

    public void fillLastName(String userLastName) {
        wait.until(d -> d.findElement(lastName)).sendKeys(userLastName);
    }

    public void fillZipCode(String userZipCode) {
        wait.until(d -> d.findElement(zipCode)).sendKeys(userZipCode);
    }

    public void clickContinue() {
        wait.until(d -> d.findElement(continueButton)).click();
    }
}