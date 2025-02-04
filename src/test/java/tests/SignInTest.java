package tests;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import testComponents.BaseTest;

import static org.assertj.core.api.Assertions.assertThat;

public class SignInTest extends BaseTest {
    SoftAssert softAssert = new SoftAssert();

    @Test
    public void registerTest() {
        headerPage.openSignInPage();
        user = signInPage.createNewAccount();
        headerPage.openMyCustomerAccount();
        myAccountPage.openInfoSection();
        registeredUser = myAccountPage.readUserData();
//        softAssert.assertEquals(user, registeredUser);
        assertThat(user).usingRecursiveComparison().ignoringFields(user.password, registeredUser.password).isEqualTo(registeredUser);


        softAssert.assertEquals(createAccountPage.showPasswordButton.getText(), "SHOW");
        softAssert.assertEquals(createAccountPage.showNewPasswordButton.getText(), "SHOW");

        createAccountPage.showPasswordButton.click();
        softAssert.assertEquals(createAccountPage.showPasswordButton.getText(), "HIDE");

        createAccountPage.showNewPasswordButton.click();
        softAssert.assertEquals(createAccountPage.showNewPasswordButton.getText(), "HIDE");
        softAssert.assertAll();
    }

    @Test
    public void signInTest() {
        headerPage.openSignInPage();
        user = signInPage.logInUsingExistingUser();
        headerPage.verifyNameInMyCustomerAccount(user);
        myAccountPage.openInfoSection();
        registeredUser = myAccountPage.readUserData();
        softAssert.assertEquals(user, registeredUser);
        softAssert.assertAll();
    }
}
