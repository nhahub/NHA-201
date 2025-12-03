package SauceDemoTests;
import Base.BaseTest;
import DataDrivenTest.TestDataProvider;
import SauceDemoPages.LoginPage;
import SauceDemoPages.SortingFilterPage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.util.List;

public class SortingFilterPageTest extends BaseTest {
    private SortingFilterPage sortingPage;

    @BeforeMethod
    public void setup() {
        LoginPage loginPage = new LoginPage(bot);
        sortingPage = new SortingFilterPage(bot);

        loginPage.navigateToLoginPage();
        loginPage.loginAsStandardUser();
    }
    @Test(dataProvider = "sortOptions", dataProviderClass = TestDataProvider.class)
    public void sortingTest(String option) {

        // Product names at default status "Expected Result"
        List<String> defaultSort = sortingPage.getProductNames();
        // Select option
        sortingPage.selectSortingOption(option);
        //Actual Result
        List<String> actualSort = sortingPage.getProductNames();
        //Assertation of products size
        Assert.assertEquals(actualSort.size(), defaultSort.size(), "Number of products changed!");

        if(option.equals("Name (A to Z)")) {
            Assert.assertTrue(defaultSort.getFirst().compareTo(actualSort.getFirst()) <= 0,
                    "Products not sorted correctly by Name (A to Z)");
        }
    }
}

