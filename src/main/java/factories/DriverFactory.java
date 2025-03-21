package factories;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

@Slf4j
public class DriverFactory {
    public WebDriver driver;

    public WebDriver initializeDriver() throws IOException {
        Properties properties = new Properties();
        FileInputStream fileInputStream = new FileInputStream(System.getProperty("user.dir") + "\\src\\main\\resources\\config.properties");
        properties.load(fileInputStream);
        String browserName = properties.getProperty("browser");
        switch (browserName) {
            case "firefox" -> driver = new FirefoxDriver();
            case "chrome" -> {
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("--unsafely-treat-insecure-origin-as-secure=http://146.59.32.4/index.php");
                chromeOptions.addArguments("--disable-search-engine-choice-screen");
                driver = new ChromeDriver(chromeOptions);
            }
        }
        driver.manage().window().maximize();
        log.info("Driver has been initialized. Browser: \"{}\"", browserName);
        return driver;
    }
}
