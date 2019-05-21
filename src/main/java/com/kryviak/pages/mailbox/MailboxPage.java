package com.kryviak.pages.mailbox;

import com.kryviak.decorator.ButtonDecor;
import com.kryviak.decorator.InputTextDecor;
import com.kryviak.decorator.LinkDecor;
import com.kryviak.pages.AbstractPage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class MailboxPage extends AbstractPage {

    private static Logger logger = LogManager.getLogger(MailboxPage.class);

    @FindBy(xpath = "//div[@role='button'and@tabindex='0'and@gh='cm']")
    private ButtonDecor clickCreateNewLetterButtonLocator;

    @FindBy(xpath = "//textarea[1]")
    private InputTextDecor emailToTextFieldLocator;

    @FindBy(xpath = "//input[@name='subjectbox']")
    private InputTextDecor subjectToTextFieldLocator;

    @FindBy(xpath = "//div[@role='textbox']")
    private InputTextDecor messageToTextFieldLocator;

    @FindBy(xpath = "//div[@role='button'and@tabindex='1'and@data-tooltip-delay='800']")
    private ButtonDecor clickSentMessageButtonLocator;

    @FindBy(xpath = "//a[@target='_top'and@title='Sent']")
    private LinkDecor clickSendMessageLinkLocator;

    @FindBy(css = "tbody div .bog")
    private List<WebElement> checkIfMessageIsSendButtonLocator;

    @FindBy(css = "#link_undo[style='visibility:hidden']")
    private LinkDecor undoLinkLocator;

    @FindBy(xpath = "//div[@role='main']/div/div/div/table/tbody/tr[@draggable='true']")
    private List<WebElement> getAllMessages;

    @FindBy(xpath = "//body/div/div/div/div/div/div/div/div/div/div/div/div[2]/div/div/div/div/div/div[@title='Delete']/div")
    private ButtonDecor deleteMessageButtonLocator;

    public void clickCreateNewLetterButton() {
        logger.info("Click to the 'Compose' button on your email page to create new letter");
        clickCreateNewLetterButtonLocator.click();
    }

    public void setEmailToTextField(String emailTo) {
        logger.info("Set email to: " + emailTo);
        emailToTextFieldLocator.sendKeys(emailTo);
    }

    public void setSubjectToTextField(String subjectTo) {
        logger.info("Set subject to: " + subjectTo);
        subjectToTextFieldLocator.sendKeys(subjectTo);
    }

    public void setMessageToTextField(String messageTo) {
        logger.info("Set message to: " + messageTo);
        messageToTextFieldLocator.sendKeys(messageTo);
    }

    public void clickSendMessageButton() {
        logger.info("Click to the 'Send' button to sent the message");
        clickSentMessageButtonLocator.click();
    }

    /**
     * Click on 'Sent' menu item to view sent messages.
     */
    public void clickSentMessageLink() {
        waitForElementIsNotVisible(undoLinkLocator);
        logger.info("Click to the 'Sent' link to view all sent message");
        clickSendMessageLinkLocator.isDisplayed();
        clickSendMessageLinkLocator.click();
    }

    public List<WebElement> getMessageTitles() {
        waitForElementIsNotVisible(checkIfMessageIsSendButtonLocator.get(0));
        checkIfDisplayedElement(checkIfMessageIsSendButtonLocator.get(0));
        return checkIfMessageIsSendButtonLocator;
    }

    public void selectMessageByTitle(String msgTitle) {
        // Used BY instead of @FindBy according to used for locator chaining.
        By mailTitleSelector = By.xpath("td[6]/div/div/div/span");
        By deleteButtonSelector = By.xpath("td[2]");

        waitForElementIsNotVisible(undoLinkLocator);
        getAllMessages.get(0).isDisplayed();
        getAllMessages.parallelStream()
                .filter(webElement -> webElement.findElement(mailTitleSelector).getText().equals(msgTitle))
                .findFirst()
                .ifPresent(webElement -> webElement.findElement(deleteButtonSelector).click());
    }

    public void deleteSelectedMessage() {
        logger.info("Delete the set message");
        deleteMessageButtonLocator.click();
    }
}
