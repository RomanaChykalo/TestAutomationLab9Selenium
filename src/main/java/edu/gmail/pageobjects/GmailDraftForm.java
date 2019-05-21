package edu.gmail.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

import static edu.gmail.pageobjects.GmailMailForm.getSubject;


public class GmailDraftForm {

    private WebDriver driver;

    @FindBy(xpath = "//div[@class = 'TN bzz aHS-bnq']//child::div[@class = 'bsU']")
    private WebElement draftSection;

    @FindBy(xpath = "//span[@class='bog']")
    private List<WebElement> draftEmails;

    @FindBy(xpath = "//div[@role = 'button' and @data-tooltip-delay = '800']")
    private WebElement buttonSend;

    private By numberOfDraftMessages = By.xpath("//div[@class = 'TN bzz aHS-bnq']//child::div[@class = 'bsU']");

    private By successfulEmailSendingIndicator = By.xpath("//div[@class = 'vh']");

    public GmailDraftForm(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public By getNumberOfDraftMessages() {
        return numberOfDraftMessages;
    }

    public WebElement getNumberOfDraftMessagesElement() {
        return driver.findElement(numberOfDraftMessages);
    }

    public By getSuccessfulEmailSendingIndicator() {
        return successfulEmailSendingIndicator;
    }

    public void clickDraftSection() {
        draftSection.click();
    }

    public void clickDraftEmail() {
        for (int i = 0; i < draftEmails.size(); i++) {
            if (draftEmails.get(i).getText().contains(getSubject())) {
                draftEmails.get(i).click();
                break;
            }
        }
    }

    public void clickSendButton() {
        buttonSend.click();
    }
}
