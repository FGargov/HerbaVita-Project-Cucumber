package CucumberOptions;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/java/features",
        glue = "stepDefinitions",
        monochrome=true, tags ="@PlaceOrder or @OffersPage",
        plugin={"pretty", "html:target/cucumber.html", "json:target/cucumber.json",
                "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"}
)
public class JUnitTestRunner {

}
