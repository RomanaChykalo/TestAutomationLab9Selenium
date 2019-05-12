package com.kryviak.pages.mailbox;

import com.kryviak.config.DriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class MailboxPage {
    private static Logger logger = LogManager.getLogger(MailboxPage.class);
    private WebDriver webDriver = DriverManager.getInstance().getWebDriver();

    private By clickCreateNewLetterButtonLocator = By.xpath("//div[@role='button'and@tabindex='0'and@gh='cm']");
    private By emailToTextFieldLocator = By.xpath("//textarea[1]");
    private By subjectToTextFieldLocator = By.xpath("//input[@name='subjectbox']");
    private By messageToTextFieldLocator = By.xpath("//div[@role='textbox']");
    private By clickSentMessageButtonLocator = By.xpath("//div[@role='button'and@tabindex='1'and@data-tooltip-delay='800']");
    private By clickSendMessageLinkLocator = By.xpath("//a[@target='_top'and@title='Sent']");
    private By checkIfMessageIsSendButtonLocator = By.cssSelector("tbody div .bog");
    private By undoLinkLocator = By.cssSelector("#link_undo[style='visibility:hidden']");
    private By getAllMessages = By.xpath("//div[@role='main']/div/div/div/table/tbody/tr[@draggable='true']");
    private By deleteMessageButton = By.cssSelector("div[data-tooltip='Delete']");
    private By getMessageTitle = By.xpath("td[6]/div/div/div/span");
    private By clickSelectButton = By.xpath("td[2]");

    public void clickCreateNewLetterButton() {
        DriverManager.getInstance().waitForElementVisible(clickCreateNewLetterButtonLocator);
        webDriver.findElement(clickCreateNewLetterButtonLocator).click();
       logger.info("Click to the 'Compose' button on your email page");
    }

    public void setEmailToTextField(String emailTo) {
        DriverManager.getInstance().waitForElementVisible(emailToTextFieldLocator);
        webDriver.findElement(emailToTextFieldLocator).sendKeys(emailTo);
        logger.info("You sent a letter to: " + emailTo);
    }

    public void setSubjectToTextField(String subjectTo) {
        DriverManager.getInstance().waitForElementVisible(subjectToTextFieldLocator);
        webDriver.findElement(subjectToTextFieldLocator).sendKeys(subjectTo);
        logger.info("Subject is: " + subjectTo);
    }

    public void setMessageToTextField(String messageTo) {
        DriverManager.getInstance().waitForElementVisible(messageToTextFieldLocator);
        webDriver.findElement(messageToTextFieldLocator).sendKeys(messageTo);
        logger.info("Message is: " + messageTo);
    }

    public void clickSenDMessageButton() {
        DriverManager.getInstance().waitForElementVisible(clickSentMessageButtonLocator);
        webDriver.findElement(clickSentMessageButtonLocator).click();
        logger.info("Click to the 'Send' button to sent the message");
    }

    /**
     * Click on 'Sent' menu item to view sent messages.
     */
    public void clickSenTMessageLink() {
        DriverManager.getInstance().waitForElementVisible(clickSendMessageLinkLocator);
        webDriver.findElement(clickSendMessageLinkLocator).click();
        logger.info("Click to the 'Sent' link to view all send message");
    }

    public List<WebElement> getMessageTitles() {
        DriverManager.getInstance().waitForElementIsNotVisible(undoLinkLocator);
        return webDriver.findElements(checkIfMessageIsSendButtonLocator);
    }

    private List<WebElement> getAllMessagesRows() {
        DriverManager.getInstance().waitForElementVisible(getAllMessages);
        return webDriver.findElements(getAllMessages);
    }

    public void selectMessageByTitle(String msgTitle) {
        List<WebElement> mailTitlesList = getAllMessagesRows();
        for (WebElement webElement : mailTitlesList) {
            if (webElement.findElement(getMessageTitle).getText().equals(msgTitle)) {
                webElement.findElement(clickSelectButton).click();
                break;
            }
        }
    }

    public void deleteSelectedMessage(){
        DriverManager.getInstance().waitForElementIsNotVisible(undoLinkLocator);
        DriverManager.getInstance().waitForElementVisible(deleteMessageButton);
        webDriver.findElement(deleteMessageButton).click();
        logger.info("Delete the message");
    }
}
