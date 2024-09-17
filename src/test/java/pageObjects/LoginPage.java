package pageObjects;

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


    public WebDriverWait getWait() {
        return this.wait;
    }

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
        wait.until(ExpectedConditions.visibilityOf(icon));
        icon.click();
    }

    public void typeUsername(By usernameLocator, String username) {
        waitForElementVisible(usernameLocator);
        WebElement usernameField = driver.findElement(usernameLocator);
        usernameField.clear();
        usernameField.sendKeys(username);
    }

    public void typePassword(By passwordLocator, String password) {
        waitForElementVisible(passwordLocator);
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
        waitForElementVisible(loginButton);
        return driver.findElement(loginButton);
    }

    public String getErrorMessage() {
        try {
            waitForPageLoad();

            takeScreenshot("before_error_check.png");

            wait.until(ExpectedConditions.visibilityOfElementLocated(errorMessageLocator));
            WebElement errorElement = driver.findElement(errorMessageLocator);
            return errorElement.getText();
        } catch (Exception e) {
            takeScreenshot("error_occurred.png");
            throw e;
        }
    }
}
