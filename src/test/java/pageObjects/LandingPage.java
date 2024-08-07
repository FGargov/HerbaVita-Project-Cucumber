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
}
