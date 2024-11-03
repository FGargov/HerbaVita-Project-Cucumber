package stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pageObjects.ProductDetailsPage;
import utils.TestContextSetup;

import java.util.List;

public class ProductDetailsStepDefinitions {
    private TestContextSetup testContextSetup;
    private ProductDetailsPage productDetailsPage;

    public ProductDetailsStepDefinitions(TestContextSetup testContextSetup) {
        this.testContextSetup = testContextSetup;
        productDetailsPage = testContextSetup.getPageObjectManager().getProductDetailsPage();
    }

    @When("I click on \"Шейкове\" navigation menu item")
    public void clickOnFirstNavigationMenuItem() {
        WebElement firstMenuItem = productDetailsPage.getNavigationMenuItems().get(0);

        String expectedUrl = firstMenuItem.getAttribute("href");
        productDetailsPage.clickMenuItem(firstMenuItem);
        productDetailsPage.waitActions.waitForUrlToBe((expectedUrl));

        String currentUrl = productDetailsPage.getCurrentUrl();
        productDetailsPage.verifyNavigationToExpectedPage(expectedUrl, currentUrl);
    }

    @Then("Then I should see products listed under the \"Шейкове\" category")
    public void shouldSeeProductsIsListed() {
        productDetailsPage.verifyProductsAreVisible();
    }

    @And("I click on the first product in the list")
    public void clickOnFirstProduct() {
        productDetailsPage.clickOnFirstListedProduct();
    }

    @Then("I should see the product details page")
    public void shouldSeeProductDetailsPage() {
        productDetailsPage.verifyProductDetailsPageUrl();
    }

    @And("The product title should be displayed correctly")
    public void shouldSeeProductTitle() {
        productDetailsPage.verifyProductTitle();
    }
}
