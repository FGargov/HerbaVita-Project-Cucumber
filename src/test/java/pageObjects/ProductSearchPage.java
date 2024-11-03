package pageObjects;

import org.junit.Assert;
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


    public By getProductSearchField() {
        return this.productSearchField;
    }

    public WebElement getAllResultsButton() {
        waitActions.waitForElementVisibleByLocator(allResultsButton);
        return driver.findElement(allResultsButton);
    }

    public void clickOnAllResultsButton(WebElement button) {
        waitActions.waitForElementVisibleByLocator(allResultsButton);
        button.click();
    }

    public List<WebElement> getProductsAutocompleteSuggestions() {
        waitActions.waitForElementVisibleByLocator(productsAutocompleteSuggestions);
        return driver.findElements(productsAutocompleteSuggestions);
    }

    public WebElement getErrorMessage() {
        waitActions.waitForElementVisibleByLocator(notFoundProductsMessage);
        return driver.findElement(notFoundProductsMessage);
    }

    public WebElement getResultsPageTitle() {
        return driver.findElement(resultsPageTitle);
    }

    public void searchForProduct(String productName) {
        typeProductName(productSearchField, productName);
    }

    public boolean isProductInSearchSuggestions(String productName) {
        List<WebElement> suggestions = getProductsAutocompleteSuggestions();
        return suggestions.stream()
                .anyMatch(suggestion -> {
                    WebElement strongElement = suggestion.findElement(By.tagName("strong"));
                    String productText = strongElement.getText();
                    return productText.toLowerCase().contains(productName.toLowerCase());
                });
    }

    public void verifyErrorMessage(String expectedErrorMessage, String actualErrorMessage) {
        Assert.assertEquals("Error message is incorrect", expectedErrorMessage, actualErrorMessage);
    }

    public void verifyNumberOfSearchedProducts(int numberOfResults, int actualNumberOfResults) {
        Assert.assertEquals("Expected number of results did not match", numberOfResults, actualNumberOfResults);
    }

    public void verifyViewAllResultsButton(String expectedButtonName, String actualButtonName) {
        Assert.assertEquals("The button name is incorrect", expectedButtonName, actualButtonName);
    }

    public void verifyPageTitle(String expectedResultsPageTitle, String resultsPageTitle) {
        Assert.assertEquals("The results page title is not as expected!", expectedResultsPageTitle, resultsPageTitle);
    }
}
