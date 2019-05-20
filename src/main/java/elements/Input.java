package elements;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

public class Input extends Element {
    public Input(WebElement webElement) {
        super(webElement);
    }
    public void sentKeys(String data){
        webElement.clear();
        webElement.sendKeys(data);
    }
    public void typeAndSubmit(String value) {
        webElement.sendKeys(value + Keys.ENTER);
    }
}
