package pageObjects;

import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage extends BasePage {
    private Actions actions;

    public LoginPage(WebDriver driver) {
        super(driver);
        this.actions = new Actions(driver);
    }

    private By loginPageIcon = By.xpath("//div[contains(@class,'wd-header-my-account wd-tools-element')]//span");
    private By usernameLocator = By.xpath("//input[@id='username']");
    private By passwordLocator = By.xpath("//input[@id='password']");
    private By loginButton = By.xpath("//button[contains(@class,'button woocommerce-button')]");
    private By errorMessageLocator = By.xpath("//ul[contains(@class, 'woocommerce-error')]//li");


    public By getUsernameLocator() {
        return this.usernameLocator;
    }

    public By getPasswordLocator() {
        return this.passwordLocator;
    }


    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    public WebElement getLoginPageIcon() {
        return driver.findElement(loginPageIcon);
    }

    public void clickOnLoginPageIcon(WebElement icon) {
        waitActions.waitForElementVisible(icon);
        icon.click();
    }

    public void typeUsername(By usernameLocator, String username) {
        waitActions.waitForElementVisibleByLocator(usernameLocator);
        WebElement usernameField = driver.findElement(usernameLocator);
        usernameField.clear();
        usernameField.sendKeys(username);
    }

    public void typePassword(By passwordLocator, String password) {
        waitActions.waitForElementVisibleByLocator(passwordLocator);
        driver.findElement(passwordLocator).clear();
        driver.findElement(passwordLocator).sendKeys(password);
    }

    public String getTypedUsername() {
        return driver.findElement(usernameLocator).getAttribute("value");
    }

    public String getTypedPassword() {
        return driver.findElement(passwordLocator).getAttribute("value");
    }

    public void clickOnLoginButton(WebElement button) {
       scrollToElement(button);
       actions.moveToElement(button).click().perform();
    }

    public WebElement getLoginButton() {
        waitActions.waitForElementVisibleByLocator(loginButton);
        return driver.findElement(loginButton);
    }

    public String getErrorMessage() {
        try {
            waitActions.waitForPageLoad();

            takeScreenshot("before_error_check.png");

            waitActions.waitForElementVisibleByLocator(errorMessageLocator);
            WebElement errorElement = driver.findElement(errorMessageLocator);
            return errorElement.getText();
        } catch (Exception e) {
            takeScreenshot("error_occurred.png");
            throw e;
        }
    }

    public void verifyProfilePageUrl(String currentUrl, String expectedUrl) {
        Assert.assertTrue("The profile page URL is incorrect", currentUrl.contains(expectedUrl));
    }

    public void verifyUsernames(String username, String typedUsername) {
        Assert.assertEquals("The typed username is not correct!", username, typedUsername);
    }

    public void verifyPasswords(String password, String typedPassword) {
        Assert.assertEquals("The typed password is not correct!", password, typedPassword);
    }

    public void verifyIsLoginSuccessful(String currentUrl, String expectedUrl) {
        Assert.assertTrue("User is not logged in", currentUrl.contains(expectedUrl));
    }

    public void verifyProfileTitle(WebElement profileTitle) {
        Assert.assertTrue("User is not logged in", profileTitle.isDisplayed());
    }

    public void verifyErrorMessage(String actualErrorMessage, String expectedErrorMessage) {
        String cleanedActualError = actualErrorMessage.replaceAll("\\s+", " ");
        String cleanedExpectedError = expectedErrorMessage.replaceAll("\\s+", " ");
        Assert.assertEquals("Error message is incorrect", cleanedExpectedError, cleanedActualError);
    }

    public void verifyLoginFormIsVisible(){
        waitActions.waitForElementVisibleByLocator(usernameLocator);
        Assert.assertTrue("The login form is not visible!", isLoginFormVisible());
    }

    private boolean isLoginFormVisible() {
        return isElementVisible(usernameLocator) &&
                isElementVisible(passwordLocator) &&
                isElementVisible(loginButton);
    }

    private boolean isElementVisible(By locator) {
        return driver.findElement(locator).isDisplayed();
    }
}
