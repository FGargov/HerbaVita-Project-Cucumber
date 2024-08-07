package stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import org.openqa.selenium.chrome.ChromeDriver;
import pageObjects.LandingPage;
import utils.TestContextSetup;


public class LandingPageStepDefinition {
    TestContextSetup testContextSetup;

    public LandingPageStepDefinition(TestContextSetup textContextSetup) {
        this.testContextSetup = textContextSetup;
    }

    @Given("User is on Green Card Landing page")
    public void userIsOnGreenCardLandingPage() {
        testContextSetup.driver = new ChromeDriver();
        testContextSetup.driver.get("https://rahulshettyacademy.com/seleniumPractise/#/");
    }

    @When("User searched with shortname {string} and expected actual name of product")
    public void userSearchedWithShortnameAndExtractedActualName(String shortName) {
        LandingPage landingPage = testContextSetup.pageObjectManager.getLandingPage();
        landingPage.searchItem(shortName);
        testContextSetup.landingPageProductName = landingPage.getProductName().split("-")[0].trim();

        System.out.println(testContextSetup.landingPageProductName + " is extracted form Home Page");
    }
}
