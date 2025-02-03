package components;

import lombok.extern.slf4j.Slf4j;
import models.User;
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

    public MyAccountPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void openInfoSection() {
        accountInfo.click();
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
