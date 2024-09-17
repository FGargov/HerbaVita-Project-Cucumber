package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class ProductSearchPage extends BasePage {
    public ProductSearchPage(WebDriver driver) {
        super(driver);
    }

    private By productSearchField = By.xpath("//input[@aria-label='Search']");
    private By productsAutocompleteSuggestions = By.xpath("//div[@class='autocomplete-suggestions']//div[contains(@class, 'autocomplete-suggestion')]");
    private By notFoundProductsMessage = By.xpath("//div[@class='autocomplete-suggestion suggestion-no-found']//span[1]");
    private By allResultsButton = By.xpath("//div[@class='view-all-results']//span[1]");
    private By resultsPageTitle = By.xpath("//h1[@class='entry-title title']");

    public WebDriverWait getWait() {
        return wait;
    }

    public By getProductSearchField() {
        return this.productSearchField;
    }

    public WebElement getAllResultsButton() {
        waitForElementVisibleByLocator(allResultsButton);
        return driver.findElement(allResultsButton);
    }

    public void clickOnAllResultsButton(WebElement button) {
        waitForElementVisibleByLocator(allResultsButton);
        button.click();
    }

    public List<WebElement> getProductsAutocompleteSuggestions() {
        waitForElementVisibleByLocator(productsAutocompleteSuggestions);
        return driver.findElements(productsAutocompleteSuggestions);
    }

    public WebElement getErrorMessage() {
        waitForElementVisibleByLocator(notFoundProductsMessage);
        return driver.findElement(notFoundProductsMessage);
    }

    public WebElement getResultsPageTitle() {
        return driver.findElement(resultsPageTitle);
    }

    public void searchForProduct(String productName) {
        typeProductName(productSearchField, productName);
    }
}
