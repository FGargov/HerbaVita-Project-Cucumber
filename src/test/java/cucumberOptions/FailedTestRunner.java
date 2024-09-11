package cucumberOptions;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "@target/failed-scenarios.txt",
        glue = "stepDefinitions",
        monochrome = true,
        plugin = {
                "pretty",
                "html:target/cucumber.html",
                "json:target/cucumber.json",
                "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:",
                "rerun:target/failed-scenarios-rerun.txt"
        })
public class FailedTestRunner {
}
