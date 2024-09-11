package stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.junit.Assert;
import pageObjects.HomePage;
import utils.TestContextSetup;

public class HomePageStepDefinition {
    private TestContextSetup testContextSetup;
    private HomePage homePage;

    public HomePageStepDefinition(TestContextSetup testContextSetup) {
        this.testContextSetup = testContextSetup;
        homePage = testContextSetup.getPageObjectManager().getHomePage();
    }

    @Given("I am on the home page")
    public void userIsOnHomePage() {
        Assert.assertTrue(homePage.getTitleHomePage().contains("Herba-Vita - Независим член на Хербалайф"));
    }

    @Then("I should be on the correct home page URL")
    public void iShouldBeOnCorrectHomePageURL() {
        Assert.assertTrue("The home page URL is incorrect", homePage.isHomePageURLCorrect());
    }


    @Then("I should see the site logo displayed correctly")
    public void iShouldSeeSideLogoIsDisplayed() {
        Assert.assertTrue("The site logo is not displayed correctly", homePage.isLogoDisplayed());
    }
}
