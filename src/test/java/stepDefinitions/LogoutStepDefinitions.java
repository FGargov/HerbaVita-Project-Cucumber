package stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import utils.TestContextSetup;
import utils.TestData;

public class LogoutStepDefinitions {
    private final String MY_PROFILE_URL = TestData.getProfileUrls().get("myProfileUrl").asText();

    private TestContextSetup testContextSetup;
    private HomePage homePage;
    private LoginPage loginPage;

    public LogoutStepDefinitions(TestContextSetup testContextSetup) {
        this.testContextSetup = testContextSetup;
        this.homePage = testContextSetup.getPageObjectManager().getHomePage();
        this.loginPage = testContextSetup.getPageObjectManager().getLoginPage();
    }

    @And("Click on login icon and redirect to login page")
    public void userIsOnLoginPage() {
        loginPage.clickOnLoginPageIcon(loginPage.getLoginPageIcon());

        String currentUrl = loginPage.getCurrentUrl();
        loginPage.verifyProfilePageUrl(currentUrl, MY_PROFILE_URL);
    }

    @When("Enter the user {string}")
    public void enterUsername(String username) {
        username = TestData.getValidUser().get("username").asText();
        loginPage.typeUsername(loginPage.getUsernameLocator(), username);
        String typedUsername = loginPage.getTypedUsername();

        loginPage.verifyUsernames(username, typedUsername);
    }

    @And("Enter the long password {string}")
    public void enterPassword(String password) {
        password = TestData.getValidUser().get("password").asText();
        loginPage.typePassword(loginPage.getPasswordLocator(), password);
        String typedPassword = loginPage.getTypedPassword();

        loginPage.verifyPasswords(password, typedPassword);
    }

    @Then("Finally click on the login button")
    public void clickOnLoginButton() {
        loginPage.clickOnLoginButton(loginPage.getLoginButton());

        String currentUrl = loginPage.getCurrentUrl();

        loginPage.verifyIsLoginSuccessful(currentUrl, MY_PROFILE_URL);
    }

    @When("I click on the logout button")
    public void clickOnLogoutButton() {
        homePage.clickLogoutButton();
    }

    @Then("I should be redirected to the home page")
    public void verifyRedirectionToHomePage() {
        homePage.verifyLoginPageURLIsCorrect();
    }

    @Then("I should no longer see the user profile button")
    public void verifyUserProfileButtonIsNotVisible() {
        loginPage.verifyLoginFormIsVisible();
    }
}
