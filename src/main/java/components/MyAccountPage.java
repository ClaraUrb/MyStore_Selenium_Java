package components;

import models.User;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.asserts.SoftAssert;

public class MyAccountPage {
    private WebDriver driver;
    SoftAssert softAssert = new SoftAssert();

    @FindBy(id = "identity-link")
    private WebElement accountInfo;

    public MyAccountPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void openInfoSection() {
        accountInfo.click();
    }

    public void verifyPersonalInfo(User user) {
        CreateAccountPage createAccountPage = new CreateAccountPage(driver);
        boolean isMale = createAccountPage.mr.isSelected();
        boolean isFemale = createAccountPage.mrs.isSelected();
        if (user.getSocialTitle().equals("Mr")) {
            softAssert.assertTrue(isMale);
        } else if (user.getSocialTitle().equals("Ms")) {
            softAssert.assertTrue(isFemale);
        }
        softAssert.assertEquals(createAccountPage.firstName.getAttribute("value"), user.getFirstName());
        softAssert.assertEquals(createAccountPage.lastName.getAttribute("value"), user.getLastName());
        softAssert.assertEquals(createAccountPage.email.getAttribute("value"), user.getEmail());
        softAssert.assertEquals(createAccountPage.birthdate.getAttribute("value"), user.getBirthdate());
        testShowPasswordButtons();
        softAssert.assertAll();
    }

    private void testShowPasswordButtons() {
        CreateAccountPage createAccountPage = new CreateAccountPage(driver);

        softAssert.assertEquals(createAccountPage.showPasswordButton.getText(), "SHOW");
        softAssert.assertEquals(createAccountPage.showNewPasswordButton.getText(), "SHOW");

        createAccountPage.showPasswordButton.click();
        softAssert.assertEquals(createAccountPage.showPasswordButton.getText(),"HIDE");

        createAccountPage.showNewPasswordButton.click();
        softAssert.assertEquals(createAccountPage.showNewPasswordButton.getText(), "HIDE");
    }
}
