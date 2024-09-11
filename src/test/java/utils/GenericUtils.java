package utils;

import org.openqa.selenium.WebDriver;

import java.util.Iterator;
import java.util.Set;

public class GenericUtils {
    private WebDriver driver;

    public GenericUtils(WebDriver driver) {
        this.driver = driver;
    }

    //Целта е да се премине (суичне) от текущия прозорец (parent window) към новоотворен прозорец или таб (child window) в браузъра.
    public void SwitchWindowsToChild() {
        Set<String> s1 = driver.getWindowHandles();
        Iterator<String> i1 = s1.iterator();
        String parentWindow = i1.next();
        String childWindow = i1.next();
        driver.switchTo().window(childWindow);
    }
}
