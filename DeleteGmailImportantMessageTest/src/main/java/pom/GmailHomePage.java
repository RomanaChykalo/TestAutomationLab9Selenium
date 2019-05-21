package pom;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import util.PageObject;
import web_elements.Alert;
import web_elements.Button;
import web_elements.CheckBox;
import web_elements.TextInput;

import java.util.List;

public class GmailHomePage extends PageObject {
    private static Logger LOG = Logger.getLogger(GmailHomePage.class.getName());
    private static final int COUNT_IMPORTANT_MESSAGES = 3;

    @FindBy(xpath = "//div[text()='Compose']")
    private Button composenButton;

    @FindBy(xpath = "//ancestor::div[contains(@class,'wO nr l1')]//textarea")
    private TextInput emailReceiver;

    @FindBy(name = "subjectbox")
    private TextInput subjectBox;

    @FindBy(xpath = "//*[contains(@class,'Am Al editable LW-avf')]")
    private TextInput messageboxTextArea;

    @FindBy(xpath = "descendant::div[contains(@class,'dC')]")
    private Button sentMessageButton;

    @FindBy(xpath = "//ancestor::*[contains(@class,'aim ain')]//*[contains(@class,'nU')]//a")
    private Button sentButton;

    @FindBy(css = "*[href*='#drafts")
    private Button draftButton;

    @FindBy(xpath = "//*[@role='main']//tr")
    private List<CheckBox> lastMessageCheckboxes;


    @FindBy(xpath = "//*[contains(@class,'pG')]")
    private List<CheckBox> importantMessageCheckboxes;

    @FindBy(xpath = "//*[contains(@class,'J-Ke n0') and contains(@title,'Inbox')]")
    private WebElement inboxButtonInGmailPage;

    @FindBy(xpath = " //*[contains(text(), 'Conversation marked as important.')]")
    private Alert conversationAsImportantMessage;

    @FindBy(xpath = "//*[contains(@class,'nH Hd')]")
    private WebElement messageWidget;

    @FindBy(className = "aAy J-KU-KO aIf-aLe")
    private Button primaryMessage;

    @FindBy(xpath = "//*[contains(@class,'aio UKr6le')]//a[contains(@title,'Important')]")
    private Button importantButton;


    public GmailHomePage() {
        super();
    }

    public void clickComposeButton() {
        composenButton.clickButton();
        wait.until(ExpectedConditions.visibilityOf(messageWidget));
    }

    public void createMessage(String receiver, String subject, String message) throws InterruptedException {
        emailReceiver.typeTextAndSubmit(receiver);
        subjectBox.typeTextAndSubmit(subject);
        messageboxTextArea.typeText(message);
        wait(5);
        sentMessageButton.clickButton();
    }

    public void markMessagesAsImportant() throws InterruptedException {
        for (int i = 0; i < COUNT_IMPORTANT_MESSAGES; i++) {
            try {
                importantMessageCheckboxes.get(i).setChecked(true);
            }
            catch (org.openqa.selenium.StaleElementReferenceException ex){
                LOG.debug("Exception in finding date");
                LOG.debug(ex);
                importantMessageCheckboxes.get(i).setChecked(true);
            }
       }
    }

    public boolean conversationWasMarked() {
        if(conversationAsImportantMessage.isVisible());
        else
            LOG.error("Test failed");
        return conversationAsImportantMessage.isVisible();

    }
}
