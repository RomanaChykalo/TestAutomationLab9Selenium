package pages.businessobj;

import data.Consts;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import pages.pagemodels.GmailPage;

public class EmailBO {
    private GmailPage gmailPage;

    public EmailBO() {}

    public EmailBO(GmailPage gmailPage) {
        this.gmailPage = gmailPage;
    }

    public void sendEmail(String recipient, String subject, String mailBody){
        gmailPage.clickWriteNewEmailBtn();
        gmailPage.setReceiver(recipient);
        gmailPage.setEmailSubject(subject);
        gmailPage.setEmailBody(mailBody);
        gmailPage.clickSendBtn();
        gmailPage.expWait(25).until(ExpectedConditions.visibilityOf(gmailPage.getViewEmailSentMsgBtn().getWebElement()));
    }

    public boolean isViewEmailSentMsgBtnDisplayed(){
        return gmailPage.getViewEmailSentMsgBtn().isDisplayed();
    }

    public void deleteUnreadEmail(int emailsQty){
        gmailPage.checkUnreadEmailsInboxAndDelete(emailsQty);
    }

    public boolean areEmailsDeleted(){
        return gmailPage.isDeleteEmailsSuccessMessageDisplayed();
    }

    public void revertDeletedEmails(){
        gmailPage.clickRevertBtn();
    }

    public boolean isActionCancelledMsgDispalyed(){
        return gmailPage.isActionCancelledMsgDispalyed();
    }
}
