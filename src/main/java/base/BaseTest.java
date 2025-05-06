package base;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import utils.DriverFactory;
import org.apache.commons.io.FileUtils;
import org.testng.ITestResult;

import java.io.File;
import java.io.IOException;

public class BaseTest {

    @BeforeMethod
    public void setUp() {
        // Make sure WebDriver is initialized before using it
        WebDriver driver = DriverFactory.getDriver();
        driver.manage().window().maximize();  // Maximize the window for each test
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        // If the driver is not null, quit it
        if (DriverFactory.getDriver() != null) {
            // Take a screenshot only if the test failed
            if (result.getStatus() == ITestResult.FAILURE) {
                takeScreenshot(result);  // Passing the result to get information about the failed test
            }

            // Quit the driver and clean up
            DriverFactory.getDriver().quit();
            DriverFactory.unload();
        }
    }

    // Method to take a screenshot
    private void takeScreenshot(ITestResult result) {
        // Create a unique file name for the screenshot based on the current time and test method name
        String fileName = "screenshot_" + result.getMethod().getMethodName() + "_" + System.currentTimeMillis() + ".png";

        // Get the driver instance
        WebDriver driver = DriverFactory.getDriver();

        // Take a screenshot and store it in a temporary file
        File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

        try {
            // Save the screenshot to the "screenshots" folder
            FileUtils.copyFile(screenshotFile, new File("screenshots/" + fileName));
            System.out.println("Screenshot saved as: " + fileName);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Failed to save screenshot: " + e.getMessage());
        }
    }
}