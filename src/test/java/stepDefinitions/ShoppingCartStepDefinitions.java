package stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageObjects.ShoppingCartPage;
import utils.TestContextSetup;
import utils.TestData;

public class ShoppingCartStepDefinitions {
    private TestContextSetup testContextSetup;
    private ShoppingCartPage shoppingCartPage;

    public ShoppingCartStepDefinitions(TestContextSetup testContextSetup) {
        this.testContextSetup = testContextSetup;
        this.shoppingCartPage = testContextSetup.getPageObjectManager().getShoppingCartPage();
    }

    @When("^I search for (.*) in the search field and click on the search button$")
    public void searchForProductAndClickOnSearchButton(String productKey) {
        String productName = TestData.getProductNameFromShoppingCart(productKey);
        shoppingCartPage.searchForProduct(productName);
        shoppingCartPage.clickOnSearchButton(shoppingCartPage.getSearchButton());

        shoppingCartPage.verifySearchResultAreDisplayed();
    }

    @Then("^I have added (.*) to my shopping cart$")
    public void addAllProductsToShoppingCart(String productKey) {
        String productName = TestData.getProductNameFromShoppingCart(productKey);
        shoppingCartPage.addProductToCart(productName);
        shoppingCartPage.verifyShoppingCartIsNotEmpty();
    }

    @And("^I change the quantity of (.*) to (\\d+)$")
    public void changeQuantityOfProduct(String productKey) {
        String productName = TestData.getProductNameFromShoppingCart(productKey);
        int quantityFromJson = TestData.getProductQuantityFromShoppingCart(productKey);
        shoppingCartPage.changeProductQuantity(productName, String.valueOf(quantityFromJson));
    }

    @And("^I should see the updated quantity of (.*) in my shopping cart and it should be (.*)$")
    public void shouldSeeTheUpdatedQuantity(String productKey) {
        String productName = TestData.getProductNameFromShoppingCart(productKey);
        int quantityFromJson = TestData.getProductQuantityFromShoppingCart(productKey);
        int actualQuantity = shoppingCartPage.getProductQuantity(productName);
        shoppingCartPage.verifyUpdatedQuantity(quantityFromJson, actualQuantity);
    }

    @And("^I remove (.*) from my shopping cart$")
    public void removeProductFromCart(String productKey) {
        String productName = TestData.getProductNameFromShoppingCart(productKey);
        shoppingCartPage.removeProductFromCart(productName);
        shoppingCartPage.verifyListOfProductsIsEmpty();
    }

    @And("My shopping cart is empty")
    public void verifyListOfProductsIsEmpty() {
        shoppingCartPage.verifyShoppingCartIsEmpty();
    }
}
