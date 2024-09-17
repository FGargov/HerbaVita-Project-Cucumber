package pageObjects;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.swing.*;
import java.util.List;

public class CheckoutPage extends BasePage {
    private Actions actions;

    public CheckoutPage(WebDriver driver) {
        super(driver);
        this.actions = new Actions(driver);
    }

    private By productSearchField = By.xpath("//input[@aria-label='Search']");
    private By searchButton = By.xpath("//button[@type='submit']");
    private By productResults = By.xpath("//div[contains(@class, 'product-wrapper')]");
    private By addToCartButtonLocator = By.xpath("//a[contains(@class, 'add_to_cart_button') and @data-product_id]");
    private By cartItems = By.xpath("//ul[contains(@class,'cart_list')]//li[contains(@class,'woocommerce-mini-cart')]");
    private By orderButton = By.xpath("//a[contains(@class,'button checkout')]");

    public WebDriverWait getWait() {
        return this.wait;
    }

    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }
    public WebElement getSearchButton() {
        return driver.findElement(searchButton);
    }

    public WebElement getAddToCartButton() {
        return driver.findElement(addToCartButtonLocator);
    }

    public WebElement getOrderButton() {
        return driver.findElement(orderButton);
    }

    public void searchForProduct(String productName) {
        typeProductName(productSearchField, productName);
    }

    public void clickOnSearchButton(WebElement button) {
        waitForElementVisible(searchButton);
        button.click();
    }

    public void clickOnOrderButton(WebElement button) {
       clickOnButton(button);
    }

    public void addProductToCart(String productName) {
        List<WebElement> allProducts = getAllProductResults();

        for (WebElement product : allProducts) {
            String productText = product.getText();

            if (productText.contains(productName)) {
                scrollDownByOffset();
                actions.moveToElement(product).perform();
                WebElement addToCartButton = getAddToCartButton();
                addToCartButton.click();

                break;
            }
        }
    }

    private List<WebElement> getAllProductResults() {
        return driver.findElements(productResults);
    }

    public void verifySearchResultAreDisplayed() {
        waitForElementVisible(productResults);
        List<WebElement> allSearchResults = getAllProductResults();

        Assert.assertTrue("No search results found!", !allSearchResults.isEmpty());
    }

    public void verifyShoppingCartIsNotEmpty() {
        boolean isCartEmpty = isCartEmpty();

        Assert.assertFalse("No search results found!", isCartEmpty);
    }

    private boolean isCartEmpty() {
        List<WebElement> items = driver.findElements(cartItems);
        return items.isEmpty();
    }

}
