package elements;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

public class Search extends Element {
    public Search(WebElement webElement) {
        super(webElement);
    }
        public void typeTextInSearch(String folderName){
         webElement.sendKeys(folderName);
        }

        public void pressEnter(){
        webElement.sendKeys(Keys.ENTER);
        }
    }

