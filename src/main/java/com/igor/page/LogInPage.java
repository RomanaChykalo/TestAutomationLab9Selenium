package com.igor.page;

import com.igor.provider.DriverProvider;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static com.igor.utils.constant.Constants.EXPLICIT_WAIT;

public class LogInPage extends BasePage{
    @FindBy(id = "identifierId")
    private WebElement usernameField;
    @FindBy(name = "password")
    private WebElement passwordField;

    public void setUsernameAndSubmit(String username){
        usernameField.sendKeys(username);
        usernameField.sendKeys(Keys.ENTER);
    }

    public void setPasswordAndSubmit(String password){
        (new WebDriverWait(driver, EXPLICIT_WAIT)).until(ExpectedConditions.visibilityOf(passwordField));
        passwordField.sendKeys(password);
        passwordField.sendKeys(Keys.ENTER);
        DriverProvider.pageLoadTimeout(EXPLICIT_WAIT);
    }
}
