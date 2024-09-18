package pageObjects;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class DashboardPage extends BasePage {

    public DashboardPage(WebDriver driver) {
        super(driver);
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
        return waitForElementVisibleByLocator(profileTitle).isDisplayed();
    }

    public void verifyLinkUrl(By elementLocator, String expectedUrl) {
        WebElement element = waitForElementVisibleByLocator(elementLocator);
        String actualUrl = element.getAttribute("href");
        Assert.assertEquals("The URL is incorrect for the element: " + elementLocator.toString(), expectedUrl, actualUrl);
    }

    public void verifyLogoutLinkUrl(By elementLocator, String expectedUrl) {
        WebElement element = waitForElementVisibleByLocator(elementLocator);
        String actualUrl = element.getAttribute("href");

        String expectedStaticUrl = expectedUrl.split("\\?_wpnonce")[0];
        String actualStaticUrl = actualUrl.split("\\?_wpnonce")[0];

        Assert.assertEquals("The URL is incorrect for the logout link: " + elementLocator.toString(), expectedStaticUrl, actualStaticUrl);
    }

    public void verifyProfileTitle() {
        Assert.assertTrue("Profile title is not displayed!", isProfileTitleDisplayed());
    }
}
