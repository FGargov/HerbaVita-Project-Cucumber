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
    private By addToCartButtonLocator = By.xpath("//a[contains(@class, 'add_to_cart_button') and @data-product_id]");
    private By cartItems = By.xpath("//ul[contains(@class,'cart_list')]//li[contains(@class,'woocommerce-mini-cart')]");
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
        waitActions.waitForElementVisibleByLocator(searchButton);
        button.click();
    }

    public void addProductToCart(String productName) {
        List<WebElement> allProducts = getAllProductResults();

        for (WebElement product : allProducts) {
            String productText = product.getText();

            System.out.println("Checking product: " + productText);

            if (productText.contains(productName)) {
                scrollDownByOffset();
                actions.moveToElement(product).perform();

                WebElement addToCartButton = getAddToCartButton();
                waitActions.waitForElementVisible(addToCartButton);
                waitActions.waitForElementClickable(addToCartButton);

                System.out.println("Adding product to cart: " + productName);
                addToCartButton.click();
                productsToRemove.add(product);
                break;
            }
        }
    }


    private List<WebElement> getAllProductResults() {
        return driver.findElements(productResults);
    }

    private boolean isCartEmpty() {
        List<WebElement> items = driver.findElements(cartItems);
        return items.isEmpty();
    }

    public int getProductQuantity(String productName) {
        WebElement quantityElement = driver.findElement(By.xpath("//input[contains(@class,'input-text qty')]"));
        return Integer.parseInt(quantityElement.getAttribute("value"));
    }

    public void changeProductQuantity(String productName, String quantity) {
        WebElement quantityElement = waitActions.waitForElementVisibleByLocator(
                By.xpath("//span[contains(text(), '" + productName + "')]/ancestor::li//input[@type='number']"));

        scrollToElement(quantityElement);

        quantityElement.clear();
        quantityElement.sendKeys(quantity);
        waitActions.waitAttributeToBe(quantityElement, "value", quantity);

        String actualResult = quantityElement.getAttribute("value");
        Assert.assertEquals("Quantity was not updated correctly", Integer.parseInt(quantity), Integer.parseInt(actualResult));
    }

    public void removeProductFromCart(String productName) {
        waitActions.waitForElementVisibleByLocator(removeProductFromCart);
        WebElement removeProduct = driver.findElement(removeProductFromCart);
        removeProduct.click();
        waitActions.waitForElementToDisappear(removeProduct);

        productsToRemove.removeIf(product -> product.getText().contains(productName));
    }
    public void verifySearchResultAreDisplayed() {
        waitActions.waitForElementVisibleByLocator(productResults);
        List<WebElement> allSearchResults = getAllProductResults();

        Assert.assertTrue("No search results found!", !allSearchResults.isEmpty());
    }

    public void verifyShoppingCartIsNotEmpty() {
        boolean isCartEmpty = isCartEmpty();

        Assert.assertFalse("Shopping cart is empty, but it was expected to contain items!", !isCartEmpty);
    }

    public void verifyListOfProductsIsEmpty() {
        Assert.assertEquals("List of products is not empty!", 0, productsToRemove.size());
    }

    public void verifyShoppingCartIsEmpty() {
        boolean isCartEmpty = isCartEmpty();
        Assert.assertTrue("Shopping cart is not empty!", isCartEmpty);

        Assert.assertEquals("List of products is not empty!", 0, productsToRemove.size());
    }

    public void verifyUpdatedQuantity(int expectedQuantity, int actualQuantity) {
        Assert.assertEquals("Quantity does not match!", expectedQuantity, actualQuantity);
    }
}
