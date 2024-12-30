package tests;

import components.Header;
import components.MyAccountSection;
import components.SignInSection;
import org.testng.annotations.Test;
import testComponents.BaseTest;

import java.util.HashMap;

public class SignInTest extends BaseTest {

    @Test
    public void registerTest() {
        Header header = new Header(driver);
        header.openSignInPage();
        SignInSection signIn = new SignInSection(driver);
        HashMap<String, String> userData = signIn.createNewAccount();
        header.openMyCustomerAccount();
        MyAccountSection myAccountSection = new MyAccountSection(driver);
        myAccountSection.openInfoSection();
        myAccountSection.verifyPersonalInfo(userData);
    }

    @Test
    public void signInTest() {
        Header header = new Header(driver);
        header.openSignInPage();
    }
}
