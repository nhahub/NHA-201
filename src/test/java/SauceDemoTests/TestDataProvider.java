
package SauceDemoTests;

import org.testng.annotations.DataProvider;

public class TestDataProvider {

    @DataProvider(name = "validLoginData")
    public Object[][] provideValidLoginData() {
        return new Object[][]{
                {"standard_user", "secret_sauce"},
                {"problem_user", "secret_sauce"},
                {"performance_glitch_user", "secret_sauce"}
        };
    }

    @DataProvider(name = "invalidLoginData")
    public Object[][] provideInvalidLoginData() {
        return new Object[][]{
                {"invalid_user", "secret_sauce"},
                {"wrong_user", "wrong_password"},
                {"fake_user", "fake_pass"}
        };
    }

    @DataProvider(name = "emptyFieldsData")
    public Object[][] provideEmptyFieldsData() {
        return new Object[][]{
                {"", "secret_sauce"},
                {"standard_user", ""},
                {"", ""}
        };
    }

    @DataProvider(name = "lockedUserData")
    public Object[][] provideLockedUserData() {
        return new Object[][]{
                {"locked_out_user", "secret_sauce"}
        };
    }

    // 1) Valid checkout data: Group / Project / 201
    @DataProvider(name = "checkoutValidData")
    public Object[][] provideCheckoutValidData() {
        return new Object[][]{
                {"Group", "Project", "201"}
        };
    }

    // 2) Empty first name
    @DataProvider(name = "checkoutEmptyFirstName")
    public Object[][] provideCheckoutEmptyFirstName() {
        return new Object[][]{
                {"", "Project", "201"}
        };
    }

    // 3) Empty zipcode
    @DataProvider(name = "checkoutEmptyZip")
    public Object[][] provideCheckoutEmptyZip() {
        return new Object[][]{
                {"Group", "Project", ""}
        };
    }
}