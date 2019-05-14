package com.sofia.pageobjects.gmailpages;

import com.sofia.pageobjects.GeneralGmailPage;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class GmailSignInPageObj extends GeneralGmailPage {
    @FindBy(xpath = "//input[@id='identifierId']")
    private WebElement usernameInput;

    @FindBy(id = "profileIdentifier")
    private WebElement activeUsername;

    @FindBy(name = "password")
    private WebElement passwordInput;

    public void typeUernameAndSubmit(String usernameInputValue) {
        usernameInput.sendKeys(usernameInputValue);
        usernameInput.sendKeys(Keys.ENTER);
    }

    public void typePasswordAndSubmit(String passwordInputValue) {
        passwordInput.sendKeys(passwordInputValue);
        passwordInput.sendKeys(Keys.ENTER);
    }

    public String getActiveUsernameAttributeValue(){
        return activeUsername.getAttribute("innerHTML");
    }
}
