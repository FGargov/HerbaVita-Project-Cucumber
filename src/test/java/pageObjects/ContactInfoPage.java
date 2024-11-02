package pageObjects;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ContactInfoPage extends BasePage {
    private  String[] lines;

    private By addressLocator = By.xpath("//div[contains(@class, 'textwidget')]//i[contains(@class, 'fa-location-arrow')]/parent::div");
    private By phoneLocator = By.xpath("//div[contains(@class, 'textwidget')]//i[contains(@class, 'fa-mobile')]/following-sibling::text()");
    private By emailLocator = By.xpath("//div[contains(@class, 'textwidget')]//i[contains(@class, 'fa-envelope-o')]/following-sibling::text()");

    public ContactInfoPage(WebDriver driver) {
        super(driver);
    }

    public String getAddress() {
        return driver.findElement(addressLocator).getText();
    }

    public String getPhoneNumber() {
        return driver.findElement(phoneLocator).getText();
    }

    public String getEmail() {
        return driver.findElement(emailLocator).getText();
    }

    public void verifyAddress(String expectedAddress) {
        lines = getAddress().split("\\n");

        String actualAddress = lines[0].trim();
        Assert.assertEquals("Address is incorrect", expectedAddress, actualAddress);

    }

    public void verifyPhoneNumber(String expectedPhone) {
        String actualPhone = lines[1].replace("Телефон: ", "").trim();
        Assert.assertEquals("The phone number is incorrect!", expectedPhone, actualPhone);
    }

    public void verifyEmail(String expectedEmail) {
        String actualEmail = lines[2].replace("E-mail: ", "").trim();
        Assert.assertEquals("The email is incorrect", expectedEmail, actualEmail);
    }
}
