package pages;

import driver.DriverFactory;
import decorator.CustomFieldDecorator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {
    protected WebDriver driver;
    protected WebDriverWait wait;
    BasePage(){
        driver = DriverFactory.getWebDriver();
        PageFactory.initElements(new CustomFieldDecorator(driver), this);
        wait = new WebDriverWait(driver, 30);
    }
}