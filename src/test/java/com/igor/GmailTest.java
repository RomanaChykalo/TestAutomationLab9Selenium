package com.igor;

import com.igor.page.LogInPage;
import com.igor.page.MainPage;
import com.igor.page.NewMessagePage;
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

import static com.igor.parser.JsonParser.*;
import static org.testng.Assert.*;

public class GmailTest {
    @BeforeMethod()
    public void setStartedPage(){
        DriverProvider.getDriver().get(Property.getProperty("initial_page"));
    }

    @DataProvider(parallel = true)
    public Iterator<Object[]> users(){
        Object[][] objects = new Object[5][];
        for (int i = 0; i < 4; i++) {
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
        NewMessagePage newMessagePage = mainPage.clickToComposeButton();
        newMessagePage.setReceiverField(getIncorrectReceiver());
        newMessagePage.setTitleField(MESSAGE_TITLE);
        newMessagePage.setMessageField(getMessage());
        newMessagePage.clickToSendButton();
        assertTrue(newMessagePage.alertDialogIsEnable());
        newMessagePage.clickToButtonOkInAlertDialog();
        newMessagePage.clickToDeleteContact();
        newMessagePage.setReceiverField(getReceiver());
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
