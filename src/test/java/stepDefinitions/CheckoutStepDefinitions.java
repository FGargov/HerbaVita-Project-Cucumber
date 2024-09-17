package stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pageObjects.CheckoutPage;
import utils.TestContextSetup;

public class CheckoutStepDefinitions {
    private TestContextSetup testContextSetup;
    private CheckoutPage checkoutPage;
    private final String CHECKOUT_PAGE_URL = "https://herba-vita.eu/stage/porachka-2/";

    public CheckoutStepDefinitions(TestContextSetup testContextSetup) {
        this.testContextSetup = testContextSetup;
        checkoutPage = testContextSetup.getPageObjectManager().getCheckoutPage();
    }

    @When("^I find and select (.*) using the search$")
    public void searchForProductAndClickOnSearchButton(String productName) {
        checkoutPage.searchForProduct(productName);
        checkoutPage.clickOnSearchButton(checkoutPage.getSearchButton());

        checkoutPage.verifySearchResultAreDisplayed();
    }

    @Then("^(.*) is added to my cart$")
    public void addAllProductsToShoppingCart(String productName) {
        checkoutPage.addProductToCart(productName);
        checkoutPage.verifyShoppingCartIsNotEmpty();
    }

    @And("I click on the order button")
    public void clickOnOrderButton() {
        checkoutPage.clickOnOrderButton(checkoutPage.getOrderButton());
    }

    @Then("I should be taken to the checkout page")
    public void shouldBeTakenToTheCheckoutPage() {
        checkoutPage.getWait().until(ExpectedConditions.urlToBe(CHECKOUT_PAGE_URL));
        String currentUrl = checkoutPage.getCurrentUrl();
        verifyCheckoutPageUrl(currentUrl, CHECKOUT_PAGE_URL);
    }

    private void verifyCheckoutPageUrl(String currentUrl, String expectedUrl) {
        Assert.assertTrue("The checkout page URL is incorrect", currentUrl.contains(expectedUrl));
    }
}
