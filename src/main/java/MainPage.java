import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MainPage extends BasePage {

    @FindBy(xpath = "//a[@title = 'Gmail']")
    private WebElement title;

    @FindBy(className = "aic")
    private WebElement writeEmailButton;
    @FindBy(name = "to")
    private WebElement whomField;

    @FindBy(className = "aoT")
    private WebElement subjectField;

    @FindBy(css = ".Ar.Au div")
    private WebElement textField;

    @FindBy(css = ".dC div")
    private WebElement sendButton;

    @FindBy(xpath = "//div[@class = 'Hp']/child::h2/div[2]")
    private WebElement headingMessageForm;

    @FindBy(xpath = "//div[@class = 'vh']")
    private WebElement sendingSuccessMessage;

    @FindBy(xpath = "//img[@class ='Ha']")
    private WebElement closeButton;

    @FindBy(xpath = "//a[@href ='https://mail.google.com/mail/u/0/#drafts']")
    private WebElement draftButton;

    @FindBy(xpath = "//div[@class = 'ae4 UI']//tbody/tr[2]")
    private WebElement messageButton;

    @FindBy(xpath = "//*[@class = 'F cf zt']//tbody/tr[2]//td[@class = 'xY a4W']//div//span[@class = 'bog']/span")
    private WebElement savedFirstLetterInDraft;

    @FindBy(xpath = "//*[@class = 'F cf zt']//tbody/tr[2]//td[@class = 'xY a4W']//div//span[@class = 'bog']/span/following::span")
    private WebElement savedTextMessageinDraft;

    @FindBy(xpath = "//div[@class ='oL aDm az9']/span")
    private WebElement savedEmailAddress;

    @FindBy(xpath = "//a[text() = 'stepankish@gmail.com']/ancestor::div//span")
    private WebElement savedEmailAddressinDtraft;

    public void clickWriteButton() {
        writeEmailButton.click();
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

    public String foundSubjectOfMessage() {
        return savedFirstLetterInDraft.getText();
    }

    public String foundTextMessage() {
        return savedTextMessageinDraft.getText();
    }

    public String foundEmailAddress() {
        return savedEmailAddress.getText();
    }

    public void clickOnMessage() {
        messageButton.click();
    }
}

