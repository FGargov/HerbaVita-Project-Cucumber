package stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import utils.TextContextSetup;

import java.time.Duration;
import java.util.Iterator;
import java.util.Set;

public class OfferPageStepDefinition {
    TextContextSetup textContextSetup;

    public OfferPageStepDefinition(TextContextSetup textContextSetup) {
        this.textContextSetup = textContextSetup;
    }

    @Then("User searched for {string} shortname in offers page to check if the product exist with same name")
    public void userSearchedForSameShortnameInOffersPage(String shortName) {
        switchToOfferPage();
        textContextSetup.driver.findElement(By.xpath("//input[@type='search']")).sendKeys(shortName);
        textContextSetup.driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
        textContextSetup.offersPageProductName = textContextSetup.driver.findElement(By.cssSelector("tr td:nth-child(1)")).getText();
    }

    private void switchToOfferPage() {
        //if switched to offer page -> skil below part of code
        textContextSetup.driver.findElement(By.xpath("//a[@class='cart-header-navlink']")).click();
        Set<String> s1 = textContextSetup.driver.getWindowHandles();
        Iterator<String> i1 = s1.iterator();
        String parentWindow = i1.next();
        String childWindow = i1.next();
        textContextSetup.driver.switchTo().window(childWindow);
    }

    @Then("Validate product name in Offers Page matches with Landing Page")
    public void validateProductNameInOffersPageMatchesWithLandingPage() {
        Assert.assertEquals(textContextSetup.landingPageProductName, textContextSetup.offersPageProductName);
    }
}
