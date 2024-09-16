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

    public PageObjectManager(WebDriver driver) {
        this.driver = driver;
    }

    public HomePage getHomePage() {
        homePage = new HomePage(driver);
        return homePage;
    }

    public OfferPage getOfferPage() {
        offerPage = new OfferPage(driver);
        return offerPage;
    }

    public LoginPage getLoginPage() {
        loginPage = new LoginPage(driver);
        return loginPage;
    }

    public DashboardPage getDashboardPage() {
        dashboardPage = new DashboardPage(driver);
        return dashboardPage;
    }

    public ProductSearchPage getProductSearchPage() {
        productSearchPage = new ProductSearchPage(driver);
        return productSearchPage;
    }

    public ShoppingCartPage getShoppingCartPage() {
        shoppingCartPage = new ShoppingCartPage(driver);
        return shoppingCartPage;
    }
}
