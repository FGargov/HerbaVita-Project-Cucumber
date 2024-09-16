package stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import org.junit.Assert;
import pageObjects.ShoppingCartPage;
import utils.TestContextSetup;

public class ShoppingCartStepDefinitions {
    private TestContextSetup testContextSetup;
    private ShoppingCartPage shoppingCartPage;

    public ShoppingCartStepDefinitions(TestContextSetup testContextSetup) {
        this.testContextSetup = testContextSetup;
        this.shoppingCartPage = testContextSetup.getPageObjectManager().getShoppingCartPage();
    }

    @When("^I search for (.*) in the search field and click on the search button$")
    public void searchForProductAndClickOnSearchButton(String productName) {
        shoppingCartPage.searchForProduct(productName);
        shoppingCartPage.clickOnSearchButton(shoppingCartPage.getSearchButton());

        shoppingCartPage.verifySearchResultAreDisplayed();
    }

    @And("^I have added (.*) to my shopping cart$")
    public void addAllProductsToShoppingCart(String productName) {
        shoppingCartPage.addProductToCart(productName);
        shoppingCartPage.verifyShoppingCartIsNotEmpty();
    }

    @And("^I change the quantity of (.*) to (.*)$")
    public void changeQuantityOfProduct(String productName, String quantity) {
        shoppingCartPage.changeProductQuantity(productName, quantity);
    }

    @And("^I should see the updated quantity of (.*) in my shopping cart and it should be (.*)$")
    public void shouldSeeTheUpdatedQuantity(String productName, int expectedQuantity) {
        int actualQuantity = shoppingCartPage.getProductQuantity(productName);
        Assert.assertEquals("Quantity does not match!", expectedQuantity, actualQuantity);
    }

    @And("^I remove (.*) from my shopping cart$")
    public void removeProductFromCart(String productName) {
        shoppingCartPage.removeProductFromCart(productName);
        shoppingCartPage.verifyListOfProductsIsEmpty();
    }

    @And("My shopping cart is empty")
    public void verifyListOfProductsIsEmpty() {
        shoppingCartPage.verifyShoppingCartIsEmpty();
    }

//    private void verifyShoppingCartIsEmpty() {
//        boolean isCartEmpty = shoppingCartPage.isCartEmpty();
//        Assert.assertFalse("The shopping cart is not empty!", isCartEmpty);
//    }
}
