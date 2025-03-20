package components;

import io.qameta.allure.Step;
import lombok.extern.slf4j.Slf4j;
import models.User;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

@Slf4j
public class MyAccountPage {

    private WebDriver driver;
    public String socialTitle;

    @FindBy(id = "identity-link")
    private WebElement accountInfo;

    @FindBy(id = "address-link")
    private WebElement addressInfo;

    @FindBy(id = "addresses-link")
    private WebElement addressesInfo;

    public MyAccountPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @Step("Open info section")
    public void openInfoSection() {
        accountInfo.click();
    }

    @Step("Open address section")
    public void openAddressSection() {
        try {
            addressInfo.click();
        } catch (NoSuchElementException e) {
            addressesInfo.click();
        }
    }

    public User readUserData() {
        CreateAccountPage createAccountPage = new CreateAccountPage(driver);
        User.UserBuilder user = User.builder();
        if (createAccountPage.mr.isSelected()) {
            socialTitle = "Mr";
        } else if (createAccountPage.mrs.isSelected()) {
            socialTitle = "Mrs";
        }
        user
                .socialTitle(socialTitle)
                .firstName(createAccountPage.firstName.getAttribute("value"))
                .lastName(createAccountPage.lastName.getAttribute("value"))
                .email(createAccountPage.email.getAttribute("value"))
                .birthdate(createAccountPage.birthdate.getAttribute("value"));
        return user.build();
    }
}
