package com.igor;

import com.igor.page.LogInPage;
import com.igor.page.MainPage;
import com.igor.page.NewMessageWidget;
import com.igor.page.SentPage;

import com.igor.property.Property;
import com.igor.provider.DriverProvider;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;


import java.util.Iterator;
import java.util.UUID;
import java.util.stream.Stream;

import static com.igor.constant.Constants.NUMBER_OF_USERS;
import static com.igor.parser.JsonParser.*;
import static org.testng.Assert.*;

public class GmailTest {
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
        logInPage.setUsername(username);
        MainPage mainPage = logInPage.setPassword(password);
        NewMessageWidget newMessageWidget = mainPage.clickToComposeButton();
        newMessageWidget.setReceiverField(getIncorrectReceiver());
        newMessageWidget.setTitleField(MESSAGE_TITLE);
        newMessageWidget.setMessageField(getMessage());
        newMessageWidget.clickToSendButton();
        assertTrue(newMessageWidget.alertDialogIsEnable());
        newMessageWidget.clickToButtonOkInAlertDialog();
        newMessageWidget.clickToDeleteContact();
        newMessageWidget.setReceiverField(getReceiver());
        newMessageWidget.clickToSendButton();
        SentPage sentPage = mainPage.goToSentPage();
        assertEquals(sentPage.getLetter(), MESSAGE_TITLE);
    }

    @AfterMethod
    public void quitDriver()
    {
        DriverProvider.quit();
    }
}
