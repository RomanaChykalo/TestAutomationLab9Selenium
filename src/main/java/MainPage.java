import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MainPage extends BasePage {

    @FindBy(className = "aic")
    private WebElement composeButton;
    @FindBy(name = "to")
    private WebElement whomField;

    @FindBy(className = "aoT")
    private WebElement subjectField;

    @FindBy(css = ".Ar.Au div")
    private WebElement textField;

    @FindBy(css = ".dC div")
    private WebElement sendButton;

    @FindBy(xpath = "//img[@class ='Ha']")
    private WebElement closeButton;

    @FindBy(xpath = "//a[@href ='https://mail.google.com/mail/u/0/#drafts']")
    private WebElement draftButton;

    @FindBy(xpath = "//div[@class = 'ae4 UI']//tbody/tr[2]")
    private WebElement draft;

    @FindBy(xpath = "//*[@class = 'F cf zt']//tbody/tr[2]//td[@class = 'xY a4W']//div//span[@class = 'bog']/span")
    private WebElement savedSubject;

    @FindBy(xpath = "//*[@class = 'F cf zt']//tbody/tr[2]//td[@class = 'xY a4W']//div//span[@class = 'bog']/span/following::span")
    private WebElement savedText;

    @FindBy(xpath = "//div[@class ='oL aDm az9']/span")
    private WebElement savedEmailAddress;

    public void clickComposeButton() {
        composeButton.click();
    }

    public void typeWhomField(String emailAddress) {
        whomField.sendKeys(emailAddress);
    }

    public void typeSubjectField(String subject) {
        subjectField.sendKeys(subject);
    }

    public void typeTextField(String text) {
        textField.sendKeys(text);
    }

    public void clickSendButton() {
        sendButton.click();
    }

    public void clickOnCloseMessageButton() {
        closeButton.click();
    }

    public void clickOnDraftButton() {
        draftButton.click();
    }

    public String takeLetterSubject() {
        return savedSubject.getText();
    }

    public String takeLetterText() {
        return savedText.getText();
    }

    public String takeEmailAddress() {
        return savedEmailAddress.getText();
    }

    public void clickOnDraft() {
        draft.click();
    }
}

