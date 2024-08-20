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

       if (driver == null) {
           if (prop.getProperty("browser").equalsIgnoreCase("chrome")) {
               ChromeOptions options = new ChromeOptions();

               // Проверка дали headless режим трябва да бъде активиран
               if (prop.getProperty("headless").equalsIgnoreCase("true")) {
                   options.addArguments("headless");
                   options.addArguments("window-size=1920,1080"); // Настройки за резолюция
               }

               driver = new ChromeDriver(options);
           }
           if (prop.getProperty("browser").equalsIgnoreCase("firefox")) {
               FirefoxOptions options = new FirefoxOptions();

               if (prop.getProperty("headless").equalsIgnoreCase("true")) {
                   options.addArguments("--headless");
               }
               driver = new FirefoxDriver(options);
           }
           driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
           driver.get(url);
       }

       return driver;
   }
}
