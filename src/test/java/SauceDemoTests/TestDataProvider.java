package SauceDemoTests;

import org.testng.annotations.DataProvider;

public class TestDataProvider {

    @DataProvider(name = "validLoginData")
    public Object[][] provideValidLoginData() {
        return new Object[][] {
                {"standard_user", "secret_sauce"},
                {"problem_user", "secret_sauce"},
                {"performance_glitch_user", "secret_sauce"}
        };
    }

    @DataProvider(name = "invalidLoginData")
    public Object[][] provideInvalidLoginData() {
        return new Object[][] {
                {"invalid_user", "secret_sauce"},
                {"wrong_user", "wrong_password"},
                {"fake_user", "fake_pass"}
        };
    }

    @DataProvider(name = "emptyFieldsData")
    public Object[][] provideEmptyFieldsData() {
        return new Object[][] {
                {"", "secret_sauce"},
                {"standard_user", ""},
                {"", ""}
        };
    }

    @DataProvider(name = "lockedUserData")
    public Object[][] provideLockedUserData() {
        return new Object[][] {
                {"locked_out_user", "secret_sauce"}
        };
    }

    @DataProvider(name = "checkoutData")
    public Object[][] provideCheckoutData() {
        return new Object[][] {
                {"First Name", "Last Name", "10000"},
                {"Ahmed", "Ali", "12345"},
                {"John", "Doe", "99999"}
        };
    }

    @DataProvider(name = "emptyCheckoutFieldsData")
    public Object[][] provideEmptyCheckoutFieldsData() {
        return new Object[][] {
                {"", "Last Name", "10000"},
                {"First Name", "", "10000"},
                {"First Name", "Last Name", ""}
        };
    }
}