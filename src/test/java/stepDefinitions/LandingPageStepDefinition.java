package stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import pageObjects.LandingPage;
import utils.TestContextSetup;


public class LandingPageStepDefinition {
    TestContextSetup testContextSetup;

    public LandingPageStepDefinition(TestContextSetup textContextSetup) {
        this.testContextSetup = textContextSetup;
    }

    @Given("User is on Green Card Landing page")
    public void userIsOnGreenCardLandingPage() {

    }

    @When("^User searched with shortname (.+) and expected actual name of product$")
    public void userSearchedWithShortnameAndExtractedActualName(String shortName) {
        LandingPage landingPage = testContextSetup.pageObjectManager.getLandingPage();
        landingPage.searchItem(shortName);
        testContextSetup.landingPageProductName = landingPage.getProductName().split("-")[0].trim();

        System.out.println(testContextSetup.landingPageProductName + " is extracted form Home Page");
    }
}
