package pageObjects;

import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCartPage extends BasePage {
    private Actions actions;
    private List<WebElement> productsToRemove;

    public ShoppingCartPage(WebDriver driver) {
        super(driver);
        this.actions = new Actions(driver);
        this.productsToRemove = new ArrayList<>();
    }

    private By productSearchField = By.xpath("//input[@aria-label='Search']");
    private By searchButton = By.xpath("//button[@type='submit']");
    private By productResults = By.xpath("//div[contains(@class, 'product-wrapper')]");
    private By addToCartButtonLocator = By.xpath("//a[contains(@class, 'add_to_cart_button') and @data-product_id]");private By closeShoppingCartButton = By.xpath("(//div[contains(@class,'close-side-widget wd-action-btn')]//a)[2]");
    private By cartItems = By.xpath("//ul[contains(@class,'cart_list')]//li[contains(@class,'woocommerce-mini-cart')]");
    private By addOneMoreItem = By.xpath("(//input[@type='button'])[2]");
    private By removeProductFromCart = By.xpath("//a[@class='remove remove_from_cart_button']");

    public WebElement getSearchButton() {
        return driver.findElement(searchButton);
    }

    public WebElement getAddToCartButton() {
        return driver.findElement(addToCartButtonLocator);
    }


    public void searchForProduct(String productName) {
        typeProductName(productSearchField, productName);
    }

    public void clickOnSearchButton(WebElement button) {
        waitForElementVisible(searchButton);
        button.click();
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

                productsToRemove.add(product);
                break;
            }
        }
    }


    private List<WebElement> getAllProductResults() {
        return driver.findElements(productResults);
    }

    public boolean isCartEmpty() {
        List<WebElement> items = driver.findElements(cartItems);
        return items.isEmpty();
    }

    public int getProductQuantity(String productName) {
        WebElement quantityElement = driver.findElement(By.xpath("//input[contains(@class,'input-text qty')]"));
        return Integer.parseInt(quantityElement.getAttribute("value"));
    }

    public void changeProductQuantity(String productName, String quantity) {
        WebElement quantityElement = waitForElementVisible(
                By.xpath("//span[contains(text(), '" + productName + "')]/ancestor::li//input[@type='number']"));

        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", quantityElement);

        quantityElement.clear();
        quantityElement.sendKeys(quantity);
        wait.until(ExpectedConditions.attributeToBe(quantityElement, "value", quantity));

        String actualResult = quantityElement.getAttribute("value");
        Assert.assertEquals("Quantity was not updated correctly", Integer.parseInt(quantity), Integer.parseInt(actualResult));
    }

    public void removeProductFromCart(String productName) {
        waitForElementVisible(removeProductFromCart);
        WebElement removeProduct = driver.findElement(removeProductFromCart);
        removeProduct.click();
        wait.until(ExpectedConditions.invisibilityOf(removeProduct));

        productsToRemove.removeIf(product -> product.getText().contains(productName));
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

    public void verifyListOfProductsIsEmpty() {
        Assert.assertEquals("List of products is not empty!", 0, productsToRemove.size());
    }

    public void verifyShoppingCartIsEmpty() {
        boolean isCartEmpty = isCartEmpty();

        Assert.assertTrue("No search results found!", isCartEmpty);
    }
}
