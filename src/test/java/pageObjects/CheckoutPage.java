package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckoutPage {
    public WebDriver driver;

    public CheckoutPage(WebDriver driver) {
        this.driver = driver;
    }

    private By cardBag = By.xpath("//img[@alt='Cart']");
    private By checkoutButton = By.xpath("//button[text()='PROCEED TO CHECKOUT']");
    private By promoBtn = By.xpath("//button[@class='promoBtn']");
    private By placeOrder = By.xpath("//button[text()='Place Order']");

    public void CheckoutItems() {
        driver.findElement(cardBag).click();
        driver.findElement(checkoutButton).click();
    }

    public Boolean VerifyPromoBtn() {
        return driver.findElement(promoBtn).isDisplayed();
    }

    public Boolean VerifyPlanOrder() {
        return driver.findElement(placeOrder).isDisplayed();
    }
}
