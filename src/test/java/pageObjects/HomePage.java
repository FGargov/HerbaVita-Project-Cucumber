package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HomePage {
    private WebDriver driver;
    private WebDriverWait wait;

    public HomePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    //private By homePageLogo = By.xpath("//a[@class='wd-logo wd-main-logo']//img");
    private By homePageLogo = By.xpath("//a[@class='wd-logo wd-main-logo']//img");
    private By menuItems = By.xpath("//span[contains(@class,'menu-opener color-scheme-light')]");

    public String getTitleHomePage() {
        return driver.getTitle();
    }

    public boolean isHomePageURLCorrect() {
        String expectedURL = "https://herba-vita.eu/stage/";
        return driver.getCurrentUrl().equals(expectedURL);
    }


    public boolean isLogoDisplayed() {
       return driver.findElement(homePageLogo).isDisplayed();
    }
}
