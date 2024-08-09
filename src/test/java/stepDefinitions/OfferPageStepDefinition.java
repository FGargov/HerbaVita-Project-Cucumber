package stepDefinitions;

import io.cucumber.java.en.Then;
import org.junit.Assert;
import pageObjects.LandingPage;
import pageObjects.OffersPage;
import pageObjects.PageObjectManager;
import utils.TestContextSetup;

import java.io.IOException;
import java.time.Duration;

public class OfferPageStepDefinition {
    TestContextSetup textContextSetup;
    PageObjectManager pageObjectManager;

    public OfferPageStepDefinition(TestContextSetup textContextSetup) {
        this.textContextSetup = textContextSetup;
    }

    @Then("^User searched for (.+) shortname in offers page to check if the product exist with same name$")
    public void userSearchedForSameShortnameInOffersPage(String shortName) throws IOException {
        switchToOfferPage();

        OffersPage offersPage = textContextSetup.pageObjectManager.getOffersPage();
        offersPage.searchItem(shortName);
        textContextSetup.testBase.WebDriverManager().manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
        textContextSetup.offersPageProductName = offersPage.getProductName();
    }

    @Then("Validate product name in Offers Page matches with Landing Page")
    public void validateProductNameInOffersPageMatchesWithLandingPage() {
        Assert.assertEquals(textContextSetup.landingPageProductName, textContextSetup.offersPageProductName);
    }

    private void switchToOfferPage() {
        //if switched to offer page -> skil below part of code
        LandingPage landingPage = textContextSetup.pageObjectManager.getLandingPage();
        landingPage.selectTopDealsLink();
        textContextSetup.genericUtils.SwitchWindowsToChild();
    }
}
