package com.igor;

import com.igor.page.LogInPage;
import com.igor.page.MainPage;
import com.igor.page.NewMessageWidget;
import com.igor.page.SentPage;

import com.igor.property.Property;
import com.igor.provider.DriverProvider;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;


import java.util.Iterator;
import java.util.UUID;
import java.util.stream.Stream;

import static com.igor.parser.JsonParser.*;
import static org.testng.Assert.*;

public class GmailTest {
    private static final Logger LOGGER = LogManager.getLogger(GmailTest.class);
    @BeforeMethod()
    public void setStartedPage(){
        DriverProvider.getDriver().get(Property.getProperty("initial_page"));
    }

    @DataProvider(parallel = true)
    public Iterator<Object[]> users(){
        int numberOfUsers = getNumberOfUsers();
        Object[][] objects = new Object[numberOfUsers][];
        for (int i = 0; i < numberOfUsers; i++) {
            objects[i] = new Object[]{getUserName(i), getPassword(i)};
        }
        return Stream.of(objects).iterator();
    }

    @Test(dataProvider = "users")
    public void sendEmailTest(String username, String password) {
        final String MESSAGE_TITLE = UUID.randomUUID().toString();
        LogInPage logInPage = new LogInPage();
        LOGGER.info("Logging in");
        logInPage.setUsername(username);
        MainPage mainPage = logInPage.setPassword(password);
        LOGGER.info("Opening new message widget");
        NewMessageWidget newMessageWidget = mainPage.clickToComposeButton();
        LOGGER.info("filling new letter");
        newMessageWidget.setReceiverField(getIncorrectReceiver());
        newMessageWidget.setTitleField(MESSAGE_TITLE);
        newMessageWidget.setMessageField(getMessage());
        newMessageWidget.clickToSendButton();
        LOGGER.info("Opening alert dialog");
        assertTrue(newMessageWidget.alertDialogIsEnable());
        LOGGER.info("closing alert dialog");
        newMessageWidget.clickToButtonOkInAlertDialog();
        LOGGER.info("deleting incorrect receiver");
        newMessageWidget.clickToDeleteContact();
        LOGGER.info("writing correct receiver");
        newMessageWidget.setReceiverField(getReceiver());
        LOGGER.info("sending letter");
        newMessageWidget.clickToSendButton();
        LOGGER.info("opening sent page");
        SentPage sentPage = mainPage.goToSentPage();
        LOGGER.info("checking sent page");
        assertEquals(sentPage.getLetter(), MESSAGE_TITLE);
    }

    @AfterMethod
    public void quitDriver()
    {
        DriverProvider.quit();
    }
}
