package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class HomePage extends BasePage {
    private final String HOME_PAGE_URL = "https://herba-vita.eu/stage/";

    public HomePage(WebDriver driver) {
        super(driver);
    }

    //private By homePageLogo = By.xpath("//a[@class='wd-logo wd-main-logo']//img");
    private By homePageLogo = By.xpath("//a[@class='wd-logo wd-main-logo']//img");
    private By menuItems = By.xpath("//ul[@id='menu-categories']//li/a");
    private By offerFrame = By.xpath("//rs-slide[@data-anim='ms:600;']");
    private By buyNowButton = By.xpath("//*[@id='slider-45-slide-110-layer-10']");

    public WebDriverWait getWait() {
        return this.wait;
    }

    public String getTitleHomePage() {
        return driver.getTitle();
    }

    public boolean isHomePageURLCorrect() {
        return driver.getCurrentUrl().equals(HOME_PAGE_URL);
    }

    public WebElement getHomePageLink() {
        waitForElementVisible(homePageLogo);
        return driver.findElement(homePageLogo);
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
        waitForElementVisible(buyNowButton);
        return driver.findElement(buyNowButton);
    }

    public boolean getOfferFrame() {
        waitForElementVisible(offerFrame);
        return driver.findElement(offerFrame).isDisplayed();
    }

}
