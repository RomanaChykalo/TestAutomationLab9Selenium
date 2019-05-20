package elements;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Button extends Element {
    public Button(WebElement webElement) {
        super(webElement);
    }

    public void click() {
        webElement.click();
    }

    public boolean isClickable(WebDriver driver) {
        try {
            (new WebDriverWait(driver, 5)).until(ExpectedConditions.elementToBeClickable(webElement));
        } catch (Exception ex) {
            return false;
        }
        return true;
    }
}
