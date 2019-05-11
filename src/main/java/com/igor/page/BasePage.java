package com.igor.page;


import com.igor.provider.DriverProvider;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {
    protected WebDriver driver;
    protected WebDriverWait driverWait;

    public BasePage(){
        driver = DriverProvider.getDriver();
        driverWait = DriverProvider.getDriverWait();
        PageFactory.initElements(driver, this);
    }
}
