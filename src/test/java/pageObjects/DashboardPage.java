package pageObjects;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class DashboardPage {

    private WebDriver driver;
    private WebDriverWait wait;

    public DashboardPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    private By profileTitle = By.xpath("//h1[contains(@class,'entry-title title') and normalize-space(text())='Моят профил']");
    private By welcomeMessage = By.xpath("//div[@class='woocommerce-MyAccount-content']//p/strong[contains(text(),'fero')]");
    private By ordersLink = By.xpath("//div[@class='orders-link']/a");
    private By downloadsLink = By.xpath("//div[@class='downloads-link']//a[1]");
    private By logoutLink = By.xpath("//div[@class='customer-logout-link']//a[1]");


    public By getProfileTitle() {
        return this.profileTitle;
    }

    public By getWelcomeMessage() {
        return this.welcomeMessage;
    }

    public By getOrdersLink() {
        return this.ordersLink;
    }

    public By getDownloadsLink() {
        return this.downloadsLink;
    }

    public By getLogoutLink() {
        return this.logoutLink;
    }

    public boolean isProfileTitleDisplayed() {
        return waitForElementVisible(profileTitle).isDisplayed();
    }

    public boolean isWelcomeMessageDisplayed() {
        return waitForElementVisible(welcomeMessage).isDisplayed();
    }

    public boolean isOrdersLinkDisplayed() {
        scrollToElement(ordersLink);

        wait.until(ExpectedConditions.visibilityOfElementLocated(ordersLink));
        return waitForElementVisible(ordersLink).isDisplayed();
    }

    public boolean isDownloadsLinkDisplayed() {
        return waitForElementVisible(downloadsLink).isDisplayed();
    }

    public boolean isLogoutLinkDisplayed() {
        return waitForElementVisible(logoutLink).isDisplayed();
    }

    private WebElement waitForElementVisible(By locator) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    private void scrollToElement(By locator) {
        WebElement element = driver.findElement(locator);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
    }

    public void verifyLinkUrl(By elementLocator, String expectedUrl) {
        WebElement element = waitForElementVisible(elementLocator);
        String actualUrl = element.getAttribute("href");
        Assert.assertEquals("The URL is incorrect for the element: " + elementLocator.toString(), expectedUrl, actualUrl);
    }

    public void verifyLogoutLinkUrl(By elementLocator, String expectedUrl) {
        WebElement element = waitForElementVisible(elementLocator);
        String actualUrl = element.getAttribute("href");

        String expectedStaticUrl = expectedUrl.split("\\?_wpnonce")[0];
        String actualStaticUrl = actualUrl.split("\\?_wpnonce")[0];

        Assert.assertEquals("The URL is incorrect for the logout link: " + elementLocator.toString(), expectedStaticUrl, actualStaticUrl);
    }
}
