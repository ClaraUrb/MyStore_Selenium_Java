package components;

import models.User;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class HeaderPage {
    private WebDriver driver;

    @FindBy(id = "header")
    private WebElement header;

    @FindBy(css = "div[class=\"user-info\"] a")
    private WebElement signIn;

    @FindBy(className = "account")
    private WebElement myCustomerAccount;

    public HeaderPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void openSignInPage() {
        signIn.click();
    }

    public void openMyCustomerAccount() {
        myCustomerAccount.click();
    }

    public void verifyNameInMyCustomerAccount(User user) {
        Assert.assertEquals(myCustomerAccount.getText(), user.getFirstName() + " " + user.getLastName());
    }
}
