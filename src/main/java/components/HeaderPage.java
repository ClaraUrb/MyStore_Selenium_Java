package components;

import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import models.User;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class HeaderPage extends BasePage {

    @FindBy(id = "header")
    private WebElement header;

    @FindBy(css = "div[class=\"user-info\"] a")
    private WebElement signIn;

    @FindBy(className = "account")
    private WebElement myCustomerAccount;

    @FindBy(css = "[id=\"category-3\"] a[class=\"dropdown-item\"]")
    private WebElement clothes;

    @FindBy(css = "[id=\"category-6\"] a[class=\"dropdown-item\"]")
    private WebElement accessories;

    public HeaderPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @Step("Open SignIn page")
    public void openSignInPage() {
        signIn.click();
    }

    @Step("Open My Customer Account page")
    public void openMyCustomerAccount() {
        myCustomerAccount.click();
    }

    @Step("Open Clothes page")
    public void openClothesPage() {
        clothes.click();
    }

    @Step("Open Accessories page")
    public void openAccessoriesPage() {
        accessories.click();
    }

    @Step("Verify name in My Customer Account")
    public void verifyNameInMyCustomerAccount(User user) {
        Allure.addAttachment("Expected: ", user.getFirstName() + " " + user.getLastName());
        Allure.addAttachment("Actual: ", myCustomerAccount.getText());
        Assert.assertEquals(myCustomerAccount.getText(), user.getFirstName() + " " + user.getLastName());
    }
}
