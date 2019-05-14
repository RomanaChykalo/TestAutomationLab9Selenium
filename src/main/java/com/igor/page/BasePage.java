package com.igor.page;


import com.igor.provider.DriverProvider;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

abstract class BasePage {
    protected WebDriver driver;

    BasePage(){
        driver = DriverProvider.getDriver();
        PageFactory.initElements(driver, this);
    }
}
