package stepDefinitions;

import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import utils.TestContextSetup;

import java.io.File;
import java.io.IOException;

public class Hooks {
    private TestContextSetup testContextSetup;

    public Hooks(TestContextSetup textContextSetup) {
        this.testContextSetup = textContextSetup;
    }

    @After
    public void tearDown() throws IOException {
        if (testContextSetup.getTestBase().getDriver() != null) {
            testContextSetup.getTestBase().getDriver().quit();
            testContextSetup.getTestBase().removeDriver();
        }
    }

    @AfterStep
    public void AddScreenshot(Scenario scenario) throws IOException {
        WebDriver driver = testContextSetup.getTestBase().getDriver();
        if (scenario.isFailed()) {
            File sourcePath = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
            byte[] fileContent = FileUtils.readFileToByteArray(sourcePath);
            scenario.attach(fileContent, "image/png", "Image:");
        }
    }
}
