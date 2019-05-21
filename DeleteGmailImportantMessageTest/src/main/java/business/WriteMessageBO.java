package business;

import org.apache.log4j.Logger;
import pom.GmailHomePage;

public class WriteMessageBO {
    private GmailHomePage gmailHomePage = new GmailHomePage();
    private static Logger LOG = Logger.getLogger(AutorizationBO.class.getName());

    public void writeGmailMessage(String res , String to, String mes) throws InterruptedException {
        LOG.info("User click compose button");
        gmailHomePage.clickComposeButton();
        LOG.info("User create new messages");
        gmailHomePage.createMessage(res, mes, to);
    }
}
