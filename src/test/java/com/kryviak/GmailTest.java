package com.kryviak;

import com.kryviak.config.DriverManager;
import com.kryviak.loginBO.LoginBO;
import com.kryviak.mailboxBO.MailboxBO;
import com.kryviak.models.MessageModel;
import com.kryviak.utils.XmlParser;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

public class GmailTest {

    @Test
    public void sendEmail() {
        XmlParser parser = new XmlParser();
        MailboxBO mailboxBO = new MailboxBO();
        MessageModel messageModel = parser.getMessageModelData();
        new LoginBO().login(parser.getLoginModelData());;
        mailboxBO.sendLetter(messageModel, messageModel.getSubjectTo());
        Assert.assertTrue(mailboxBO.isMessageSent(messageModel.getSubjectTo()), "Message with subject [" +
                messageModel.getSubjectTo() + "] was not sent.");
    }

    @AfterTest
    private void closeBrowser() {
        DriverManager.getInstance().closeWebDriver();
    }
}
