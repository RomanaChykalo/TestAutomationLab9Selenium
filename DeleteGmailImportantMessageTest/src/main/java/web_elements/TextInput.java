package web_elements;

import decorator.Element;
import org.openqa.selenium.WebElement;

public class TextInput extends Element {

    public TextInput(WebElement webElement) {
        super(webElement);
    }

    public void typeTextAndSubmit(CharSequence... charSequences) {
        if (charSequences != null) {
            webElement.clear();
            webElement.sendKeys(charSequences);
            webElement.submit();
        } else {
            webElement.sendKeys(charSequences);
            webElement.submit();
        }
    }

    public void typeText(CharSequence... charSequences) {
        if (charSequences != null) {
            webElement.clear();
            webElement.sendKeys(charSequences);
        } else {
            webElement.sendKeys(charSequences);
        }
    }

    public void clearText(CharSequence... charSequences) {
        if (charSequences != null)
            webElement.clear();
    }

}

