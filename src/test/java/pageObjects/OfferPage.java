package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class OfferPage extends BasePage {

    public OfferPage(WebDriver driver) {
       super(driver);
    }

    private By productTitle = By.xpath("//h1[text()[normalize-space()='CR7 DRIVE С ВКУС НА АКАЙ БЕРИ']]");

    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    public String getProductTitle() {
        waitActions.waitForElementVisibleByLocator(productTitle);
        return driver.findElement(productTitle).getText();
    }

}
