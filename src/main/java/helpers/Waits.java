package helpers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Waits {
    private WebDriver driver;
    private static WebDriverWait wait;

    @FindBy(css = "div[class=\"overlay__inner\"")
    private static WebElement overlay;

    public Waits(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        PageFactory.initElements(driver, this);
    }

    public void waitToLoad() {
        wait.until(ExpectedConditions.invisibilityOf(overlay));
    }
}
