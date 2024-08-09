package stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import org.junit.Assert;
import pageObjects.LandingPage;
import utils.TestContextSetup;


public class LandingPageStepDefinition {
    TestContextSetup testContextSetup;
    public LandingPage landingPage;

    public LandingPageStepDefinition(TestContextSetup textContextSetup) {
        this.testContextSetup = textContextSetup;
        landingPage = testContextSetup.pageObjectManager.getLandingPage();
    }

    @Given("User is on Green Card Landing page")
    public void userIsOnGreenCardLandingPage() {
        Assert.assertTrue(landingPage.getTitleLandingPage().contains("GreenKart"));
    }

    @When("^User searched with shortname (.+) and expected actual name of product$")
    public void userSearchedWithShortnameAndExtractedActualName(String shortName) {
        landingPage.searchItem(shortName);
        testContextSetup.landingPageProductName = landingPage.getProductName().split("-")[0].trim();

        System.out.println(testContextSetup.landingPageProductName + " is extracted form Home Page");
    }

    @When("Added {string} items of the selected product to card")
    public void addedItemsOfTheSelectedProductCard(String quantity) {
        landingPage.incrementQuantity(Integer.parseInt(quantity));
    }
}
