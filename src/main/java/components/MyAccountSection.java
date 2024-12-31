package components;

import models.User;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class MyAccountSection {
    private WebDriver driver;

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
            Assert.assertTrue(isMale);
        } else if (user.getSocialTitle().equals("Ms")) {
            Assert.assertTrue(isFemale);
        }
        Assert.assertEquals(createAccountSection.firstName.getAttribute("value"), user.getFirstName());
        Assert.assertEquals(createAccountSection.lastName.getAttribute("value"), user.getLastName());
        Assert.assertEquals(createAccountSection.email.getAttribute("value"), user.getEmail());
        Assert.assertEquals(createAccountSection.birthdate.getAttribute("value"), user.getBirthdate());
        testShowPasswordButtons();
    }

    private void testShowPasswordButtons() {
        CreateAccountSection createAccountSection = new CreateAccountSection(driver);
        Assert.assertEquals(createAccountSection.showPasswordButton.getText(), "SHOW");
        Assert.assertEquals(createAccountSection.showNewPasswordButton.getText(), "SHOW");
        createAccountSection.showPasswordButton.click();
        Assert.assertEquals(createAccountSection.showPasswordButton.getText(), "HIDE");
        createAccountSection.showNewPasswordButton.click();
        Assert.assertEquals(createAccountSection.showNewPasswordButton.getText(), "HIDE");
    }
}
