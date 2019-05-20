package web_elements;

import decorator.Element;
import org.openqa.selenium.WebElement;

public class Allert extends Element {

    public Allert(WebElement webElement) {
        super(webElement);
    }

    public boolean isVisible() {
        return webElement.isDisplayed();
    }

}
