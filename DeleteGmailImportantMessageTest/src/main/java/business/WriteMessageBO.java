package business;

import org.apache.log4j.Logger;
import pom.GmailHomePage;
import util.readers.Message;
import util.readers.XMLParser;

public class WriteMessageBO {
    private GmailHomePage gmailHomePage = new GmailHomePage();
    private static Logger LOG = Logger.getLogger(AutorizationBO.class.getName());

    public void writeGmailMessage(Message message) throws InterruptedException {
        LOG.info("User click compose button");
        gmailHomePage.clickComposeButton();
        LOG.info("User create new messages");
        gmailHomePage.createMessage(message.getRecipient() , message.getSubject(), message.getMessage_text());
    }
}
