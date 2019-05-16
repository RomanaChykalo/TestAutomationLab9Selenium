package pages.pagemodels;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pages.BasePage;

import java.util.List;

public class GmailPage extends BasePage {
    Logger logger = LogManager.getLogger(GmailPage.class);

    @FindBy(xpath = "(//a[@title='Gmail'])[2]")
    private WebElement gmailLogo;

    @FindBy(xpath = "//div[@class = 'aic']//div[@role='button']")
    private WebElement writeNewEmailButton;

    @FindBy(xpath = "//div[@role='dialog']")
    private WebElement dialogWindow;

    @FindBy(name = "to")
    private WebElement receiver;

    @FindBy(name = "subjectbox")
    private WebElement subject;

    @FindBy(css = "div[role='textbox']")
    private WebElement emailBody;

    @FindBy(xpath = "(//div[@class='dC']/div)[1]")
    WebElement sendBtn;

    @FindBy(xpath = "(//div[@class='Cp'])[1]//tr")
    private List<WebElement> unreadInboxEmailList;

    @FindBy(xpath = "(//div[@class='asa'])[3]")
    private WebElement deleteSelectedEmailsBtn;

    @FindBy(xpath = "//div[@class='vh']")
    private WebElement deleteEmailsSuccessMessage;

    @FindBy(xpath = "(//div[@class='vh']/span/span)[2]/span")
    private WebElement revertBtn;

    @FindBy(xpath = "//span[@class='aT']/span")
    private WebElement actionCancelledMsg;

    @FindBy(id = "link_vsm")
    private WebElement viewEmailSentMsgBtn;


    public GmailPage() {
        expWait(30).until(ExpectedConditions.visibilityOf(gmailLogo));
    }

    public WebElement getGmailLogo() {
        return this.gmailLogo;
    }

    public WebElement getWriteNewEmailButton() {
        return this.writeNewEmailButton;
    }

    public WebElement getDialogWindow() {
        return this.dialogWindow;
    }

    public WebElement getReceiver() {
        return this.receiver;
    }

    public WebElement getSubject() {
        return this.subject;
    }

    public WebElement getEmailBody() {
        return this.emailBody;
    }

    public WebElement getSendBtn() {
        return this.sendBtn;
    }

    public List<WebElement> getUnreadInboxEmailList() {
        return unreadInboxEmailList;
    }

    public WebElement getDeleteSelectedEmailsBtn() {
        return deleteSelectedEmailsBtn;
    }

    public WebElement getDeleteEmailsSuccessMessage() {
        return deleteEmailsSuccessMessage;
    }

    public WebElement getRevertBtn() {
        return revertBtn;
    }

    public WebElement getCancelledMsg() {
        return actionCancelledMsg;
    }

    public WebElement getViewEmailSentMsgBtn() {
        return viewEmailSentMsgBtn;
    }

    public void clickWriteNewEmailBtn(){
        logger.trace("Clicking Write new email btn...");
        getWriteNewEmailButton().click();
    }

    public void setReceiver(String receiverEmail){
        logger.trace("Typing receiver email ...");
        getReceiver().sendKeys(receiverEmail);
    }

    public void setEmailSubject(String subject){
        logger.trace("Typing email subject...");
        getSubject().sendKeys(subject);
    }

    public void setEmailBody(String emailBody){
        logger.trace("Typing email email body...");
        getEmailBody().sendKeys(emailBody);
    }

    public void clickSendBtn(){
        logger.trace("Clicking Send email btn...");
        getSendBtn().click();
    }

    public void check3UnreadEmailsInboxAndDelete() {
        if (getUnreadInboxEmailList().size() >= 3) {
            for (int i = 0; i < 3; i++) {
                logger.trace("Choosing " + (i + 1) + " unread email...");
                expWait(35).until(ExpectedConditions.elementToBeClickable(getUnreadInboxEmailList().get(i)));
                getUnreadInboxEmailList().get(i).findElement(By.xpath("(./td)[2]/div")).click();
            }
            getDeleteSelectedEmailsBtn().click();
        } else {
            logger.trace("There is no 3 unread emails inbox!");
            return;
        }
    }

    public void clickRevertBtn(){
        expWait(25).until(ExpectedConditions.elementToBeClickable(getRevertBtn()));
        getRevertBtn().click();
    }

    public WebElement getActionCancelledMsg(){
        expWait(25).until(ExpectedConditions.visibilityOf(getCancelledMsg()));
        return getCancelledMsg();
    }
}
