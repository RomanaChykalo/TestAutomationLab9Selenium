package com.sofia.pageobjects;

import org.openqa.selenium.support.PageFactory;

import static com.sofia.utilmanager.driver.DriverManager.getDriverInstance;

public abstract class GeneralGmailPage {
    public GeneralGmailPage() {
        PageFactory.initElements(getDriverInstance(), this);
    }
}