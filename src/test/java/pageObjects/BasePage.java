package pageObjects;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

public class BasePage {
    protected WebDriver driver;
    protected WebDriverWait wait;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    protected WebElement waitForElementVisible(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    protected void typeProductName(By productSearchField, String productName) {
        waitForElementVisible(productSearchField);
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

    protected void scrollDown(WebElement button) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("arguments[0].scrollIntoView(true);", button);
    }

    public void scrollDownByOffset() {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("window.scrollBy(0,400)");
    }
}
