package pageObjects;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.TestData;

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
    private By checkoutButton = By.xpath("//a[contains(@class,'button checkout')]");
    private By firstNameField = By.xpath("//input[@id='billing_first_name']");
    private By lastNameField = By.xpath("//input[@id='billing_last_name']");
    private By addressField = By.xpath("//input[@id='billing_address_1']");
    private By cityField = By.xpath("//input[@id='billing_city']");
    private By postcodeField = By.xpath("//input[@id='billing_postcode']");
    private By phoneField = By.xpath("//input[@id='billing_phone']");
    private By emailField = By.xpath("//input[@id='billing_email']");
    private By termsCheckbox = By.xpath("//input[@id='terms']");
    private By orderButton = By.xpath("//button[@id='place_order']");
    private By completedOrderMessage = By.xpath("//p[text()[normalize-space()='Благодарности. Вашата поръчка беше получена.']]");


    public WebElement getFirstName() {
        return driver.findElement(firstNameField);
    }

    public WebElement getLastName() {
        return driver.findElement(lastNameField);
    }

    public WebElement getAddress() {
        return driver.findElement(addressField);
    }

    public WebElement getCity() {
        return driver.findElement(cityField);
    }

    public WebElement getPostcode() {
        return driver.findElement(postcodeField);
    }

    public WebElement getPhone() {
        return driver.findElement(phoneField);
    }

    public WebElement getEmail() {
        return driver.findElement(emailField);
    }

    public WebElement getTermsCheckbox() {
        return driver.findElement(termsCheckbox);
    }

    public WebElement getOrderButton() {
        return driver.findElement(orderButton);
    }

    public WebElement getCompletedOrderMessage() {
        return driver.findElement(completedOrderMessage);
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

    public WebElement getCheckoutButton() {
        return driver.findElement(checkoutButton);
    }

    public void searchForProduct(String productName) {
        typeProductName(productSearchField, productName);
    }

    public void clickOnSearchButton(WebElement button) {
        waitActions.waitForElementVisibleByLocator(searchButton);
        button.click();
    }

    public void clickOnOrderButton(WebElement button) {
        clickOnButton(button);
    }

    public void addProductToCart(String productName) {
        waitActions.waitForPageLoad();
        List<WebElement> allProducts = getAllProductResults();

        for (WebElement product : allProducts) {
            String productText = product.getText();

            if (productText.contains(productName)) {
                scrollDownByOffset();
                actions.moveToElement(product).perform();
                WebElement addToCartButton = getAddToCartButton();
                addToCartButton.click();

                waitActions.waitForElementVisibleByLocator(cartItems);

                break;
            }
        }
    }

    private List<WebElement> getAllProductResults() {
        return driver.findElements(productResults);
    }

    public void enterShippingInformation(List<String> data) {
        waitActions.waitForPageToBeReady();
        waitActions.waitForElementVisibleByLocator(firstNameField);

        String firstName = TestData.getAddressField("firstName");
        String lastName = TestData.getAddressField("lastName");
        String address = TestData.getAddressField("address");
        String city = TestData.getAddressField("city");
        String postalCode = TestData.getAddressField("postalCode");
        String phone = TestData.getAddressField("phone");
        String email = TestData.getAddressField("email");

        enterData(firstNameField, firstName);
        enterData(lastNameField, lastName);
        enterData(addressField, address);
        enterData(cityField, city);
        enterData(postcodeField, postalCode);
        enterData(phoneField, phone);
        enterData(emailField, email);

        scrollToElement(getTermsCheckbox());
        clickOnButton(getTermsCheckbox());
    }

    private void enterData(By locator, String data) {
        WebElement element = driver.findElement(locator);
        element.clear();
        element.sendKeys(data);
    }

    public void clickOnOrderButton() {
        waitActions.waitForElementVisibleByLocator(orderButton);
        WebElement button = getOrderButton();
        clickOnButton(button);
    }

    public void verifySearchResultAreDisplayed() {
        waitActions.waitForElementVisibleByLocator(productResults);
        List<WebElement> allSearchResults = getAllProductResults();

        Assert.assertTrue("No search results found!", !allSearchResults.isEmpty());
    }

    public void verifyShoppingCartIsNotEmpty() {
        waitActions.waitForElementVisibleByLocator(cartItems);
        boolean isCartEmpty = isCartEmpty();

        Assert.assertFalse("No search results found!", isCartEmpty);
    }

    private boolean isCartEmpty() {
        List<WebElement> items = driver.findElements(cartItems);
        return items.isEmpty();
    }

    public void verifyPageUrl(String currentUrl, String expectedUrl) {
        Assert.assertTrue("The checkout page URL is incorrect", currentUrl.contains(expectedUrl));
    }

    public void confirmPageIsLoaded() {
        waitActions.waitForPageLoad();
    }

    public void verifyShippingInformation(List<String> data) {
        String expectedFirstName = data.get(0);
        String expectedLastName = data.get(1);
        String expectedAddress = data.get(2);
        String expectedCity = data.get(3);
        String expectedPostcode = data.get(4);
        String expectedPhone = data.get(5);
        String expectedEmail = data.get(6);

        verifyAllShippingInformation(expectedFirstName, expectedLastName,
                expectedAddress, expectedCity, expectedPostcode, expectedPhone, expectedEmail);
    }

    private void verifyAllShippingInformation(String expectedFirstName, String expectedLastName, String expectedAddress,
                                              String expectedCity, String expectedPostcode, String expectedPhone, String expectedEmail) {
        String actualFirstName = getFirstName().getAttribute("value");
        Assert.assertEquals("First name is incorrect", expectedFirstName, actualFirstName);

        String actualLastName = getLastName().getAttribute("value");
        Assert.assertEquals("Last name is incorrect", expectedLastName, actualLastName);

        String actualAddress = getAddress().getAttribute("value");
        Assert.assertEquals("Address is incorrect", expectedAddress, actualAddress);

        String actualCity = getCity().getAttribute("value");
        Assert.assertEquals("City is incorrect", expectedCity, actualCity);

        String actualPostcode = getPostcode().getAttribute("value");
        Assert.assertEquals("Postcode is incorrect", expectedPostcode, actualPostcode);

        String actualPhone = getPhone().getAttribute("value");
        Assert.assertEquals("Phone is incorrect", expectedPhone, actualPhone);

        String actualEmail = getEmail().getAttribute("value");
        Assert.assertEquals("Email is incorrect", expectedEmail, actualEmail);
    }

    public void verifyOrderCompletedMessage(String actualMessage, String expectedMessage) {
        Assert.assertEquals("Order completed message is incorrect", expectedMessage, actualMessage);
    }
}
