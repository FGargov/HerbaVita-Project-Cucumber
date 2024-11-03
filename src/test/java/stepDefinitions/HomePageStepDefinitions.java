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

public class HomePageStepDefinitions {
    private TestContextSetup testContextSetup;
    private HomePage homePage;
    private OfferPage offerPage;

    public HomePageStepDefinitions(TestContextSetup testContextSetup) {
        this.testContextSetup = testContextSetup;
        this.homePage = testContextSetup.getPageObjectManager().getHomePage();
        this.offerPage = testContextSetup.getPageObjectManager().getOfferPage();
    }

    @Given("I am on the home page")
    public void userIsOnHomePage() {
        homePage.verifyUserIsOnHomePage();
    }

    @Then("I should be on the correct home page URL")
    public void shouldBeOnCorrectHomePageURL() {
        homePage.verifyHomePageURLIsCorrect();
    }


    @Then("I should see the site logo displayed correctly")
    public void shouldSeeSideLogoIsDisplayed() {
        homePage.verifyHomepageLogoIsDisplayed();
    }

    @When("I click on first menu item and I should be directed to the correct page")
    public void clickOnFirstNavigationMenuItem() {
        WebElement firstMenuItem = homePage.getNavigationMenuItems().get(0);

        String expectedUrl = firstMenuItem.getAttribute("href");
        homePage.clickMenuItem(firstMenuItem);
        homePage.waitActions.waitForUrlToBe(expectedUrl);

        String currentUrl = homePage.getCurrentUrl();
        homePage.verifyNavigationToExpectedPage(expectedUrl, currentUrl);
    }

    @When("I click on second menu item and I should be directed to the correct page")
    public void clickOnSecondNavigationMenuItem() {
        homePage.getHomePageLink().click();
        WebElement secondMenuItem = homePage.getNavigationMenuItems().get(1);

        String expectedUrl = secondMenuItem.getAttribute("href");
        homePage.clickMenuItem(secondMenuItem);
        homePage.waitActions.waitForUrlToBe(expectedUrl);

        String currentUrl = homePage.getCurrentUrl();
        homePage.verifyNavigationToExpectedPage(expectedUrl, currentUrl);
    }

    @When("I should see promotional banners displayed on the home page")
    public void shouldSeePromotionalBanners() {
        homePage.verifyOfferFrameIsDisplayed();
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

        homePage.verifyOfferPageURLIsCorrect(expectedUrl, currentUrl);
    }

    private void verifyProductTitle() {
        String productTitle = offerPage.getProductTitle();
        homePage.verifyProductTitleIsCorrect(productTitle);
    }
}
