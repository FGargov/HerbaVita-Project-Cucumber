package stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;
import java.util.Iterator;
import java.util.Set;

public class LandingPageStepDefinition {
    public WebDriver driver;
    String landingPageProductName;
    String offersPageProductName;

    @Given("User is on Green Card Landing page")
    public void userIsOnGreenCardLandingPage() {
        driver = new ChromeDriver();
        driver.get("https://rahulshettyacademy.com/seleniumPractise/#/");
    }

    @When("User searched with shortname {string} and expected actual name of product")
    public void userSearchedWithShortnameAndExtractedActualName(String shortName) {
        driver.findElement(By.xpath("//input[@type='search']")).sendKeys(shortName);
        landingPageProductName = driver.findElement(By.xpath("//h4[text()='Tomato - 1 Kg']")).getText().split("-")[0].trim();
        System.out.println(landingPageProductName + " is extracted form Home Page");
    }
}
