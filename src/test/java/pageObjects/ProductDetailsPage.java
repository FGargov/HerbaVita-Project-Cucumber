package pageObjects;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class ProductDetailsPage extends BasePage {
    private final String FIRST_PRODUCT_URL = "https://herba-vita.eu/stage/produkt/sheik-formula-1-malina-i-bql-shokolad/";
    private final String PRODUCT_PAGE_TITLE = "ШЕЙК ФОРМУЛA 1 МАЛИНА И БЯЛ ШОКОЛАД";

    public ProductDetailsPage(WebDriver driver) {
        super(driver);
    }

    private By menuItems = By.xpath("//ul[@id='menu-categories']//li/a");
    private By listedProducts = By.xpath("//div[@class='product-wrapper']");
    private By productTitle = By.xpath("//h1[contains(@class,'product_title entry-title')]");

    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    public WebDriverWait getWait() {
        return this.wait;
    }

    public WebElement getProductTitle() {
        return driver.findElement(productTitle);
    }

    public List<WebElement> getListedProducts() {
        scrollDownByOffset();
        return driver.findElements(listedProducts);
    }

    public List<WebElement> getAllProductResults() {
        return driver.findElements(listedProducts);
    }

    public void clickOnFirstListedProduct() {
        WebElement firstProduct = getListedProducts().get(0).findElement(By.tagName("a"));
        firstProduct.getAttribute("href");
        clickOnButton(firstProduct);
    }

    public List<WebElement> getNavigationMenuItems() {
        return driver.findElements(menuItems);
    }

    public void clickMenuItem(WebElement menuItem) {
        menuItem.click();
    }
    public void verifyProductDetailsPageUrl() {
        waitForPageLoad();
        String currentUrl = getCurrentUrl();
        String expectedUrl = FIRST_PRODUCT_URL;

        verifyProductDetailsPageURLIsCorrect(expectedUrl, currentUrl);
    }

    public void verifyProductTitle() {
        String productTitle = getProductTitle().getText();
        verifyProductTitleIsCorrect(productTitle);
    }

    public void verifyNavigationToExpectedPage(String currentUrl, String expectedUrl) {
        Assert.assertEquals("The redirection is not to the correct page!", expectedUrl, currentUrl);
    }

    public void verifyProductDetailsPageURLIsCorrect(String currentUrl, String expectedUrl) {
        Assert.assertEquals("The redirection is not to the correct page!", expectedUrl, currentUrl);
    }

    public void verifyProductTitleIsCorrect(String productTitle) {
        Assert.assertEquals("The product title is not displayed correctly", PRODUCT_PAGE_TITLE, productTitle);
    }

    public void verifyProductsAreVisible() {
        List<WebElement> listedProducts = getListedProducts();

        Assert.assertTrue("No products are listed under the category!", !listedProducts.isEmpty());

        for (WebElement listedProduct : listedProducts) {
            Assert.assertTrue("Product is not visible!", listedProduct.isDisplayed());
        }
    }
}
