package utils;

import pageObjects.PageObjectManager;

import java.io.IOException;

public class TestContextSetup {
    private PageObjectManager pageObjectManager;
    private TestBase testBase;
    private GenericUtils genericUtils;

    public TestContextSetup() throws IOException {
        testBase = new TestBase();
        pageObjectManager = new PageObjectManager(testBase.getDriver());
        genericUtils = new GenericUtils(testBase.getDriver());
    }


    public PageObjectManager getPageObjectManager() {
        return pageObjectManager;
    }

    public TestBase getTestBase() {
        return testBase;
    }

    public GenericUtils getGenericUtils() {
        return genericUtils;
    }
}
