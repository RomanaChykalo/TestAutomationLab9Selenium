package com.kryviak;

import com.kryviak.config.DriverThreadInit;
import com.kryviak.loginBO.LoginBO;
import com.kryviak.mailboxBO.MailboxBO;
import com.kryviak.models.LoginModel;
import com.kryviak.models.MessageModel;
import com.kryviak.utils.XmlParser;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Iterator;
import java.util.stream.Stream;


public class GmailTest {

    @DataProvider(parallel = true)
    public Iterator<Object[]> users() {
        return Stream.of(
                new Object[]{"testmy12341234", "1234test1234My"},
                new Object[]{"tt8517837", "856ttestmy856"},
                new Object[]{"test28832", "12345test54321"},
                new Object[]{"test28361", "12345test12345"},
                new Object[]{"test283611", "1234512345t"}).iterator();
    }

    @Test(dataProvider = "users")
    public void sendEmail(String login, String password) {
        XmlParser parser = new XmlParser();
        MailboxBO mailboxBO = new MailboxBO();
        MessageModel messageModel = parser.getMessageModelData();
        LoginModel loginModel = new LoginModel();
        loginModel.setUserLogin(login);
        loginModel.setUserPassword(password);
        new LoginBO().login(loginModel);
        mailboxBO.sendLetter(messageModel, messageModel.getSubjectTo());
        Assert.assertTrue(mailboxBO.isMessageSent(messageModel.getSubjectTo()), "Message with subject [" +
                messageModel.getSubjectTo() + "] was not sent.");
    }

    @AfterMethod
    private void closeBrowser() {
        DriverThreadInit.getInstance().removeDriver();
    }
}
