package steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.datatable.DataTable;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import pages.HomePage;

import java.util.List;
import java.util.Map;

public class HomeSteps {

    HomePage homePage = new HomePage(); // Composition: using HomePage inside step class

    @Given("I am on the ElevanceHealth homepage")
    public void i_am_on_homepage() {
        homePage.openHomePage(); // Abstraction: hides how the page is opened
    }

    @Then("the following links should be present and clickable:")
    public void verify_links_clickable(DataTable dataTable) {
        List<Map<String, String>> linkData = dataTable.asMaps();

        for (Map<String, String> row : linkData) {
            String linkText = row.get("Link Text");

            WebElement link = homePage.getLinkByText(linkText);
            Assert.assertTrue(link.isDisplayed(), "Link not visible: " + linkText);
            Assert.assertTrue(link.isEnabled(), "Link not clickable: " + linkText);
        }
    }
}