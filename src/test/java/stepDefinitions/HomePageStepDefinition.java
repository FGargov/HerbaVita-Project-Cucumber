package stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pageObjects.HomePage;
import pageObjects.OfferPage;
import utils.TestContextSetup;

public class HomePageStepDefinition {
    private TestContextSetup testContextSetup;
    private HomePage homePage;
    private OfferPage offerPage;

    public HomePageStepDefinition(TestContextSetup testContextSetup) {
        this.testContextSetup = testContextSetup;
        homePage = testContextSetup.getPageObjectManager().getHomePage();
        offerPage = testContextSetup.getPageObjectManager().getOfferPage();
    }

    @Given("I am on the home page")
    public void userIsOnHomePage() {
        Assert.assertTrue(homePage.getTitleHomePage().contains("Herba-Vita - Независим член на Хербалайф"));
    }

    @Then("I should be on the correct home page URL")
    public void shouldBeOnCorrectHomePageURL() {
        Assert.assertTrue("The home page URL is incorrect", homePage.isHomePageURLCorrect());
    }


    @Then("I should see the site logo displayed correctly")
    public void shouldSeeSideLogoIsDisplayed() {
        Assert.assertTrue("The site logo is not displayed correctly", homePage.isLogoDisplayed());
    }

    @When("I click on first menu item and I should be directed to the correct page")
    public void clickOnFirstNavigationMenuItem() {
            WebElement firstMenuItem = homePage.getNavigationMenuItems().get(0);

            String expectedUrl = firstMenuItem.getAttribute("href");
            homePage.clickMenuItem(firstMenuItem);
            homePage.getWait().until(ExpectedConditions.urlToBe(expectedUrl));

            String currentUrl = homePage.getCurrentUrl();
            verifyRedirectionToCorrectPage(expectedUrl, currentUrl);
    }

    @When("I click on second menu item and I should be directed to the correct page")
    public void clickOnSecondNavigationMenuItem() {
        WebElement firstMenuItem = homePage.getNavigationMenuItems().get(1);

        String expectedUrl = firstMenuItem.getAttribute("href");
        homePage.clickMenuItem(firstMenuItem);
        homePage.getWait().until(ExpectedConditions.urlToBe(expectedUrl));

        String currentUrl = homePage.getCurrentUrl();
        verifyRedirectionToCorrectPage(expectedUrl, currentUrl);
    }

    @When("I should see promotional banners displayed on the home page")
    public void shouldSeePromotionalBanners() {
        Assert.assertTrue("The promotional banner is not displayed correctly", homePage.getOfferFrame());
    }

    @Then("I click on the promotional banner and I should be directed to the promotional page")
    public void clickOnOfferBannerAndDirectedToPromoPage() {

        homePage.clickOnPromoButton(homePage.getBuyNowButton());

        verifyOfferPageUrl();
        verifyProductTitle();
    }

    private void verifyOfferPageUrl() {
        String currentUrl = homePage.getCurrentUrl();
        String expectedUrl = offerPage.getCurrentUrl();

        Assert.assertEquals("The redirection is not to the correct page!", expectedUrl, currentUrl);
    }

    private void verifyProductTitle() {
        String productTitle = offerPage.getProductTitle();
        Assert.assertEquals("The product title is not displayed correctly", "CR7 DRIVE С ВКУС НА АКАЙ БЕРИ", productTitle);
    }

    private void verifyRedirectionToCorrectPage(String currentUrl, String expectedUrl) {
        Assert.assertEquals("The redirection is not to the correct page!", expectedUrl, currentUrl);
    }
}
