package Listener;

import Engine.Bot;
import Engine.BotLogger;
import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ITestContext;
import org.testng.ITestResult;

import static Drivers.DriverFactory.getDriver;

public class IInvokedMethodResultListener implements IInvokedMethodListener {
    private static final String SCREENSHOTS_PATH = "test-output/Screenshots/";
    /*public void beforeInvocation(IInvokedMethod method, ITestResult testResult, ITestContext context) {
    }*/

    public void afterInvocation(IInvokedMethod method, ITestResult testResult, ITestContext context) {
        if (testResult.getStatus() == ITestResult.FAILURE) {
            BotLogger.info("TestCase  " + testResult.getName() + "  failed");
            Bot.takeScreenShot(getDriver(), testResult.getName());
        }
    }
}

