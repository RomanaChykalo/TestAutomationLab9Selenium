package pagemodels;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class GmailPage extends BasePage{
    Logger logger = LogManager.getLogger(GmailPage.class);

    @FindBy(xpath = "(//a[@title='Gmail'])[2]")
    WebElement gmailLogo;

    @FindBy(xpath = "//div[@class = 'aic']//div[@role='button']")
    WebElement writeNewEmailButton;

    @FindBy(xpath = "//div[@role='dialog']")
    WebElement dialogWindow;

    @FindBy(name = "to")
    WebElement receiver;

    @FindBy(name = "subjectbox")
    WebElement subject;

    @FindBy(css = "div[role='textbox']")
    WebElement emailBody;

    @FindBy(xpath = "(//div[@class='dC']/div)[1]")
    WebElement sendBtn;

    public GmailPage() {
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
}
