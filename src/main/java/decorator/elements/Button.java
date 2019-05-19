package decorator.elements;

import org.openqa.selenium.WebElement;

public class Button extends BaseElement {

    public Button(WebElement element) {
        super(element);
    }

    public boolean isClickable(){
        return element.isEnabled();
    }

    public void click(){
        if(isClickable()) {
            element.click();
        }
    }

    public String getText(){
       return element.getText();
    }

    public Boolean isDisplayed(){
        return element.isDisplayed();
    }
}
