package base;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import utils.DriverFactory;

public class BaseTest {
    // Abstraction: We simplify the WebDriver setup process by hiding the details.
    @BeforeMethod
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();

        // Encapsulation: We are storing and managing the driver in one place (DriverFactory),
        // so the test doesn’t need to worry about handling it directly. It’s like keeping everything organized.
        DriverFactory.setDriver(driver);

        driver.manage().window().maximize();
        driver.get("https://your-test-url.com"); // можно заменить позже
    }
    // Abstraction: We handle cleanup of the WebDriver so the test doesn’t need to worry about it.
    @AfterMethod
    public void tearDown() {
        if (DriverFactory.getDriver() != null) {
            DriverFactory.getDriver().quit();
            DriverFactory.unload();
        }
    }
}