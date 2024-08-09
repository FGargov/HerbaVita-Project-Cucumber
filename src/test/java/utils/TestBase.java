package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;


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
               driver = new ChromeDriver();
           }
           if (prop.getProperty("browser").equalsIgnoreCase("firefox")) {
               //firefox code
           }
           driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
           driver.get(url);
       }

       return driver;
   }
}
