package base;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utils.DriverFactory;

public class BasePage {

    protected WebDriver driver; // Encapsulation: Hiding the WebDriver details from other classes

    // 🔧 DriverFactory provides WebDriver to avoid creating it every time.
    // All page classes will inherit from BasePage to reuse common actions like click, type, getText.
    // Constructor: This provides the WebDriver from DriverFactory.
    // Encapsulation: The driver is handled internally in BasePage and is not exposed to other classes directly.
    public BasePage() {
        this.driver = DriverFactory.getDriver();
    }
    // Encapsulation: Common actions like click, type, and getText are defined here,
    // so specific page classes (LoginPage, HomePage) don't need to know the implementation details.
    // This ensures reusability and reduces code redundancy.
    protected void click(By locator) {
        driver.findElement(locator).click();
    }
    // Encapsulation: Reusing this method for typing in text fields.
    // Specific page classes will pass their own locators and text values.
    protected void type(By locator, String text) {
        driver.findElement(locator).sendKeys(text);
    }
    // Encapsulation: Common method to get the text of an element on the page.
    // Page classes can use this method to verify content without repeating the logic.
    protected String getText(By locator) {
        return driver.findElement(locator).getText();
    }
}
//The By locator here is not tied to a specific element but is a placeholder that
// allows us to use this method on any page to interact with any element, by
// providing the specific locator in each page class (like LoginPage, HomePage, etc.).