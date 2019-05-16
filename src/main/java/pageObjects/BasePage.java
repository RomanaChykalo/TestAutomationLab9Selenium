package pageObjects;

import driver.DriverLoader;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public abstract class BasePage {
    protected WebDriver driver;
    public BasePage(){
       driver = DriverLoader.getDriver();
        PageFactory.initElements(driver,this);
    }
}
