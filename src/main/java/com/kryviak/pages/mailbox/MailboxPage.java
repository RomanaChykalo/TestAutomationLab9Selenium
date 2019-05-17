package com.kryviak.pages.mailbox;

import com.kryviak.pages.AbstractPage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;

import java.util.List;

public class MailboxPage extends AbstractPage {
    private static Logger logger = LogManager.getLogger(MailboxPage.class);

    private By clickCreateNewLetterButtonLocator = By.xpath("//div[@role='button'and@tabindex='0'and@gh='cm']");
    private By emailToTextFieldLocator = By.xpath("//textarea[1]");
    private By subjectToTextFieldLocator = By.xpath("//input[@name='subjectbox']");
    private By messageToTextFieldLocator = By.xpath("//div[@role='textbox']");
    private By clickSentMessageButtonLocator = By.xpath("//div[@role='button'and@tabindex='1'and@data-tooltip-delay='800']");
    private By clickSendMessageLinkLocator = By.xpath("//a[@target='_top'and@title='Sent']");
    private By checkIfMessageIsSendButtonLocator = By.cssSelector("tbody div .bog");
    private By undoLinkLocator = By.cssSelector("#link_undo[style='visibility:hidden']");
    private By getAllMessages = By.xpath("//div[@role='main']/div/div/div/table/tbody/tr[@draggable='true']");
    private By deleteMessageButton = By.xpath("//body/div/div/div/div/div/div/div/div/div/div/div/div[2]/div/div/div/div/div/div[@title='Delete']/div"); //div[data-tooltip='Delete']>div  div[title='Delete']
    private By getMessageTitle = By.xpath("td[6]/div/div/div/span");
    private By clickSelectButton = By.xpath("td[2]");

    public void clickCreateNewLetterButton() {
        waitForElementVisible(clickCreateNewLetterButtonLocator);
        webDriver.findElement(clickCreateNewLetterButtonLocator).click();
        logger.info("Click to the 'Compose' button on your email page");
    }

    public void setEmailToTextField(String emailTo) {
        waitForElementVisible(emailToTextFieldLocator);
        webDriver.findElement(emailToTextFieldLocator).sendKeys(emailTo);
        logger.info("You sent a letter to: " + emailTo);
    }

    public void setSubjectToTextField(String subjectTo) {
        waitForElementVisible(subjectToTextFieldLocator);
        webDriver.findElement(subjectToTextFieldLocator).sendKeys(subjectTo);
        logger.info("Subject is: " + subjectTo);
    }

    public void setMessageToTextField(String messageTo) {
        waitForElementVisible(messageToTextFieldLocator);
        webDriver.findElement(messageToTextFieldLocator).sendKeys(messageTo);
        logger.info("Message is: " + messageTo);
    }

    public void clickSenDMessageButton() {
        waitForElementVisible(clickSentMessageButtonLocator);
        webDriver.findElement(clickSentMessageButtonLocator).click();
        logger.info("Click to the 'Send' button to sent the message");
    }

    /**
     * Click on 'Sent' menu item to view sent messages.
     */
    public void clickSenTMessageLink() {
        waitForElementVisible(clickSendMessageLinkLocator);
        checkIfDisplayedElement(webDriver.findElement(clickSendMessageLinkLocator));
        webDriver.findElement(clickSendMessageLinkLocator).click();
        logger.info("Click to the 'Sent' link to view all send message");
    }

    public List<WebElement> getMessageTitles() {
        waitForElementIsNotVisible(undoLinkLocator);
        return webDriver.findElements(checkIfMessageIsSendButtonLocator);
    }

    private List<WebElement> getAllMessagesRows() {
        waitForElementVisible(getAllMessages);
        return webDriver.findElements(getAllMessages);
    }

    public void selectMessageByTitle(String msgTitle) {
        List<WebElement> mailTitlesList = getAllMessagesRows();
        mailTitlesList.stream()
                .filter(webElement -> webElement.findElement(getMessageTitle).getText().equals(msgTitle))
                .findFirst()
                .ifPresent(webElement -> webElement.findElement(clickSelectButton).click());
    }

    public void deleteSelectedMessage() {
        waitForElementIsNotVisible(undoLinkLocator);
        waitForElementVisible(deleteMessageButton);
        webDriver.findElement(deleteMessageButton).click();
        logger.info("Delete the message");
    }

    private void checkIfDisplayedElement(WebElement webElement) {
        try {
            webElement.isDisplayed();
        } catch (StaleElementReferenceException ignored) {
        }
    }
}
