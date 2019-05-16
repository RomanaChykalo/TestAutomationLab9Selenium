package com.igor.page.widget;

import com.igor.page.BasePage;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static com.igor.utils.constant.Constants.EXPLICIT_WAIT;


public class NewMessageWidget extends BasePage {
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
    @FindBy(xpath = "//table[@role='group']/tbody/tr/td[1]/div/div/div[1]")
    private WebElement sendButton;


    public void setReceiverField(String receiver) {
        (new WebDriverWait(driver, EXPLICIT_WAIT)).until(ExpectedConditions.visibilityOf(receiverField));
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
        WebDriverWait webDriverWait = new WebDriverWait(driver, EXPLICIT_WAIT);
        messageField.sendKeys(Keys.chord(Keys.CONTROL, Keys.SHIFT, "B"));
        webDriverWait.until(ExpectedConditions.visibilityOf(receiverArea));
        webDriverWait.until(ExpectedConditions.elementToBeClickable(deleteContact)).click();
    }
}
