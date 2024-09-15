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

public class ProductSearchStepDefinition {
    private TestContextSetup testContextSetup;
    private ProductSearchPage productSearchPage;

    public ProductSearchStepDefinition(TestContextSetup testContextSetup) {
        this.testContextSetup = testContextSetup;
        productSearchPage = testContextSetup.getPageObjectManager().getProductSearchPage();
    }


    @When("^I search for (.*) in the search field$")
    public void searchProductInSearchField(String productName) {
        productSearchPage.typeProductName(productSearchPage.getProductSearchField(), productName);
    }

    @Then("^I should see suggestions related to (.*)$")
    public void shouldSeeProductInSearchResultsInStrongTag(String productName) {
        List<WebElement> suggestions = productSearchPage.getProductsAutocompleteSuggestions();
        boolean productFound = suggestions.stream()
                .anyMatch(suggestion -> {
                    WebElement strongElement = suggestion.findElement(By.tagName("strong"));
                    String productText = strongElement.getText();
                    return productText.toLowerCase().contains(productName.toLowerCase());
                });

        Assert.assertTrue("Product " + productName + " is not found in the search results", productFound);
    }

    @And("^The number of search suggestions should be (.*)$")
    public void numberOfSearchResultsShouldBe(int numberOfSuggestions) {
        int actualNumberOfSuggestions = productSearchPage.getProductsAutocompleteSuggestions().size();
        verifyNumberOfSearchedProducts(numberOfSuggestions, actualNumberOfSuggestions);
    }

    @Then("I should see a message saying {string}")
    public void shouldSeeErrorMessage(String expectedErrorMessage) {
        String actualErrorMessage = productSearchPage.getErrorMessage().getText();
        Assert.assertEquals("Error message is incorrect", expectedErrorMessage, actualErrorMessage);
    }


    @Then("^The suggestions should contain at least (.*) items$")
    public void numberOfSuggestionsShouldBe(int numberOfSuggestions) {
        int actualNumberOfSuggestions = productSearchPage.getProductsAutocompleteSuggestions().size();
        verifyNumberOfSearchedProducts(numberOfSuggestions, actualNumberOfSuggestions);
    }

    @And("I should see the {string} button")
    public void shouldSeeViewAllResultsButton(String expectedButtonName) {
        WebElement actualButtonName = productSearchPage.getAllResultsButton();
        Assert.assertEquals("The button name is incorrect", expectedButtonName.toLowerCase(), actualButtonName.getText().toLowerCase());
    }

    @When("I click on the \"View all results\" button")
    public void clickOnViewAllResultsButton() {
        productSearchPage.clickOnAllResultsButton(productSearchPage.getAllResultsButton());
    }

    @Then("^I should be directed to the result page for (.*) product$")
    public void shouldBeDirectedToSearchResultsPage(String productName) {
       String resultsPageTitle = productSearchPage.getResultsPageTitle().getText();
       String expectedResultsPageTitle = "Search Results for: " + productName;

       Assert.assertEquals("The results page title is not as expected!", expectedResultsPageTitle, resultsPageTitle);
    }
    private void verifyNumberOfSearchedProducts(int numberOfResults, int actualNumberOfResults) {
        Assert.assertEquals("Expected number of results did not match", numberOfResults, actualNumberOfResults);
    }
}
