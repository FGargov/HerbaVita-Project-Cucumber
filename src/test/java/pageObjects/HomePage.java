package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class HomePage {
    private WebDriver driver;
    private WebDriverWait wait;

    public HomePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
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
        String expectedURL = "https://herba-vita.eu/stage/";
        return driver.getCurrentUrl().equals(expectedURL);
    }

    public WebElement getHomePageLink() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(homePageLogo));
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
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", button);
    }

    public WebElement getBuyNowButton() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(buyNowButton));
        return driver.findElement(buyNowButton);
    }

    public boolean getOfferFrame() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(offerFrame));
        return driver.findElement(offerFrame).isDisplayed();
    }

}
