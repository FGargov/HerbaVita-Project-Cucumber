package stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import pageObjects.ProductSearchPage;
import utils.TestContextSetup;
import utils.TestData;

import java.util.List;

public class ProductSearchStepDefinitions {
    private TestContextSetup testContextSetup;
    private ProductSearchPage productSearchPage;

    public ProductSearchStepDefinitions(TestContextSetup testContextSetup) {
        this.testContextSetup = testContextSetup;
        productSearchPage = testContextSetup.getPageObjectManager().getProductSearchPage();
    }

    @When("^I search for (.*) in the search field$")
    public void searchProductInSearchField(String productKey) {
        String productName = TestData.getProductName(productKey);
        productSearchPage.searchForProduct(productName);
    }

    @Then("^I should see suggestions related to (.*)$")
    public void shouldSeeProductInSearchResultsInStrongTag(String productKey) {
        String productName = TestData.getProductName(productKey);
        Assert.assertTrue("Product " + productName + " is not found in the search results",
                productSearchPage.isProductInSearchSuggestions(productName));
    }

    @And("^The number of search suggestions should be correct for (.*)$")
    public void numberOfSearchResultsShouldBe(String productName) {
        int expectedNumberOfSuggestions = TestData.getExpectedSuggestionCount(productName);
        int actualNumberOfSuggestions = productSearchPage.getProductsAutocompleteSuggestions().size();
        productSearchPage.verifyNumberOfSearchedProducts(expectedNumberOfSuggestions, actualNumberOfSuggestions);
    }

    @Then("I should see a message saying {string}")
    public void shouldSeeErrorMessage(String messageKey) {
        String expectedErrorMessage = TestData.getNoProductsFoundMessage(messageKey);
        String actualErrorMessage = productSearchPage.getErrorMessage().getText();
        productSearchPage.verifyErrorMessage(actualErrorMessage, expectedErrorMessage);
    }


    @Then("^The suggestions should contain at least (.*) items$")
    public void numberOfSuggestionsShouldBe(String productKey) {
        int expectedCount = TestData.getExpectedSuggestionCount(productKey);
        int actualNumberOfSuggestions = productSearchPage.getProductsAutocompleteSuggestions().size();
        productSearchPage.verifyNumberOfSearchedProducts(expectedCount, actualNumberOfSuggestions);
    }

    @And("I should see the {string} button")
    public void shouldSeeViewAllResultsButton(String buttonKey) {
        String expectedButtonName = TestData.getViewAllResultsButtonText();
        WebElement actualButtonName = productSearchPage.getAllResultsButton();
        productSearchPage.verifyViewAllResultsButton(expectedButtonName.toLowerCase(), actualButtonName.getText().toLowerCase());
    }

    @When("I click on the \"View all results\" button")
    public void clickOnViewAllResultsButton() {
        productSearchPage.clickOnAllResultsButton(productSearchPage.getAllResultsButton());
    }

    @Then("^I should be directed to the result page for (.*) product$")
    public void shouldBeDirectedToSearchResultsPage(String productKey) {
       String productName = TestData.getProductName(productKey);
       String resultsPageTitle = productSearchPage.getResultsPageTitle().getText();
       String expectedResultsPageTitle = "Search Results for: " + productName;

       productSearchPage.verifyPageTitle(expectedResultsPageTitle, resultsPageTitle);
    }
}
