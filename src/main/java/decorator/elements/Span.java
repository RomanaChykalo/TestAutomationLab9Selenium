package decorator.elements;

import org.openqa.selenium.WebElement;

public class Span extends BaseElement {

    public Span(WebElement element) {
        super(element);
    }

    public void click(){
        if(isEnabled()) {
            element.click();
        }
    }

    public Boolean isEnabled(){
       return element.isEnabled();
    }
    public String getContent(){
        return element.getText();
    }
}
