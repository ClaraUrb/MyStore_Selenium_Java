package components;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import java.util.HashMap;

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

    public void verifyPersonalInfo(HashMap<String, String> userData) {
        CreateAccountSection createAccountSection = new CreateAccountSection(driver);
        boolean isMale = createAccountSection.mr.isSelected();
        boolean isFemale = createAccountSection.mrs.isSelected();
        if (userData.get("socialTitle").equals("Mr")) {
            Assert.assertTrue(isMale);
        } else if (userData.get("socialTitle").equals("Ms")) {
            Assert.assertTrue(isFemale);
        }
        Assert.assertEquals(createAccountSection.firstName.getAttribute("value"), userData.get("firstName"));
        Assert.assertEquals(createAccountSection.lastName.getAttribute("value"), userData.get("lastName"));
        Assert.assertEquals(createAccountSection.email.getAttribute("value"), userData.get("email"));
        Assert.assertEquals(createAccountSection.birthdate.getAttribute("value"), userData.get("birthdate"));
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
