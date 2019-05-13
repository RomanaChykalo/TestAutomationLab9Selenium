package com.igor.page;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class NewMessagePage extends BasePage {
    @FindBy(xpath = "//textarea[@name='to']")
    private WebElement receiverField;
    @FindBy(xpath = "//form[@enctype='multipart/form-data']/div[1]")
    private WebElement receiverArea;
    @FindBy(xpath = "//input[@name='to']/preceding-sibling::span/div[2]")
    private WebElement deleteContact;
    @FindBy(xpath = "//input[@name='subjectbox']")
    private WebElement titleField;
    @FindBy(xpath = "//input[@name='composeid']/../../table//div[@role='textbox']")
    private WebElement messageField;
    @FindBy(css = "table[role=group]>tbody>tr>td:first-child>div>div>div:first-child")
    private WebElement sendButton;
    @FindBy(xpath = "//button[@name='ok']")
    private WebElement buttonOkInAlertDialog;

    public void setReceiverField(String receiver) {
        receiverField.clear();
        receiverField.sendKeys(receiver);
    }

    public void setTitleField(String title) {
        titleField.sendKeys(title);
    }

    public void setMessageField(String message) {
        messageField.sendKeys(message);
    }

    public void clickToSendButton() {
        sendButton.click();
    }

    public boolean alertDialogIsEnable() {
        try {
            buttonOkInAlertDialog.getText();
        } catch (NoSuchElementException e) {
            return false;
        }
        return true;
    }

    public void clickToButtonOkInAlertDialog() {
        buttonOkInAlertDialog.click();
    }

    public void clickToDeleteContact() {
        WebDriverWait webDriverWait = new WebDriverWait(driver, 15);
        messageField.sendKeys(Keys.chord(Keys.CONTROL, Keys.SHIFT, "B"));
        webDriverWait.until(ExpectedConditions.visibilityOf(receiverArea));
        webDriverWait.until(ExpectedConditions.elementToBeClickable(deleteContact)).click();
    }
}
