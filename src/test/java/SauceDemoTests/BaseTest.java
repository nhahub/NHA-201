package SauceDemoTests;

import Engine.Bot.Bot;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class BaseTest {

    protected Bot bot;

    @BeforeMethod
    public void setUp() {
        //open Browser
        bot = new Bot();
    }

    @AfterMethod
    public void tearDown() {
        //close Browser
        bot.quit();
    }
}