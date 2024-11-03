package stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pageObjects.CheckoutPage;
import utils.TestContextSetup;
import utils.TestData;

import java.util.List;

public class CheckoutStepDefinitions {
    private TestContextSetup testContextSetup;
    private CheckoutPage checkoutPage;
    private final String CHECKOUT_PAGE_URL = TestData.getCheckoutPageUrl();
    private final String ORDER_RECEIVE_PAGE_URL = TestData.getOrderReceiveUrl();

    public CheckoutStepDefinitions(TestContextSetup testContextSetup) {
        this.testContextSetup = testContextSetup;
        checkoutPage = testContextSetup.getPageObjectManager().getCheckoutPage();
    }

    @When("^I find and select (.*) using the search$")
    public void searchForProductAndClickOnSearchButton(String productName) {
        productName = TestData.getProduct("product1").get("name").asText();
        checkoutPage.searchForProduct(productName);
        checkoutPage.clickOnSearchButton(checkoutPage.getSearchButton());

        checkoutPage.verifySearchResultAreDisplayed();
    }

    @Then("^(.*) is added to my cart$")
    public void addProductToCart(String productName) {
        productName = TestData.getProduct("product1").get("name").asText();
        checkoutPage.addProductToCart(productName);
        checkoutPage.verifyShoppingCartIsNotEmpty();
    }

    @And("I click on the order button")
    public void clickTheOrderButton() {
        checkoutPage.clickOnOrderButton(checkoutPage.getCheckoutButton());
    }

    @Then("I should be taken to the checkout page")
    public void shouldBeTakenToTheCheckoutPage() {
        checkoutPage.getWait().until(ExpectedConditions.urlToBe(CHECKOUT_PAGE_URL));
        String currentUrl = checkoutPage.getCurrentUrl();
        checkoutPage.verifyPageUrl(currentUrl, CHECKOUT_PAGE_URL);
    }

    @When("^I find and select (.*) using the search field in home page$")
    public void searchForProductAndClickOnSearchButtonInHomePage(String productName) {
        productName = TestData.getProduct("product1").get("name").asText();
        checkoutPage.searchForProduct(productName);
        checkoutPage.clickOnSearchButton(checkoutPage.getSearchButton());

        checkoutPage.verifySearchResultAreDisplayed();
    }

    @Then("^(.*) is added to my cart and click on the order button$")
    public void addAllProductsToShoppingCart(String productName) {
        productName = TestData.getProduct("product1").get("name").asText();
        checkoutPage.addProductToCart(productName);
        checkoutPage.verifyShoppingCartIsNotEmpty();
        checkoutPage.clickOnOrderButton(checkoutPage.getCheckoutButton());
    }

    @And("I am on the checkout page")
    public void shouldBeOnTheCheckoutPage() {
        checkoutPage.getWait().until(ExpectedConditions.urlToBe(CHECKOUT_PAGE_URL));
        String currentUrl = checkoutPage.getCurrentUrl();
        checkoutPage.verifyPageUrl(currentUrl, CHECKOUT_PAGE_URL);
    }

    @Then("I enter the following shipping information and verify that the shipping information is correct")
    public void enterShippingInformation(List<String> data) {
        checkoutPage.enterShippingInformation(data);
        checkoutPage.verifyShippingInformation(data);
    }

    @And("I click on the \"ПОРЪЧВАНЕ\" button")
    public void clickOnOrderButton() {
        checkoutPage.clickOnOrderButton();
    }

    @And("I should see a confirmation page with the order details")
    public void verifyConfirmationPage() {
        checkoutPage.getWait().until(ExpectedConditions.urlContains(ORDER_RECEIVE_PAGE_URL));
        String currentUrl = checkoutPage.getCurrentUrl();
        checkoutPage.verifyPageUrl(currentUrl, ORDER_RECEIVE_PAGE_URL);
    }

    @And("А confirmation message saying {string} should be displayed")
    public void verifyConfirmationMessage(String expectedMessage) {
        expectedMessage = TestData.getMessage("orderConfirmation");
        checkoutPage.getWait().until(ExpectedConditions.visibilityOf(checkoutPage.getCompletedOrderMessage()));
        String actualMessage = checkoutPage.getCompletedOrderMessage().getText();
        checkoutPage.verifyOrderCompletedMessage(expectedMessage, actualMessage);
    }
}
