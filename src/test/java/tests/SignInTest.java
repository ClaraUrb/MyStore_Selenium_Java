package tests;

import components.Header;
import components.MyAccountSection;
import components.SignInSection;
import models.User;
import org.testng.annotations.Test;
import testComponents.BaseTest;

public class SignInTest extends BaseTest {

    @Test
    public void registerTest() {
        Header header = new Header(driver);
        header.openSignInPage();
        SignInSection signIn = new SignInSection(driver);
        User user = signIn.createNewAccount();
        header.openMyCustomerAccount();
        MyAccountSection myAccountSection = new MyAccountSection(driver);
        myAccountSection.openInfoSection();
        myAccountSection.verifyPersonalInfo(user);
    }

    @Test
    public void signInTest() {
        Header header = new Header(driver);
        header.openSignInPage();
        SignInSection signIn = new SignInSection(driver);
        User user = signIn.logInUsingExistingUser();
        header.verifyNameInMyCustomerAccount(user);
        MyAccountSection myAccountSection = new MyAccountSection(driver);
        myAccountSection.openInfoSection();
        myAccountSection.verifyPersonalInfo(user);
    }
}
