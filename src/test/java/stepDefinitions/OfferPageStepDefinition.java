package stepDefinitions;

import io.cucumber.java.en.Then;
import org.junit.Assert;
import pageObjects.LandingPage;
import pageObjects.OffersPage;
import pageObjects.PageObjectManager;
import utils.TestContextSetup;
import java.io.IOException;

public class OfferPageStepDefinition {
    TestContextSetup textContextSetup;
    PageObjectManager pageObjectManager;
    OffersPage offersPage;

    public OfferPageStepDefinition(TestContextSetup textContextSetup) {
        this.textContextSetup = textContextSetup;
        offersPage = textContextSetup.pageObjectManager.getOffersPage();
    }

    @Then("^User searched for (.+) shortname in offers page to check if the product exist with same name$")
    public void userSearchedForSameShortnameInOffersPage(String shortName) throws IOException {
        switchToOfferPage();

        offersPage.searchItem(shortName);
        textContextSetup.offersPageProductName = offersPage.getProductName();
    }

    @Then("Validate product name in Offers Page matches with Landing Page")
    public void validateProductNameInOffersPageMatchesWithLandingPage() {
        Assert.assertEquals(textContextSetup.landingPageProductName, textContextSetup.offersPageProductName);
    }

    private void switchToOfferPage() {
        LandingPage landingPage = textContextSetup.pageObjectManager.getLandingPage();
        landingPage.selectTopDealsLink();
        textContextSetup.genericUtils.SwitchWindowsToChild();
    }
}
