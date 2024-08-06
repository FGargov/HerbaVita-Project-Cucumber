package CucumberOptions;

import io.cucumber.junit.CucumberOptions;
import io.cucumber.testng.AbstractTestNGCucumberTests;

@CucumberOptions(features = "src/test/java/features",
                glue="stepDefinitions", monochrome = true, dryRun = true)
public class TestNGTestRunner extends AbstractTestNGCucumberTests {
}
