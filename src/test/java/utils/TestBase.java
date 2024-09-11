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
    public WebDriver driver;

    public WebDriver WebDriverManager()  throws IOException {
        //FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "//src//test//resources//global.properties");
        //FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + File.separator + "src" + File.separator + "test" + File.separator + "resources" + File.separator + "global.properties");
        InputStream fis = getClass().getClassLoader().getResourceAsStream("global.properties");

        if (fis == null) {
            throw new FileNotFoundException("Property file not found in the classpath");
        }
        Properties prop = new Properties();
        prop.load(fis);
        String url = prop.getProperty("QAUrl");
        String browserProperties = prop.getProperty("browser");
        String mavenBrowser = System.getProperty("browser");

        String browser = mavenBrowser != null ? mavenBrowser : browserProperties;



        if (driver == null) {
            if (browser != null && browser.equalsIgnoreCase("chrome")) {
                ChromeOptions options = new ChromeOptions();

                if (prop.getProperty("headless").equalsIgnoreCase("true")) {
                    options.addArguments("--headless");
                    options.addArguments("disable-gpu");
                }

                driver = new ChromeDriver(options);
            } else if (browser != null && browser.equalsIgnoreCase("firefox")) {
                FirefoxOptions options = new FirefoxOptions();

                if (prop.getProperty("headless").equalsIgnoreCase("true")) {
                    options.addArguments("--headless");
                    options.addArguments("disable-gpu");
                }
                driver = new FirefoxDriver(options);
            } else if (browser != null && browser.equalsIgnoreCase("edge")) {
                EdgeOptions options = new EdgeOptions();

                if (prop.getProperty("headless").equalsIgnoreCase("true")) {
                    options.addArguments("--headless");
                    options.addArguments("disable-gpu");
                }
                driver = new EdgeDriver(options);
            } else {
                throw new IllegalArgumentException("A valid browser is not set: " + browser);
            }
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
            driver.get(url);
        }

        return driver;
    }
}



