package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class OfferPage {
    private WebDriver driver;
    private WebDriverWait wait;

    public OfferPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    private By productTitle = By.xpath("//h1[text()[normalize-space()='CR7 DRIVE С ВКУС НА АКАЙ БЕРИ']]");

    public WebDriverWait getWait() {
        return this.wait;
    }

    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    public String getProductTitle() {
        return driver.findElement(productTitle).getText();
    }

}
