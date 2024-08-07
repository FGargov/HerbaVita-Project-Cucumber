package stepDefinitions;

import io.cucumber.java.After;
import utils.TextContextSetup;

public class hooks {
    TextContextSetup textContextSetup;

    public hooks(TextContextSetup textContextSetup) {
        this.textContextSetup = textContextSetup;
    }

    @After
    public void tearDown() {
        textContextSetup.driver.quit();
    }
}
