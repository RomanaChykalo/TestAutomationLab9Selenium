package pom;

import decorator.CustomFieldDecorator;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import util.DriverManager;

public class PageObject {
    protected WebDriverWait wait;

    public PageObject() {
        wait = new WebDriverWait(DriverManager.getWebDriver(), 20);
        PageFactory.initElements(new CustomFieldDecorator(DriverManager.getWebDriver()), this);
    }
}


