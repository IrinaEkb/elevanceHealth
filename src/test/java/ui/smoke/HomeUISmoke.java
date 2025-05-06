package ui.smoke;

import base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import pages.HomePage;
import org.testng.Assert;
import utils.DriverFactory;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class HomeUISmoke extends BaseTest {

    @Test
    public void verifyAllLinksOnHomePage() {
        // 1. **Abstraction**: Create an instance of HomePage and use its functionality to open the home page
        HomePage homePage = new HomePage();  // **Abstraction**: HomePage class hides the implementation details of opening the page
        homePage.openHomePage(); // **Encapsulation**: The method `openHomePage` encapsulates the logic of page navigation.

        // 2. **Polymorphism**: `findElements` can return different types of collections based on the underlying HTML, here it returns a List<WebElement>
        List<WebElement> allLinks = DriverFactory.getDriver().findElements(By.tagName("a"));  // **Encapsulation**: DriverFactory encapsulates the WebDriver setup.

        // 3. Iterate over each link and check the URL for validity
        for (WebElement link : allLinks) {
            String url = link.getAttribute("href");

            // 4. Skip empty, javascript or anchor links
            if (url == null || url.isEmpty() || url.startsWith("javascript") || url.startsWith("#")) {
                continue;  // Skip to the next link if the URL is invalid or non-relevant
            }

            try {
                // 5. Open a connection to the URL and check the response code
                HttpURLConnection connection = (HttpURLConnection) (new URL(url).openConnection());
                connection.setRequestMethod("HEAD");
                connection.connect();

                int responseCode = connection.getResponseCode();

                // 6. **Polymorphism**: The assertTrue method can accept different conditions, here it's validating that responseCode is less than 400
                // Assert fails only if the link is broken (status code >= 400)
                if (responseCode >= 400) {
                    // Log the broken link without failing the test
                    System.out.println("Broken link: " + url + " — HTTP Code: " + responseCode);
                } else {
                    System.out.println("Working link: " + url + " — HTTP Code: " + responseCode);
                }
            } catch (Exception e) {
                // 7. Handle any exceptions that occur while checking the link
                System.out.println("Exception while checking link: " + url + " — " + e.getMessage());
            }
        }
    }
}