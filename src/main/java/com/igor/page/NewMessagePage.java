package com.igor.page;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

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
        //doing receiver enable
        ((JavascriptExecutor) driver).executeScript("arguments[0].removeAttribute('style')", receiverArea);
        //waiting while js is running
        driverWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@name='to']/preceding-sibling::span/div[2]")));
        deleteContact.click();
    }
}
