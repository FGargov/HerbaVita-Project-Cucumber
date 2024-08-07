package stepDefinitions;

import io.cucumber.java.After;
import utils.TestContextSetup;

public class hooks {
    TestContextSetup textContextSetup;

    public hooks(TestContextSetup textContextSetup) {
        this.textContextSetup = textContextSetup;
    }

    @After
    public void tearDown() {
        textContextSetup.driver.quit();
    }
}
