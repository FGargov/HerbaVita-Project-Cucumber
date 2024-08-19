package stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
//import org.testng.Assert;

import org.junit.Assert;
import pageObjects.CheckoutPage;
import utils.TestContextSetup;


public class CheckoutPageStepDefinition {
    TestContextSetup testContextSetup;
    public CheckoutPage checkoutPage;

    public CheckoutPageStepDefinition(TestContextSetup textContextSetup) {
        this.testContextSetup = textContextSetup;
        checkoutPage = testContextSetup.pageObjectManager.getCheckoutPage();
    }

    @Then("^User proceeds to Checkout and validate the (.+) items in checkout page$")
    public void userProceedsToCheckout(String name)  throws InterruptedException {
        checkoutPage.CheckoutItems();
    }

    @Then("Verify user has ability to enter promo code and place order")
    public void verifyUserHasAbilityToEnterPromoCodeAndPlaceOrder() {
        Assert.assertTrue(checkoutPage.VerifyPromoBtn());
        Assert.assertTrue(checkoutPage.VerifyPlanOrder());
    }
}
