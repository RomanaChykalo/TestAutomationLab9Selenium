package web_elements;

import decorator.Element;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

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


}
