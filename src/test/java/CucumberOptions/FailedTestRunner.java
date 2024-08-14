
package CucumberOptions;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "@target/failed_scenarios.txt",
        glue = "stepDefinitions",
        monochrome=true, tags ="@PlaceOrder or @OffersPage",
        plugin={"pretty", "html:target/cucumber.html", "json:target/cucumber.json",
                "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:",
                "rerun:target/failed_scenarios.txt"}
)
public class FailedTestRunner {

}




//package CucumberOptions;
//
//import io.cucumber.testng.AbstractTestNGCucumberTests;
//import io.cucumber.testng.CucumberOptions;
//import org.testng.annotations.DataProvider;
//
//@CucumberOptions(features = "@target/failed_scenarios.txt",
//        glue="stepDefinitions", monochrome=true,
//        dryRun = true,
//        plugin = {"pretty", "html:target/cucumber.html", "json:target/cucumber.json",
//                "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:",
//                "rerun:target/failed.txt"})
//public class FailedTestRunner extends AbstractTestNGCucumberTests {
//    @Override
//    @DataProvider(parallel=true)
//    public Object[][] scenarios() {
//        return super.scenarios();
//    }
//}





