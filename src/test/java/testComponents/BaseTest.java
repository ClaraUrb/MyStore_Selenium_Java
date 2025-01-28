package testComponents;

import components.HeaderPage;
import components.MyAccountPage;
import components.SignInPage;
import factories.DriverFactory;
import lombok.extern.slf4j.Slf4j;
import models.User;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.IOException;

@Slf4j
public class BaseTest extends DriverFactory {
    public WebDriver driver;
    private static final String URL = "http://146.59.32.4/index.php";

    public HeaderPage headerPage;
    public SignInPage signInPage;
    public MyAccountPage myAccountPage;
    public User user;

    @BeforeMethod
    public void startTest() throws IOException {
        driver = initializeDriver();
        getTestObjects();
        driver.get(URL);
        log.info("Landing page has been opened");
    }

    public void getTestObjects() {
        headerPage = new HeaderPage(driver);
        signInPage = new SignInPage(driver);
        myAccountPage = new MyAccountPage(driver);
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
        log.info("Driver has been closed");
    }
}
