package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import pageObjects.BasePage;

import java.io.*;
import java.time.Duration;
import java.util.Properties;

public class TestBase  {
    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();
    private BasePage basePage;

    public WebDriver getDriver() throws IOException {
        if (driver.get() == null) {
            initializeDriver();
        }
        return driver.get();
    }

    private void initializeDriver() throws IOException{
        BrowserType browserType = BrowserType.valueOf(ConfigManager.getProperty("browser", "CHROME").toUpperCase());
        boolean isHeadless = Boolean.parseBoolean(ConfigManager.getProperty("headless", "false"));

        WebDriver localDriver;

        switch (browserType) {
            case CHROME:
                ChromeOptions chromeOptions = new ChromeOptions();
                if (isHeadless) {
                    chromeOptions.addArguments("--headless");
                    chromeOptions.addArguments("disable-gpu");
                }
                localDriver = new ChromeDriver(chromeOptions);
                break;
            case FIREFOX:
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                if (isHeadless) {
                    firefoxOptions.addArguments("--headless");
                    firefoxOptions.addArguments("disable-gpu");
                }
                localDriver = new FirefoxDriver(firefoxOptions);
                break;
            case EDGE:
                EdgeOptions edgeOptions = new EdgeOptions();
                if (isHeadless) {
                    edgeOptions.addArguments("--headless");
                    edgeOptions.addArguments("disable-gpu");
                }
                localDriver = new EdgeDriver(edgeOptions);
                break;
            default:
                throw new IllegalArgumentException("Browser not supported: " + browserType);
        }

        driver.set(localDriver);

        localDriver.manage().window().maximize();

        String url = ConfigManager.getProperty("QAUrl");
        System.out.println("Loading URL: " + url);
        driver.get().get(url);

        basePage = new BasePage(localDriver);
        basePage.waitActions.waitForPageToBeReady();
    }
    public void removeDriver() {
        driver.remove();
    }
}



