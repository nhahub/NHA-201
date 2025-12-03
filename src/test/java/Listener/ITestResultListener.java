package Listener;

import Engine.BotLogger;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class ITestResultListener implements ITestListener {
    public void onTestStart(ITestResult result) {
        BotLogger.info("Test Case" + result.getName() + "started");
    }

    public void onTestSuccess(ITestResult result) {
        BotLogger.info("Test Case" + result.getName() + "passed");
    }

    public void onTestFailure(ITestResult result) {
        BotLogger.info("Test Case" + result.getName() + "failed");
    }

    public void onTestSkipped(ITestResult result) {
        BotLogger.info("Test Case" + result.getName() + "skipped");
    }

}
