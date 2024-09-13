package pageObjects;

import org.openqa.selenium.WebDriver;

public class PageObjectManager {
    private WebDriver driver;
    private HomePage homePage;
    private OfferPage offerPage;
    private LoginPage loginPage;
    private DashboardPage dashboardPage;

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
}
