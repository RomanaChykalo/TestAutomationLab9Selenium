package com.kryviak.mailboxBO;

import com.kryviak.models.MessageModel;
import com.kryviak.pages.mailbox.MailboxPage;
import org.openqa.selenium.WebElement;

import java.util.List;

public class MailboxBO {
    public void sendLetter(MessageModel messageModel, String messageTitle) {
        MailboxPage mailboxPage = new MailboxPage();
        mailboxPage.clickCreateNewLetterButton();
        mailboxPage.setEmailToTextField(messageModel.getEmailTo());
        mailboxPage.setSubjectToTextField(messageModel.getSubjectTo());
        mailboxPage.setMessageToTextField(messageModel.getMessageTo());
        mailboxPage.clickSenDMessageButton();
        mailboxPage.clickSenTMessageLink();
        mailboxPage.selectMessageByTitle(messageTitle);
        mailboxPage.deleteSelectedMessage();
    }

    public boolean isMessageSent(String msgTitle) {
        List<WebElement> mailTitlesList = new MailboxPage().getMessageTitles();
        boolean isSent = false;
        for (WebElement element : mailTitlesList) {
            if (element.getText().equals(msgTitle)) {
                isSent = true;
                break;
            }
        }
        return isSent;
    }
}
