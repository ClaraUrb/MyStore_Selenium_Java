package components;

import models.User;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.asserts.SoftAssert;

public class MyAccountSection {
    private WebDriver driver;
    SoftAssert softAssert = new SoftAssert();

    @FindBy(id = "identity-link")
    private WebElement accountInfo;

    public MyAccountSection(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void openInfoSection() {
        accountInfo.click();
    }

    public void verifyPersonalInfo(User user) {
        CreateAccountSection createAccountSection = new CreateAccountSection(driver);
        boolean isMale = createAccountSection.mr.isSelected();
        boolean isFemale = createAccountSection.mrs.isSelected();
        if (user.getSocialTitle().equals("Mr")) {
            softAssert.assertTrue(isMale);
        } else if (user.getSocialTitle().equals("Ms")) {
            softAssert.assertTrue(isFemale);
        }
        softAssert.assertEquals(createAccountSection.firstName.getAttribute("value"), user.getFirstName());
        softAssert.assertEquals(createAccountSection.lastName.getAttribute("value"), user.getLastName());
        softAssert.assertEquals(createAccountSection.email.getAttribute("value"), user.getEmail());
        softAssert.assertEquals(createAccountSection.birthdate.getAttribute("value"), user.getBirthdate());
        testShowPasswordButtons();
        softAssert.assertAll();
    }

    private void testShowPasswordButtons() {
        CreateAccountSection createAccountSection = new CreateAccountSection(driver);

        softAssert.assertEquals(createAccountSection.showPasswordButton.getText(), "SHOW");
        softAssert.assertEquals(createAccountSection.showNewPasswordButton.getText(), "SHOW");

        createAccountSection.showPasswordButton.click();
        softAssert.assertEquals(createAccountSection.showPasswordButton.getText(),"HIDE");

        createAccountSection.showNewPasswordButton.click();
        softAssert.assertEquals(createAccountSection.showNewPasswordButton.getText(), "HIDE");
    }
}
