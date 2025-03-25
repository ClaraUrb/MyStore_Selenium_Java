package components;

import helpers.DataSaver;
import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import models.User;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SignInPage extends BasePage {

    @FindBy(css = "input[name=\'email\'][class=\'form-control\']")
    public WebElement emailInput;

    @FindBy(name = "password")
    public WebElement passwordInput;

    @FindBy(id = "submit-login")
    public WebElement signInButton;

    @FindBy(css = "div[class='no-account'] a")
    public WebElement newAccountButton;

    public SignInPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @Step("Create new account")
    public User createNewAccount() {
        newAccountButton.click();
        CreateAccountPage createAccountPage = new CreateAccountPage(driver);
        return createAccountPage.createAccount();
    }

    @Step("Log in using existing user")
    public User logInUsingExistingUser() {
        User user = DataSaver.readUserDataFromFile();
        Allure.addAttachment("User data: ", user.toString());
        emailInput.sendKeys(user.getEmail());
        passwordInput.sendKeys(user.getPassword());
        signInButton.click();
        return user;
    }
}
