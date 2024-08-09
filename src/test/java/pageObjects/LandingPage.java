package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LandingPage {
    public WebDriver driver;

    public LandingPage(WebDriver driver) {
        this.driver = driver;
    }

    private By search = By.xpath("//input[@type='search']");
    private By productName = By.xpath("//h4[text()='Tomato - 1 Kg']");
    private By topDealsLink = By.xpath("//a[@class='cart-header-navlink']");
    private By increment = By.xpath("//a[@class='increment']");
    private By addToCard = By.cssSelector(".product-action button");

    public void searchItem(String name) {
        driver.findElement(search).sendKeys(name);
    }

    public void getSearchText() {
        driver.findElement(search).getText();
    }

    public String getProductName() {
        return driver.findElement(productName).getText();
    }

    public void selectTopDealsLink() {
        driver.findElement(topDealsLink).click();
    }

    public String getTitleLandingPage() {
        return driver.getTitle();
    }

    public void incrementQuantity(int quantity) {
        int i = quantity - 1;
        while (i > 0) {
            driver.findElement(increment).click();
            i--;
        }
    }

    public void setAddToCard() {
        driver.findElement(addToCard).click();
    }
}
