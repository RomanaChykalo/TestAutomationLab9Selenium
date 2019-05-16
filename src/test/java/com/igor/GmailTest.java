package com.igor;

import com.igor.business.LogInBO;
import com.igor.business.MessageBO;
import com.igor.provider.DriverProvider;
import com.igor.utils.property.Property;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Iterator;
import java.util.UUID;
import java.util.stream.Stream;

import static com.igor.utils.parser.JsonParser.*;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

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
        LogInBO logInBO = new LogInBO();
        logInBO.logIn(username, password);
        MessageBO messageBO = new MessageBO();
        assertTrue(messageBO.sendIncorrectMessage(MESSAGE_TITLE));
        messageBO.correctReceiverAndSend();
        assertEquals(messageBO.checkThatLetterIsSent(), MESSAGE_TITLE);
    }

    @AfterMethod
    public void quitDriver()
    {
        DriverProvider.quit();
    }
}
