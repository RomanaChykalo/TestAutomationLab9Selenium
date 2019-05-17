package com.igor;

import com.igor.business.LogInBO;
import com.igor.business.MessageBO;
import com.igor.listener.FailureLister;
import com.igor.provider.DriverProvider;
import com.igor.utils.property.Property;
import org.testng.annotations.*;

import java.util.Iterator;
import java.util.stream.Stream;

import static com.igor.utils.parser.JsonParser.getUsers;
import static org.testng.Assert.assertTrue;

@Listeners(FailureLister.class)
public class GmailTest {
    @BeforeMethod()
    public void setStartedPage(){
        DriverProvider.getDriver().get(Property.getProperty("initial_page"));
    }

    @DataProvider(parallel = true)
    public Iterator<Object[]> users(){
        return Stream.of(getUsers()).iterator();
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
