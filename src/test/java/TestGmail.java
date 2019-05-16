import businessobjects.SendFromDraft;
import businessobjects.SignIn;
import businessobjects.WriteNewMessage;
import driver.DriverManager;
import json.Data;
import json.Parser;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.*;
import properties.FilePath;

import java.io.File;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;

public class TestGmail {
    private Logger logger = LogManager.getLogger(TestGmail.class);

    @DataProvider(parallel = true)
    public Iterator<Object[]> getUsers() {
        FilePath filePath = new FilePath();
        File json = new File(filePath.propertyFile("fileWayJSON"));
        Parser parser = new Parser();
        List<Data> users = parser.getData(json);
        return Stream.of(
                new Object[]{users.get(0).getLogin(), users.get(0).getPassword(), users.get(0).getMessage(),
                        users.get(0).getSubject(), users.get(0).getReceiver()},
                new Object[]{users.get(1).getLogin(), users.get(1).getPassword(), users.get(1).getMessage(),
                        users.get(1).getSubject(),  users.get(1).getReceiver()},
                new Object[]{users.get(2).getLogin(), users.get(2).getPassword(), users.get(2).getMessage(),
                       users.get(2).getSubject(), users.get(2).getReceiver()},
                new Object[]{users.get(3).getLogin(), users.get(3).getPassword(), users.get(3).getMessage(),
                        users.get(3).getSubject(), users.get(3).getReceiver(),},
                new Object[]{users.get(4).getLogin(), users.get(4).getPassword(), users.get(4).getMessage(),
                       users.get(4).getSubject(), users.get(4).getReceiver()}).iterator();
    }

    @Test(dataProvider = "getUsers")
    public void LoginTest(String login, String password,String message, String subject, String receiver) {
        SignIn signIn = new SignIn();
        signIn.login(login,password);
        logger.info("You have successfully logged in.");

        WriteNewMessage writeMessage = new WriteNewMessage();
        writeMessage.tryToWriteMessage(receiver,subject,message);
        logger.info("Message was wrote but was not sent.");

        SendFromDraft sendFromDraft = new SendFromDraft();
        sendFromDraft.sendMessage();
        logger.info("Message was sent.");
    }

    @AfterMethod
    public void tearDown() {
        DriverManager.tearDown();
    }
}
