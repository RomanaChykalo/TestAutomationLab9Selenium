package edu.gmail.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static edu.gmail.utils.ParserJSON.*;


public class GmailMailForm {

    private WebDriver driver;

    private static String subject;

    @FindBy(xpath = "//div[@class = 'T-I J-J5-Ji T-I-KE L3']")
    private WebElement buttonCompose;

    @FindBy(name = "to")
    private WebElement addressField;

    @FindBy(className = "aoT")
    private WebElement topicField;

    @FindBy(xpath = "//div[@class = 'Am Al editable LW-avf' and @role = 'textbox']")
    private WebElement messageField;

    @FindBy(xpath = "//img[@class = 'Ha']")
    private WebElement buttonExit;

    public GmailMailForm(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public static String getSubject() {
        return subject;
    }

    public void composeEmail() {
        (new WebDriverWait(driver, 15))
                .until(ExpectedConditions.elementToBeClickable(buttonCompose));
        buttonCompose.click();
    }

    public void setAddress() {
        (new WebDriverWait(driver, 20))
                .until(ExpectedConditions.visibilityOf(addressField));
        addressField.sendKeys(getAddressProperty());
    }

    public void setSubject() {
        subject = getSubjectProperty() + String.valueOf(Math.random());
        topicField.sendKeys(subject);
    }

    public void writeMessage() {
        messageField.click();
        messageField.sendKeys(getMessageProperty());
    }

    public void exitFromLetter() {
        buttonExit.click();
    }
}
