package edu.pageobject.pagefactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;


public class GmailDraftForm {

    private WebDriver driver;

    @FindBy(xpath = "//div[@class = 'TN bzz aHS-bnq']//child::div[@class = 'bsU']")
    private WebElement draftSection;

    @FindBy(xpath = "//span[@class='bog']")
    private List<WebElement> draftEmails;

    @FindBy(xpath = "//div[@role = 'button' and @data-tooltip-delay = '800']")
    private WebElement buttonSend;

    public GmailDraftForm(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void clickDraftSection() {
        draftSection.click();
    }

    public void clickDraftEmail(String topic) {
        for (int i = 0; i < draftEmails.size(); i++) {
            if (draftEmails.get(i).getText().contains(topic)) {
                draftEmails.get(i).click();
                break;
            }
        }
    }

    public void clickSendButton() {
        buttonSend.click();
    }
}
