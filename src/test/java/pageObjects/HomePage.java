package pageObjects;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.TestData;

import java.util.List;

public class HomePage extends BasePage {
    private final String HOME_PAGE_URL = TestData.getHomePageData().get("url").asText();
    private final String MY_PROFILE_LOGIN_PAGE_URL = TestData.getHomePageData().get("loginPageUrl").asText();
    private final String PROMO_PRODUCT_TITLE = TestData.getHomePageData().get("promoProductTitle").asText();

    public HomePage(WebDriver driver) {
        super(driver);
    }

    //private By homePageLogo = By.xpath("//a[@class='wd-logo wd-main-logo']//img");
    private By homePageLogo = By.xpath("//a[@class='wd-logo wd-main-logo']//img");
    private By menuItems = By.xpath("//ul[@id='menu-categories']//li/a");
    private By offerFrame = By.xpath("//rs-slide[@data-anim='ms:600;']");
    private By buyNowButton = By.xpath("//*[@id='slider-45-slide-110-layer-10']");
    private By logoutButton = By.xpath("//div[@class='customer-logout-link']//a[1]");

    public String getTitleHomePage() {
        return driver.getTitle();
    }

    public boolean isHomePageURLCorrect() {
        return driver.getCurrentUrl().equals(HOME_PAGE_URL);
    }
    public boolean isLoginPageURLCorrect() {
        waitActions.waitForPageLoad();
        return driver.getCurrentUrl().equals(MY_PROFILE_LOGIN_PAGE_URL);
    }


    public WebElement getHomePageLink() {
        waitActions.waitForElementVisibleByLocator(homePageLogo);
        return driver.findElement(homePageLogo);
    }

    public WebElement getLogoutButton() {
        return driver.findElement(logoutButton);
    }

    public boolean isLogoDisplayed() {
       return driver.findElement(homePageLogo).isDisplayed();
    }


    public List<WebElement> getNavigationMenuItems() {
        return driver.findElements(menuItems);
    }

    public void clickMenuItem(WebElement menuItem) {
        menuItem.click();
    }

    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    public void clickOnPromoButton(WebElement button) {
        clickOnButton(button);
    }

    public WebElement getBuyNowButton() {
        waitActions.waitForElementVisibleByLocator(buyNowButton);
        return driver.findElement(buyNowButton);
    }

    public boolean getOfferFrame() {
        waitActions.waitForElementVisibleByLocator(offerFrame);
        return driver.findElement(offerFrame).isDisplayed();
    }

    public void clickLogoutButton() {
        waitActions.waitForElementVisibleByLocator(logoutButton);
        clickOnButton(getLogoutButton());
    }

    public void verifyUserIsOnHomePage() {
        String expectedTitle = TestData.getHomePageData().get("title").asText();
        Assert.assertTrue(getTitleHomePage().contains(expectedTitle));
    }

    public void verifyHomePageURLIsCorrect() {
        Assert.assertTrue("The home page URL is incorrect", isHomePageURLCorrect());
    }

    public void verifyLoginPageURLIsCorrect() {
        Assert.assertTrue("The home page URL is incorrect", isLoginPageURLCorrect());
    }

    public void verifyHomepageLogoIsDisplayed() {
        Assert.assertTrue("The site logo is not displayed correctly", isLogoDisplayed());
    }

    public void verifyOfferFrameIsDisplayed() {
        Assert.assertTrue("The promotional banner is not displayed correctly", getOfferFrame());
    }

    public void verifyOfferPageURLIsCorrect(String currentUrl, String expectedUrl) {
        Assert.assertEquals("The redirection is not to the correct page!", expectedUrl, currentUrl);
    }

    public void verifyProductTitleIsCorrect(String productTitle) {
        Assert.assertEquals("The product title is not displayed correctly", PROMO_PRODUCT_TITLE, productTitle);
    }

    public void verifyNavigationToExpectedPage(String currentUrl, String expectedUrl) {
        Assert.assertEquals("The redirection is not to the correct page!", expectedUrl, currentUrl);
    }
}
