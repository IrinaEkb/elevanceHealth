package utils;

import org.openqa.selenium.WebDriver;

public class DriverFactory {
    private static final ThreadLocal<WebDriver> tlDriver = new ThreadLocal<>();
    // 🔧 DriverFactory helps run tests in parallel without browser conflicts.
    // It uses ThreadLocal to make sure each test runs in its own browser window.

    // getDriver(): Returns the WebDriver for the current test thread.
    public static WebDriver getDriver() {
        return tlDriver.get();
    }
    // setDriver(driver): Stores the WebDriver for the current test thread.
    public static void setDriver(WebDriver driver) {
        tlDriver.set(driver);
    }
    // unload(): Cleans up the WebDriver after the test to prevent memory issues.
    public static void unload() {
        tlDriver.remove();
    }
}

