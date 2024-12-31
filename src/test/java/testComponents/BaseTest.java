package testComponents;

import factories.DriverFactory;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.IOException;

@Slf4j
public class BaseTest extends DriverFactory {
    public WebDriver driver;
    private static final String URL = "http://146.59.32.4/index.php";

    @BeforeMethod
    public void startTest() throws IOException {
        driver = initializeDriver();
        driver.get(URL);
        log.info("Landing page has been opened");
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
        log.info("Driver has been closed");
    }
}
