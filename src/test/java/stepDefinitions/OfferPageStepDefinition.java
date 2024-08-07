package stepDefinitions;

import io.cucumber.java.en.Then;
import org.junit.Assert;
import org.openqa.selenium.By;
import pageObjects.LandingPage;
import pageObjects.OffersPage;
import pageObjects.PageObjectManager;
import utils.TestContextSetup;

import java.time.Duration;
import java.util.Iterator;
import java.util.Set;

public class OfferPageStepDefinition {
    TestContextSetup textContextSetup;
    PageObjectManager pageObjectManager;

    public OfferPageStepDefinition(TestContextSetup textContextSetup) {
        this.textContextSetup = textContextSetup;
    }

    @Then("User searched for {string} shortname in offers page to check if the product exist with same name")
    public void userSearchedForSameShortnameInOffersPage(String shortName) {
        switchToOfferPage();

        OffersPage offersPage = new OffersPage(textContextSetup.driver);
        offersPage.searchItem(shortName);
        textContextSetup.driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
        textContextSetup.offersPageProductName = offersPage.getProductName();
    }

    @Then("Validate product name in Offers Page matches with Landing Page")
    public void validateProductNameInOffersPageMatchesWithLandingPage() {
        Assert.assertEquals(textContextSetup.landingPageProductName, textContextSetup.offersPageProductName);
    }

    private void switchToOfferPage() {
        //if switched to offer page -> skil below part of code
        //if (textContextSetup.driver.getCurrentUrl().equalsIgnoreCase("https://rahulshettyacademy.com/seleniumPractise/#/offers"))

        LandingPage landingPage = textContextSetup.pageObjectManager.getLandingPage();
        landingPage.selectTopDealsLink();
        Set<String> s1 = textContextSetup.driver.getWindowHandles();
        Iterator<String> i1 = s1.iterator();
        String parentWindow = i1.next();
        String childWindow = i1.next();
        textContextSetup.driver.switchTo().window(childWindow);
    }
}
