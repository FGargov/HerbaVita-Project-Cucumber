package pageObjects;

import org.openqa.selenium.WebDriver;

public class PageObjectManager {
    private WebDriver driver;
    private HomePage homePage;
    private OfferPage offerPage;
    private LoginPage loginPage;
    private DashboardPage dashboardPage;
    private ProductSearchPage productSearchPage;
    private ShoppingCartPage shoppingCartPage;
    private CheckoutPage checkoutPage;
    private ProductDetailsPage productDetailsPage;
    private ContactInfoPage contactInfoPage;

    public PageObjectManager(WebDriver driver) {
        this.driver = driver;
    }

    public HomePage getHomePage() {
        if (homePage == null) {
            homePage = new HomePage(driver);
        }
        return homePage;
    }

    public OfferPage getOfferPage() {
        if (offerPage == null) {
            offerPage = new OfferPage(driver);
        }
        return offerPage;
    }

    public LoginPage getLoginPage() {
        if (loginPage == null) {
            loginPage = new LoginPage(driver);
        }
        return loginPage;
    }

    public DashboardPage getDashboardPage() {
        if (dashboardPage == null) {
            dashboardPage = new DashboardPage(driver);
        }
        return dashboardPage;
    }

    public ProductSearchPage getProductSearchPage() {
        if (productSearchPage == null) {
            productSearchPage = new ProductSearchPage(driver);
        }
        return productSearchPage;
    }

    public ShoppingCartPage getShoppingCartPage() {
        if (shoppingCartPage == null) {
            shoppingCartPage = new ShoppingCartPage(driver);
        }
        return shoppingCartPage;
    }

    public CheckoutPage getCheckoutPage() {
        if (checkoutPage == null) {
            checkoutPage = new CheckoutPage(driver);
        }
        return checkoutPage;
    }

    public ProductDetailsPage getProductDetailsPage() {
        if (productDetailsPage == null) {
            productDetailsPage = new ProductDetailsPage(driver);
        }
        return productDetailsPage;
    }

    public ContactInfoPage getContactInfoPage() {
        if (contactInfoPage == null) {
            contactInfoPage = new ContactInfoPage(driver);
        }
        return contactInfoPage;
    }
}
