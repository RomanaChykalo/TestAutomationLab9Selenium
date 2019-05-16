package com.igor;

import com.igor.business.LogInBO;
import com.igor.business.MessageBO;
import com.igor.provider.DriverProvider;
import com.igor.utils.property.Property;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Iterator;
import java.util.stream.Stream;

import static com.igor.utils.parser.JsonParser.*;
import static org.testng.Assert.assertTrue;

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
        LogInBO logInBO = new LogInBO();
        MessageBO messageBO = new MessageBO();
        logInBO.logIn(username, password);
        messageBO.sendIncorrectMessage();
        assertTrue(messageBO.checkThatAlertWidgetAppeared());
        messageBO.correctReceiverAndSend();
        assertTrue(messageBO.checkThatLetterIsSent());
    }

    @AfterMethod
    public void quitDriver()
    {
        DriverProvider.quit();
    }
}
