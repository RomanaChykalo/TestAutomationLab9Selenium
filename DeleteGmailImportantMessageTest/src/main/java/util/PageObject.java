package util;

import decorator.CustomFieldDecorator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PageObject {
    protected WebDriverWait wait;

    public PageObject() {
        wait = new WebDriverWait(DriverManager.getWebDriver(), 20);
        PageFactory.initElements(new CustomFieldDecorator(DriverManager.getWebDriver()), this);
    }
}


