package pages.bo;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import pages.pagemodels.GmailPage;

public class EmailManager {
    private GmailPage gmailPage;

    public EmailManager() {
    }

    public EmailManager(GmailPage gmailPage) {
        this.gmailPage = gmailPage;
    }

    public GmailPage getGmailPage() {
        return gmailPage;
    }

    public void sendEmail(String recipient, String subject, String mailBody){
        gmailPage.clickWriteNewEmailBtn();
        gmailPage.setReceiver(recipient);
        gmailPage.setEmailSubject(subject);
        gmailPage.setEmailBody(mailBody);
        gmailPage.clickSendBtn();
        gmailPage.expWait(25).until(ExpectedConditions.visibilityOf(gmailPage.getViewEmailSentMsgBtn()));
    }

    public WebElement getViewEmailSentMsgBtn(){
        return gmailPage.getViewEmailSentMsgBtn();
    }

    public void delete3UnreadEmailAndVerifyTheyAreReverted(){
        gmailPage.check3UnreadEmailsInboxAndDelete();
        Assert.assertTrue(gmailPage.getDeleteEmailsSuccessMessage().isDisplayed());
        gmailPage.clickRevertBtn();
        Assert.assertTrue(gmailPage.getActionCancelledMsg().isDisplayed());
    }
}
