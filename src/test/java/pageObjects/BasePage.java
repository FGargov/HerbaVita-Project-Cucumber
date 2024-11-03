package pageObjects;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.WaitActions;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

public class BasePage {
    protected WebDriver driver;
    public WaitActions waitActions;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.waitActions = new WaitActions(driver, 30);
    }

    protected void typeProductName(By productSearchField, String productName) {
        waitActions.waitForElementVisibleByLocator(productSearchField);
        WebElement searchProductField = driver.findElement(productSearchField);
        searchProductField.clear();
        searchProductField.sendKeys(productName);
    }

    protected void takeScreenshot(String fileName) {
        try {
            String projectDirectory = System.getProperty("user.dir");
            String screenshotDirectory = projectDirectory + "/screenshots/";

            File directory = new File(screenshotDirectory);
            if (!directory.exists()) {
                directory.mkdirs();
            }

            File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            File destinationFile = new File(screenshotDirectory + fileName);
            FileUtils.copyFile(screenshot, destinationFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected void scrollToElement(WebElement locator) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("arguments[0].scrollIntoView(true);", locator);
    }

    protected void scrollDownByOffset() {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("window.scrollBy(0,400)");
    }

    protected void clickOnButton(WebElement button) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", button);
    }

    protected void sendKeysUsingJavaScript(WebElement element, String value) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("arguments[0].value='" + value + "';", element);
    }
}
