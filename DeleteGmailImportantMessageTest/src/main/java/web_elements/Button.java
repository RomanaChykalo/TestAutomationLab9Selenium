package web_elements;

import decorator.Element;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import util.DriverManager;

import static web_elements.Alert.TIME_OUT;

public class Button extends Element {

    public Button(WebElement webElement) {
        super(webElement);
    }

    public void clickButton() {
        webElement.click();
    }

    public void clickWithJS(WebDriver driver) {
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", webElement);
    }

    public boolean isClicable() {
        boolean clicable = true;
        try {
            (new WebDriverWait(DriverManager.getWebDriver(), TIME_OUT))
                    .until(ExpectedConditions.elementToBeClickable(webElement));
        } catch (TimeoutException ex) {
            clicable = false;
        }
        return clicable;
    }
}
