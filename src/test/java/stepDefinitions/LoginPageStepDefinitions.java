package stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import lombok.extern.log4j.Log4j2;
import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pageObjects.DashboardPage;
import pageObjects.LoginPage;
import utils.TestContextSetup;

@Log4j2
public class LoginPageStepDefinitions {
    private TestContextSetup testContextSetup;
    private LoginPage loginPage;
    private DashboardPage dashboardPage;
    private final String ORDERS_LINK = "https://herba-vita.eu/stage/moyat-profil/orders/";
    private final String DOWNLOADS_LINK = "https://herba-vita.eu/stage/moyat-profil/downloads/";
    private final String  LOGOUT_LINK = "https://herba-vita.eu/stage/moyat-profil/customer-logout/?_wpnonce=31143fab33";


    public LoginPageStepDefinitions(TestContextSetup testContextSetup) {
        this.testContextSetup = testContextSetup;
        loginPage = testContextSetup.getPageObjectManager().getLoginPage();
        dashboardPage = testContextSetup.getPageObjectManager().getDashboardPage();
    }

    @Given("I am on the login page")
    public void userIsOnLoginPage() {
        loginPage.getWait().until(ExpectedConditions.visibilityOf(loginPage.getLoginPageIcon()));
        loginPage.clickOnLoginPageIcon(loginPage.getLoginPageIcon());

        String currentUrl = loginPage.getCurrentUrl();
        String expectedUrl = "https://herba-vita.eu/stage/moyat-profil/";
        verifyOfferPageUrl(currentUrl, expectedUrl);
    }

    @When("Enter the username {string}")
    public void enterUsername(String username) {
        loginPage.typeUsername(loginPage.getUsernameLocator(), username);
        String typedUsername = loginPage.getTypedUsername();

        verifyUsernames(username, typedUsername);
    }

    @And("Enter the password {string}")
    public void enterPassword(String password) {
        loginPage.typePassword(loginPage.getPasswordLocator(), password);
        String typedPassword = loginPage.getTypedPassword();

        verifyPasswords(password, typedPassword);
    }

    @And("Click on the login button")
    public void clickOnLoginButton() {
        loginPage.clickOnLoginButton(loginPage.getLoginButton());

        String currentUrl = loginPage.getCurrentUrl();
        String expectedUrl = "https://herba-vita.eu/stage/moyat-profil/";

        verifyIsLoginSuccessful(currentUrl, expectedUrl);
    }

    @Then("I should be logged in successfully")
    public void shouldBeLoggedInSuccessfully() {
        WebElement profileTitle = testContextSetup.getTestBase().driver.findElement(dashboardPage.getProfileTitle());
        verifyProfileTitle(profileTitle);
    }

    @And("I should see the user dashboard")
    public void userShouldSeeDashboard() {
        Assert.assertTrue("Profile title is not displayed!", dashboardPage.isProfileTitleDisplayed());
        verifyAllLinks();
    }

    @Then("Should see an error message saying {string}")
    public void shouldSeeErrorMessage(String expectedErrorMessage) {
        String actualErrorMessage = loginPage.getErrorMessage();
        verifyErrorMessage(actualErrorMessage, expectedErrorMessage);
    }

    private void verifyOfferPageUrl(String currentUrl, String expectedUrl) {
        Assert.assertTrue("The login page URL is incorrect", currentUrl.contains(expectedUrl));
    }

    private void verifyUsernames(String username, String typedUsername) {
        Assert.assertEquals("The typed username is not correct!", username, typedUsername);
    }

    private void verifyPasswords(String password, String typedPassword) {
        Assert.assertEquals("The typed password is not correct!", password, typedPassword);
    }

    private void verifyIsLoginSuccessful(String currentUrl, String expectedUrl) {
        Assert.assertTrue("User is not logged in", currentUrl.contains(expectedUrl));
    }

    private void verifyProfileTitle(WebElement profileTitle) {
        Assert.assertTrue("User is not logged in", profileTitle.isDisplayed());
    }

    private void verifyErrorMessage(String actualErrorMessage, String expectedErrorMessage) {
        String cleanedActualError = actualErrorMessage.replaceAll("\\s+", " ");
        String cleanedExpectedError = expectedErrorMessage.replaceAll("\\s+", " ");
        Assert.assertEquals("Error message is incorrect", cleanedExpectedError, cleanedActualError);
    }

    public void verifyAllLinks() {
        dashboardPage.verifyLinkUrl(dashboardPage.getOrdersLink(), ORDERS_LINK);
        dashboardPage.verifyLinkUrl(dashboardPage.getDownloadsLink(), DOWNLOADS_LINK);
        dashboardPage.verifyLogoutLinkUrl(dashboardPage.getLogoutLink(), LOGOUT_LINK);
    }
}
