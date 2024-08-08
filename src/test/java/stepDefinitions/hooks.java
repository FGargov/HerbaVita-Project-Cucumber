package stepDefinitions;

import io.cucumber.java.After;
import utils.TestContextSetup;

import java.io.IOException;

public class hooks {
    TestContextSetup textContextSetup;

    public hooks(TestContextSetup textContextSetup) {
        this.textContextSetup = textContextSetup;
    }

    @After
    public void tearDown() throws IOException {
        textContextSetup.testBase.WebDriverManager().quit();
    }
}
