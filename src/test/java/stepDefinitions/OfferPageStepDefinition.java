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

public class OfferPageStepDefinition {
    public WebDriver driver;
    String landingPageProductName;
    String offersPageProductName;


    @Then("User searched for {string} shortname in offers page to check if the product exist with same name")
    public void userSearchedForSameShortnameInOffersPage(String shortName) {
        driver.findElement(By.xpath("//a[@class='cart-header-navlink']")).click();
        Set<String> s1 = driver.getWindowHandles();
        Iterator<String> i1 = s1.iterator();
        String parentWindow = i1.next();
        String childWindow = i1.next();
        driver.switchTo().window(childWindow);
        driver.findElement(By.xpath("//input[@type='search']")).sendKeys(shortName);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
        offersPageProductName = driver.findElement(By.cssSelector("tr td:nth-child(1)")).getText();
        driver.quit();
    }

    @Then("Validate product name in Offers Page matches with Landing Page")
    public void validateProductNameInOffersPageMatchesWithLandingPage() {
        Assert.assertEquals(landingPageProductName, offersPageProductName);
    }
}
