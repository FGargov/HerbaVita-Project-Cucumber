package pageObjects;

import org.openqa.selenium.WebDriver;

public class PageObjectManager {
    private WebDriver driver;
    private HomePage homePage;

    public PageObjectManager(WebDriver driver) {
        this.driver = driver;
    }

    public HomePage getHomePage() {
        homePage = new HomePage(driver);
        return homePage;
    }
}
