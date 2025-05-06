package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver; // or any other WebDriver implementation
import java.util.concurrent.TimeUnit;

public class DriverFactory {
    private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    public static WebDriver getDriver() {
        // Initialize the WebDriver if it's null
        if (driver.get() == null) {
            // You can add browser-specific WebDriver setup here, for example:
            driver.set(new ChromeDriver());  // For Chrome; update for other browsers if needed
            driver.get().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        }
        return driver.get();
    }

    public static void setDriver(WebDriver driverInstance) {
        driver.set(driverInstance);
    }

    public static void unload() {
        driver.remove();
    }
}
