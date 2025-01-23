package components;

import factories.UserFactory;
import lombok.extern.slf4j.Slf4j;
import models.User;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

@Slf4j
public class CreateAccountPage {
    private WebDriver driver;

    @FindBy(css = "input[name='id_gender'][value='1']")
    public WebElement mr;

    @FindBy(css = "input[name='id_gender'][value='2']")
    public WebElement mrs;

    @FindBy(name = "firstname")
    public WebElement firstName;

    @FindBy(name = "lastname")
    public WebElement lastName;

    @FindBy(name = "email")
    public WebElement email;

    @FindBy(name = "password")
    public WebElement password;

    @FindBy(xpath = "//input[@name='password']/following-sibling::span/button")
    public WebElement showPasswordButton;

    @FindBy(xpath = "//input[@name='new_password']/following-sibling::span/button")
    public WebElement showNewPasswordButton;

    @FindBy(name = "birthday")
    public WebElement birthdate;

    @FindBy(name = "customer_privacy")
    public WebElement customerDataPrivacyRadio;

    @FindBy(name = "psgdpr")
    public WebElement termsAndConditionsRadio;

    @FindBy(css = "footer button[type='submit']")
    public WebElement saveButton;

    public CreateAccountPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public User createAccount() {
        User user = UserFactory.createRandomUser();
        tickSocialTitle(user.getSocialTitle());
        firstName.sendKeys(user.getFirstName());
        lastName.sendKeys(user.getLastName());
        email.sendKeys(user.getEmail());
        password.sendKeys(user.getPassword());
        showPasswordButton.click();
        Assert.assertEquals(password.getAttribute("value"), user.getPassword());
        birthdate.sendKeys(user.getBirthdate());
        customerDataPrivacyRadio.click();
        termsAndConditionsRadio.click();
        saveButton.click();
        return user;
    }

    private void tickSocialTitle(String socialTitle) {
        switch (socialTitle) {
            case "Mr" -> mr.click();
            case "Mrs" -> mrs.click();
            default -> log.info("Social title is empty");
        }
    }
}
