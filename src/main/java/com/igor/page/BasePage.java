package com.igor.page;


import com.igor.provider.DriverProvider;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public abstract class BasePage {
    protected WebDriver driver;

    protected BasePage(){
        driver = DriverProvider.getDriver();
        PageFactory.initElements(driver, this);
    }
}
