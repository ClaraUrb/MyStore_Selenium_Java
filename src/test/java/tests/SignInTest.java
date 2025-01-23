package tests;

import org.testng.annotations.Test;
import testComponents.BaseTest;

public class SignInTest extends BaseTest {

    @Test
    public void registerTest() {
        headerPage.openSignInPage();
        user = signInPage.createNewAccount();
        headerPage.openMyCustomerAccount();
        myAccountPage.openInfoSection();
        myAccountPage.verifyPersonalInfo(user);
    }

    @Test
    public void signInTest() {
        headerPage.openSignInPage();
        user = signInPage.logInUsingExistingUser();
        headerPage.verifyNameInMyCustomerAccount(user);
        myAccountPage.openInfoSection();
        myAccountPage.verifyPersonalInfo(user);
    }
}
