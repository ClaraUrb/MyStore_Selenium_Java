package components;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import userGenerator.UserGenerator;

import java.util.HashMap;

@Slf4j
public class CreateAccountSection {
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

    public CreateAccountSection(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public HashMap<String, String> createAccount() {
        UserGenerator generator = new UserGenerator();
        HashMap<String, String> userData = generator.generateUser();
        tickSocialTitle(userData.get("socialTitle"));
        firstName.sendKeys(userData.get("firstName"));
        lastName.sendKeys(userData.get("lastName"));
        email.sendKeys(userData.get("email"));
        password.sendKeys(userData.get("password"));
        showPasswordButton.click();
        Assert.assertEquals(password.getAttribute("value"), userData.get("password"));
        birthdate.sendKeys(userData.get("birthdate"));
        customerDataPrivacyRadio.click();
        termsAndConditionsRadio.click();
        showPasswordButton.click();
        saveButton.click();
        return userData;
    }

    private void tickSocialTitle(String socialTitle) {
        switch (socialTitle) {
            case "Mr" -> mr.click();
            case "Mrs" -> mrs.click();
            default -> log.info("Social title is empty");
        }
    }
}
