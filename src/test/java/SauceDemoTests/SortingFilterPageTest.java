package SauceDemoTests;

import Base.BaseTest;
import DataDrivenTest.TestDataProvider;
import Engine.Bot.Bot;
import SauceDemoPages.LoginPage;
import SauceDemoPages.SortingFilterPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SortingFilterPageTest extends BaseTest {
    private LoginPage loginPage;
    private SortingFilterPage sortingPage;

    @BeforeMethod
    public void setup() {
        loginPage = new LoginPage(bot);
        sortingPage = new SortingFilterPage(bot);

        loginPage.navigateToLoginPage();
        loginPage.loginAsStandardUser();
    }
    @Test(dataProvider = "sortOptions", dataProviderClass = TestDataProvider.SortingDataProvider.class)
    public void sortingTest(String option) {
        // Product names at default status "Expected Result"
        List<String> beforeSort = sortingPage.getProductNames();

        // Select option
        sortingPage.selectSortingOption(option);
        //Actual Result
        List<String> afterSort = sortingPage.getProductNames();
        //Assertation of products size
        Assert.assertEquals(afterSort.size(), beforeSort.size(), "Number of products changed!");

        if(option.equals("Name (A to Z)")) {
            Assert.assertTrue(beforeSort.get(0).compareTo(afterSort.get(0)) <= 0,
                    "Products not sorted correctly by Name (A to Z)");
        }
    }
}

