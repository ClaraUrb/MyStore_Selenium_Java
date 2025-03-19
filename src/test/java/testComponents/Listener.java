package testComponents;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Slf4j
public class Listener extends BaseTest implements ITestListener {
    @Override
    public void onTestStart(ITestResult result) {
        log.info("*** Starting Test *** Test Name -> {}", result.getName());
    }

    @Override
    public void onTestFailure(ITestResult result) {
        log.info("*** Test Failed *** Test Name -> {}", result.getName());
        LocalDateTime timestamp = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss");
        String screenshotName = "FAILED_" + result.getName() + "_" + formatter.format(timestamp);
        try {
            driver = (WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
        } catch (NoSuchFieldException | IllegalAccessException exception) {
            throw new RuntimeException(exception);
        }
        File file = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(file, new File(System.getProperty("user.dir") + "./screenshots/" + screenshotName + ".png"));
            log.info("Screenshot is successfully created");
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }
}
