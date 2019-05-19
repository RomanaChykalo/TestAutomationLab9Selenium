package elements;
import org.openqa.selenium.WebElement;

public final class Button extends Element{
  public Button(WebElement webElement) {
        super(webElement);
    }
    @Override
    public void click() {
      webElement.click();
         }
}
