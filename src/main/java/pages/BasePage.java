package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class BasePage {
    protected WebDriver driver;

    BasePage(){
        driver = DriverFactory.getWebDriver();
        PageFactory.initElements(driver, this);
    }
}