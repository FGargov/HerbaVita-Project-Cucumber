package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;


import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

public class TestBase  {
    public WebDriver driver;

   public WebDriver WebDriverManager() throws IOException {
       FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "//src//test//resources//global.properties");
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
                   options.addArguments("headless");
                   options.addArguments("window-size=1920,1080"); // Настройки за резолюция
                   options.addArguments("--disable-dev-shm-usage");
                   options.addArguments("--remote-debugging-port=9222");
                   options.addArguments("--disable-gpu");
                   options.setBinary("/usr/bin/google-chrome");
                   options.addArguments("--verbose");

               }

               driver = new ChromeDriver(options);
           } else if (browser != null && browser.equalsIgnoreCase("firefox")) {
               FirefoxOptions options = new FirefoxOptions();

               if (prop.getProperty("headless").equalsIgnoreCase("true")) {
                   options.addArguments("--headless");
               }
               driver = new FirefoxDriver(options);
           } else {
               throw new IllegalArgumentException("A valid browser is not set: " + browser);
           }
           driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
           driver.get(url);
       }

       return driver;
   }
}

