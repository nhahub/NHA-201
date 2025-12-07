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
        new LoginPage(bot).loginAsStandardUser();
        sortingPage = new SortingFilterPage(bot);
    }

    @Test(dataProvider = "sortOptions", dataProviderClass = TestDataProvider.class)
    public void sortingTest(String option) {

        List<String> actualSort = sortingPage.getProductNames();

        sortingPage.selectSortingOption(option);

        actualSort = sortingPage.getProductNames();

        Assert.assertEquals(actualSort.size(), actualSort.size(),
                "Number of products changed!");

        if (option.equals("Name (A to Z)")) {
            sortingPage.assertSortedAZ(actualSort);
        } else if (option.equals("Name (Z to A)")) {
            sortingPage.assertSortedZA(actualSort);
        }
    }
}