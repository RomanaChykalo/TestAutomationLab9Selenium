package com.igor;

import com.igor.page.LogInPage;
import com.igor.page.MainPage;
import com.igor.page.NewMessagePage;
import com.igor.page.SentPage;

import com.igor.property.Property;
import com.igor.provider.DriverProvider;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


import static com.igor.parser.JsonParser.*;
import static org.testng.Assert.*;

public class GmailTest {
    private static final String MESSAGE_TITLE = getTitle();

    @BeforeMethod
    public void setStartedPage(){
        DriverProvider.getDriver().get(Property.getProperty("initial_page"));
    }

    @Test
    public void sendEmailTest() {
        LogInPage logInPage = new LogInPage();
        logInPage.setUsername(getUserName());
        MainPage mainPage = logInPage.setPassword(getPassword());
        NewMessagePage newMessagePage = mainPage.clickToComposeButton();
        newMessagePage.setReceiverField(getIncorrectReceiver());
        newMessagePage.setTitleField(MESSAGE_TITLE);
        newMessagePage.setMessageField(getMessage());
        newMessagePage.clickToSendButton();
        assertTrue(newMessagePage.alertDialogIsEnable());
        newMessagePage.clickToButtonOkInAlertDialog();
        newMessagePage.clickToDeleteContact();
        newMessagePage.setReceiverField(getReceiver(0));
        newMessagePage.clickToSendButton();
        SentPage sentPage = mainPage.goToSentPage();
        assertTrue(sentPage.getLetter(0).getText().contains(MESSAGE_TITLE));
    }

    @AfterMethod
    public void quitDriver()
    {
        DriverProvider.quit();
    }
}
