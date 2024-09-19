package stepDefinitions;

import io.cucumber.java.en.Then;
import org.junit.Assert;
import pageObjects.ContactInfoPage;
import utils.TestContextSetup;

public class ContactInfoStepDefinitions {

    private TestContextSetup testContextSetup;
    private ContactInfoPage contactInfoPage;

    public ContactInfoStepDefinitions(TestContextSetup testContextSetup) {
        this.testContextSetup = testContextSetup;
        this.contactInfoPage = testContextSetup.getPageObjectManager().getContactInfoPage();
    }

    @Then("I should see the address as {string}")
    public void shouldSeeTheAddressAs(String expectedAddress) {
        contactInfoPage.verifyAddress(expectedAddress);
    }

    @Then("I should see the phone number as {string}")
    public void shouldSeeThePhoneNumberAs(String expectedPhone) {
        contactInfoPage.verifyPhoneNumber(expectedPhone);
    }

    @Then("I should see the email as {string}")
    public void shouldSeeTheEmailAs(String expectedEmail) {
        contactInfoPage.verifyEmail(expectedEmail);
    }
}
