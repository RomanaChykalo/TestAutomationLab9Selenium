package com.igor.decorator.element;
import org.openqa.selenium.WebElement;

public class Label extends AbstractElement {
    public Label(WebElement webElement) {
        super(webElement);
    }

    public void sendKeys(CharSequence... var1) {
        webElement.sendKeys(var1);
    }

    public void clear() {
        webElement.clear();
    }

    public String getText(){
        return webElement.getText();
    }
}
