package stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import pageObjects.ProductSearchPage;
import utils.TestContextSetup;

import java.util.List;

public class ProductSearchStepDefinitions {
    private TestContextSetup testContextSetup;
    private ProductSearchPage productSearchPage;

    public ProductSearchStepDefinitions(TestContextSetup testContextSetup) {
        this.testContextSetup = testContextSetup;
        productSearchPage = testContextSetup.getPageObjectManager().getProductSearchPage();
    }


    @When("^I search for (.*) in the search field$")
    public void searchProductInSearchField(String productName) {
        productSearchPage.searchForProduct(productName);
    }

    @Then("^I should see suggestions related to (.*)$")
    public void shouldSeeProductInSearchResultsInStrongTag(String productName) {
        Assert.assertTrue("Product " + productName + " is not found in the search results",
                productSearchPage.isProductInSearchSuggestions(productName));
    }


    @And("^The number of search suggestions should be (.*)$")
    public void numberOfSearchResultsShouldBe(int numberOfSuggestions) {
        int actualNumberOfSuggestions = productSearchPage.getProductsAutocompleteSuggestions().size();
        productSearchPage.verifyNumberOfSearchedProducts(numberOfSuggestions, actualNumberOfSuggestions);
    }

    @Then("I should see a message saying {string}")
    public void shouldSeeErrorMessage(String expectedErrorMessage) {
        String actualErrorMessage = productSearchPage.getErrorMessage().getText();
        productSearchPage.verifyErrorMessage(actualErrorMessage, expectedErrorMessage);
    }


    @Then("^The suggestions should contain at least (.*) items$")
    public void numberOfSuggestionsShouldBe(int numberOfSuggestions) {
        int actualNumberOfSuggestions = productSearchPage.getProductsAutocompleteSuggestions().size();
        productSearchPage.verifyNumberOfSearchedProducts(numberOfSuggestions, actualNumberOfSuggestions);
    }

    @And("I should see the {string} button")
    public void shouldSeeViewAllResultsButton(String expectedButtonName) {
        WebElement actualButtonName = productSearchPage.getAllResultsButton();
        productSearchPage.verifyViewAllResultsButton(expectedButtonName.toLowerCase(), actualButtonName.getText().toLowerCase());
    }

    @When("I click on the \"View all results\" button")
    public void clickOnViewAllResultsButton() {
        productSearchPage.clickOnAllResultsButton(productSearchPage.getAllResultsButton());
    }

    @Then("^I should be directed to the result page for (.*) product$")
    public void shouldBeDirectedToSearchResultsPage(String productName) {
       String resultsPageTitle = productSearchPage.getResultsPageTitle().getText();
       String expectedResultsPageTitle = "Search Results for: " + productName;

       productSearchPage.verifyPageTitle(expectedResultsPageTitle, resultsPageTitle);
    }
}
