package stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import utils.TextContextSetup;


public class LandingPageStepDefinition {
    TextContextSetup textContextSetup;

    public LandingPageStepDefinition(TextContextSetup textContextSetup) {
        this.textContextSetup = textContextSetup;
    }

    @Given("User is on Green Card Landing page")
    public void userIsOnGreenCardLandingPage() {
        textContextSetup.driver = new ChromeDriver();
        textContextSetup.driver.get("https://rahulshettyacademy.com/seleniumPractise/#/");
    }

    @When("User searched with shortname {string} and expected actual name of product")
    public void userSearchedWithShortnameAndExtractedActualName(String shortName) {
        textContextSetup.driver.findElement(By.xpath("//input[@type='search']")).sendKeys(shortName);
        textContextSetup.landingPageProductName = textContextSetup.driver.findElement(By.xpath("//h4[text()='Tomato - 1 Kg']")).getText().split("-")[0].trim();
        System.out.println(textContextSetup.landingPageProductName + " is extracted form Home Page");
    }
}
