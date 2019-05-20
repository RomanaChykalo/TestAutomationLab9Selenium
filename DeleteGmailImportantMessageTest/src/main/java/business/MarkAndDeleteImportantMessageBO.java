package business;

import org.apache.log4j.Logger;
import org.testng.Assert;
import pom.GmailHomePage;
import pom.GmailImportantPage;

public class MarkAndDeleteImportantMessageBO {

    private GmailHomePage gmailHomePage = new GmailHomePage();
    private GmailImportantPage gmailImportantPage = new GmailImportantPage();
    private static Logger LOG = Logger.getLogger(MarkAndDeleteImportantMessageBO.class.getName());

    public void choseImportantMessages() throws InterruptedException {
        gmailHomePage.markMessagesAsImportant();
        LOG.info("3 conversation marked as important");
    }

    public void deleteImportantMessages(){
        gmailImportantPage.chooseSomeImportantMessages();
        LOG.info("Messages was choosen");
        gmailImportantPage.clickDeleteButton();
        LOG.info("Conversations are deleted");
    }

    public boolean messagesMarkedAsImportant(){
        return gmailHomePage.conversationWasMarked();
    }

    public boolean messagesDeleted(){
     return gmailImportantPage.verifyDeleteMessages();
    }




}
