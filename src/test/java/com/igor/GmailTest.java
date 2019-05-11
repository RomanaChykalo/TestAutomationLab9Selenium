package com.igor;

import com.igor.model.Spoiler;
import com.igor.page.LogInPage;
import com.igor.page.MainPage;
import com.igor.page.NewMessagePage;
import com.igor.page.SentPage;
import com.igor.provider.DriverProvider;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

import static org.testng.Assert.*;

public class GmailTest {
    private static final Logger LOGGER = LogManager.getLogger(GmailTest.class);
    private static final String INITIAL_URL = "https://mail.google.com/";
    private static final String EMAIL_NAME = "groot.epam@gmail.com";
    private static final String EMAIL_PASSWORD = "iamgroot";
    private static final String RECEIVER_EMAIL = "paprika0015@gmail.com";
    private static final String INCORRECT_RECEIVER_EMAIL = "Tralalala";
    private static final String MESSAGE = "I am Groot! " + Spoiler.getSpoiler();
    private static final String MESSAGE_TITLE = "Final battle";

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
