package stepDefinitions;

import io.cucumber.java.en.*;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.WebElement;
import pageObjects.DashboardPage;
import pageObjects.LoginPage;
import utils.TestContextSetup;

import java.io.IOException;

@Log4j2
public class LoginPageStepDefinitions {
    private TestContextSetup testContextSetup;
    private LoginPage loginPage;
    private DashboardPage dashboardPage;
    private final String MY_PROFILE_URL = "https://herba-vita.eu/stage/moyat-profil/";
    private final String ORDERS_LINK_URL = "https://herba-vita.eu/stage/moyat-profil/orders/";
    private final String DOWNLOADS_LINK_URL = "https://herba-vita.eu/stage/moyat-profil/downloads/";
    private final String LOGOUT_LINK_URL = "https://herba-vita.eu/stage/moyat-profil/customer-logout/?_wpnonce=31143fab33";


    public LoginPageStepDefinitions(TestContextSetup testContextSetup) {
        this.testContextSetup = testContextSetup;
        this.loginPage = testContextSetup.getPageObjectManager().getLoginPage();
        this.dashboardPage = testContextSetup.getPageObjectManager().getDashboardPage();
    }

    @Given("I am on the login page")
    public void userIsOnLoginPage() {
        loginPage.clickOnLoginPageIcon(loginPage.getLoginPageIcon());

        String currentUrl = loginPage.getCurrentUrl();
        loginPage.verifyProfilePageUrl(currentUrl, MY_PROFILE_URL);
    }

    @When("Enter the username {string}")
    public void enterUsername(String username) {
        loginPage.typeUsername(loginPage.getUsernameLocator(), username);
        String typedUsername = loginPage.getTypedUsername();

        loginPage.verifyUsernames(username, typedUsername);
    }

    @And("Enter the password {string}")
    public void enterPassword(String password) {
        loginPage.typePassword(loginPage.getPasswordLocator(), password);
        String typedPassword = loginPage.getTypedPassword();

        loginPage.verifyPasswords(password, typedPassword);
    }

    @And("Click on the login button")
    public void clickOnLoginButton() {
        loginPage.clickOnLoginButton(loginPage.getLoginButton());

        String currentUrl = loginPage.getCurrentUrl();

        loginPage.verifyIsLoginSuccessful(currentUrl, MY_PROFILE_URL);
    }

    @Then("I should be logged in successfully")
    public void shouldBeLoggedInSuccessfully() throws IOException {
        WebElement profileTitle = testContextSetup.getTestBase().getDriver().findElement(dashboardPage.getProfileTitle());
        loginPage.verifyProfileTitle(profileTitle);
    }

    @And("I should see the user dashboard")
    public void userShouldSeeDashboard() {
        dashboardPage.verifyProfileTitle();
        verifyAllLinks();
    }

    @But("Should see an error message saying {string}")
    public void shouldSeeErrorMessage(String expectedErrorMessage) {
        String actualErrorMessage = loginPage.getErrorMessage();
        loginPage.verifyErrorMessage(actualErrorMessage, expectedErrorMessage);
    }

    private void verifyAllLinks() {
        dashboardPage.verifyLinkUrl(dashboardPage.getOrdersLink(), ORDERS_LINK_URL);
        dashboardPage.verifyLinkUrl(dashboardPage.getDownloadsLink(), DOWNLOADS_LINK_URL);
        dashboardPage.verifyLogoutLinkUrl(dashboardPage.getLogoutLink(), LOGOUT_LINK_URL);
    }
}
