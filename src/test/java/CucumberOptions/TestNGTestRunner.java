package CucumberOptions;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features="src/test/java/features",
        glue="stepDefinitions",monochrome=true,
        dryRun = true)
public class TestNGTestRunner extends AbstractTestNGCucumberTests {

}
