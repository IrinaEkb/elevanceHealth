package base;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import utils.DriverFactory;

public class BasePage {

    // OOP: Abstraction — provides a simple reusable interface for actions
    // OOP: Encapsulation — hides the WebDriver logic from subclasses

    protected void click(By locator) {
        DriverFactory.getDriver().findElement(locator).click();
    }

    protected void type(By locator, String text) {
        DriverFactory.getDriver().findElement(locator).sendKeys(text);
    }

    protected String getText(By locator) {
        return DriverFactory.getDriver().findElement(locator).getText();
    }
}