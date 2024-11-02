package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.io.*;
import java.time.Duration;
import java.util.Properties;

public class TestBase  {
    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    public WebDriver getDriver() throws IOException {
        if (driver.get() == null) {
            initializeDriver();
        }
        return driver.get();
    }

    private void initializeDriver() throws IOException{
        String browser = System.getProperty("browser", ConfigManager.getProperty("browser"));
        boolean isHeadless = Boolean.parseBoolean(ConfigManager.getProperty("headless", "false"));

        switch (browser.toLowerCase()) {
            case "chrome":
                ChromeOptions chromeOptions = new ChromeOptions();
                if (isHeadless) {
                    chromeOptions.addArguments("--headless");
                    chromeOptions.addArguments("disable-gpu");
                }
                driver.set(new ChromeDriver(chromeOptions));
                break;
            case "firefox":
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                if (isHeadless) {
                    firefoxOptions.addArguments("--headless");
                    firefoxOptions.addArguments("disable-gpu");
                }
                driver.set(new FirefoxDriver(firefoxOptions));
                break;
            case "edge":
                EdgeOptions edgeOptions = new EdgeOptions();
                if (isHeadless) {
                    edgeOptions.addArguments("--headless");
                    edgeOptions.addArguments("disable-gpu");
                }
                driver.set(new EdgeDriver(edgeOptions));
                break;
            default:
                throw new IllegalArgumentException("Browser not supported: " + browser);
        }

        driver.get().manage().window().maximize();
        driver.get().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        String url = ConfigManager.getProperty("QAUrl");
        System.out.println("Loading URL: " + url);
        driver.get().get(url);
    }
    public void removeDriver() {
        driver.remove();
    }
}



