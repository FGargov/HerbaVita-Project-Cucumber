package stepDefinitions;

import io.cucumber.java.en.Then;
import pageObjects.ContactInfoPage;
import utils.TestContextSetup;
import utils.TestData;

public class ContactInfoStepDefinitions {

    private TestContextSetup testContextSetup;
    private ContactInfoPage contactInfoPage;

    public ContactInfoStepDefinitions(TestContextSetup testContextSetup) {
        this.testContextSetup = testContextSetup;
        this.contactInfoPage = testContextSetup.getPageObjectManager().getContactInfoPage();
    }

    @Then("I should see the address displayed")
    public void shouldSeeTheAddressAs() {
        String expectedAddress = TestData.getContactInfo("address");
        contactInfoPage.verifyAddress(expectedAddress);
    }

    @Then("I should see the phone number displayed")
    public void shouldSeeThePhoneNumberAs() {
        String expectedPhone = TestData.getContactInfo("phone");
        contactInfoPage.verifyPhoneNumber(expectedPhone);
    }

    @Then("I should see the email displayed")
    public void shouldSeeTheEmailAs() {
        String expectedEmail = TestData.getContactInfo("email");
        contactInfoPage.verifyEmail(expectedEmail);
    }
}
