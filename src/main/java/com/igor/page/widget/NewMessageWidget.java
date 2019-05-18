package com.igor.page.widget;

import com.igor.decorator.element.Button;
import com.igor.decorator.element.Label;
import com.igor.page.BasePage;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;


public class NewMessageWidget extends BasePage {
    @FindBy(xpath = "//textarea[@name='to']")
    private Label receiverField;
    @FindBy(xpath = "//form[@enctype='multipart/form-data']/div[1]")
    private Label receiverArea;
    @FindBy(xpath = "//input[@name='to']/preceding-sibling::span/div[2]")
    private Button deleteContact;
    @FindBy(xpath = "//input[@name='subjectbox']")
    private Label titleField;
    @FindBy(xpath = "//input[@name='composeid']/../../table//div[@role='textbox']")
    private Label messageField;
    @FindBy(xpath = "//table[@role='group']/tbody/tr/td[1]/div/div/div[1]")
    private Button sendButton;


    public void setReceiverField(String receiver) {
        webDriverWait.until(ExpectedConditions.visibilityOf(receiverField.getWebElement()));
        receiverField.clear();
        receiverField.sendKeys(receiver);
    }

    public void setTitleField(String title) {
        titleField.sendKeys(title);
    }

    public void setMessageField(String message) { messageField.sendKeys(message); }

    public void clickToSendButton() {
        sendButton.click();
    }

    public void clickToDeleteContact() {
        messageField.sendKeys(Keys.chord(Keys.CONTROL, Keys.SHIFT, "B"));
        webDriverWait.until(ExpectedConditions.visibilityOf(receiverArea.getWebElement()));
        webDriverWait.until(ExpectedConditions.elementToBeClickable(deleteContact.getWebElement())).click();
    }
}
