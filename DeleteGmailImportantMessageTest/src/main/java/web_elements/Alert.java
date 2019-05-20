package web_elements;

import decorator.Element;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import util.DriverManager;

public class Alert extends Element {

    public final static int TIME_OUT = 10;

    public Alert(WebElement webElement)
    {
        super(webElement);
    }

    public boolean isVisible() {
        boolean visible = true;
        try {
            (new WebDriverWait(DriverManager.getWebDriver(), TIME_OUT))
                    .until(ExpectedConditions.visibilityOf(webElement));
        }
        catch (TimeoutException ex) {
            visible = false;
        }
        return visible;
    }

}
