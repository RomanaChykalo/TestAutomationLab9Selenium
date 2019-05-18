package com.igor.page;

import com.igor.decorator.element.Label;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LogInPage extends BasePage{
    @FindBy(id = "identifierId")
    private Label usernameField;
    @FindBy(name = "password")
    private Label passwordField;

    public void setUsernameAndSubmit(String username){
        usernameField.sendKeys(username);
        usernameField.sendKeys(Keys.ENTER);
    }

    public void setPasswordAndSubmit(String password){
        webDriverWait.until(ExpectedConditions.visibilityOf(passwordField.getWebElement()));
        passwordField.sendKeys(password);
        passwordField.sendKeys(Keys.ENTER);
    }
}
