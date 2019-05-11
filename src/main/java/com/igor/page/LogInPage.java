package com.igor.page;

import com.igor.provider.DriverProvider;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LogInPage extends BasePage{
    @FindBy(id = "identifierId")
    private WebElement usernameField;
    @FindBy(name = "password")
    private WebElement passwordField;

    public void setUsername(String username){
        usernameField.sendKeys(username);
        usernameField.sendKeys(Keys.ENTER);
    }

    public MainPage setPassword(String password){
        passwordField.sendKeys(password);
        passwordField.sendKeys(Keys.ENTER);
        DriverProvider.pageLoadTimeout(10);
        return new MainPage();
    }
}
