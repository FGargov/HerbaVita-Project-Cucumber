package utils;

import org.junit.Test;
import org.openqa.selenium.WebDriver;
import pageObjects.PageObjectManager;

import java.io.IOException;

public class TestContextSetup {
    public String landingPageProductName;
    public String offersPageProductName;
    public PageObjectManager pageObjectManager;
    public TestBase testBase;
    public GenericUtils genericUtils;

    public TestContextSetup() throws IOException {
        testBase = new TestBase();
        pageObjectManager = new PageObjectManager(testBase.WebDriverManager());
        genericUtils = new GenericUtils(testBase.WebDriverManager());
    }
}
