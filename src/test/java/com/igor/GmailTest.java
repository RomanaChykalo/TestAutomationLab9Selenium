package com.igor;

import com.igor.page.LogInPage;
import com.igor.page.MainPage;
import com.igor.page.NewMessagePage;
import com.igor.page.SentPage;
import static com.igor.parser.JsonParser.*;
import com.igor.provider.DriverProvider;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;


import static org.testng.Assert.*;

public class GmailTest {
    private static final String EMAIL_NAME = getUserName();
    private static final String EMAIL_PASSWORD = getPassword();
    private static final String INCORRECT_RECEIVER_EMAIL = getReceiver(0);
    private static final String RECEIVER_EMAIL = getReceiver(1);
    private static final String MESSAGE = getMessage();
    private static final String MESSAGE_TITLE = getTitle();

    @AfterClass
    public void quitDriver() {
        DriverProvider.getDriver().quit();
    }

    @Test
    public void sendEmailTest() {
        LogInPage logInPage = new LogInPage();
        logInPage.setUsername(EMAIL_NAME);
        MainPage mainPage = logInPage.setPassword(EMAIL_PASSWORD);
        NewMessagePage newMessagePage = mainPage.clickToComposeButton();
        newMessagePage.setReceiverField(INCORRECT_RECEIVER_EMAIL);
        newMessagePage.setTitleField(MESSAGE_TITLE);
        newMessagePage.setMessageField(MESSAGE);
        newMessagePage.clickToSendButton();
        assertTrue(newMessagePage.alertDialogIsEnable());
        newMessagePage.clickToButtonOkInAlertDialog();
        newMessagePage.clickToDeleteContact();
        newMessagePage.setReceiverField(RECEIVER_EMAIL);
        newMessagePage.clickToSendButton();
        SentPage sentPage = mainPage.goToSentPage();
        assertTrue(sentPage.getLetter(0).getText().contains(MESSAGE_TITLE));
    }
}
