package components;

import models.User;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SignInSection {
    private WebDriver driver;

    @FindBy(css = "input[name=\'email\'][class=\'form-control\']")
    public WebElement emailInput;

    @FindBy(name = "password")
    public WebElement passwordInput;

    @FindBy(id = "submit-login")
    public WebElement signInButton;

    @FindBy(css = "div[class='no-account'] a")
    public WebElement newAccountButton;

    public SignInSection(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public User createNewAccount() {
        newAccountButton.click();
        CreateAccountSection createAccountSection = new CreateAccountSection(driver);
        return createAccountSection.createAccount();
    }
}
