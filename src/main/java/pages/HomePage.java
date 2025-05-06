package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import utils.DriverFactory;
import base.BasePage;

public class HomePage extends BasePage {

    // OOP: Inheritance — inherits utility methods from BasePage

    public void openHomePage() {
        DriverFactory.getDriver().get("https://www.elevancehealth.com/");
    }

    public WebElement getLinkByText(String linkText) {
        return DriverFactory.getDriver().findElement(By.linkText(linkText));
    }

    // OOP: Polymorphism — this class could override BasePage methods if needed
}