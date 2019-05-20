package pages;

import elements.Button;
import elements.Label;
import elements.Popup;
import elements.TextField;
import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class MainPage extends BasePage {

    @FindBy(className = "aic")
    private Button composeButton;

    @FindBy(name = "to")
    private TextField whomField;

    @FindBy(xpath = "//span[@class='vN bfK a3q']")
    private TextField savedEmail;

    @FindBy(className = "aoT")
    private TextField subjectField;

    @FindBy(css = ".Ar.Au div")
    private TextField textField;

    @FindBy(css = ".dC div")
    private Button sendButton;

    @FindBy(xpath = "//img[@class ='Ha']")
    private Button closeButton;

    @FindBy(xpath = "//input[@class='gb_Fe']")
    private TextField draftFolder;

    @FindBy(xpath = "//div[@class = 'ae4 UI']//tbody/tr[1]/td[5]")
    private Label lastMessageInDraftFolder;

    @FindBy(xpath = "//div[@class = 'vh']")
    private Popup popUpMessage;


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
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@class = 'vh']")));
    }

    public void clickOnCloseMessageButton() {
        closeButton.click();
    }

    public void clickOnDraftFolder() {
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@class='gb_Fe']")));
        draftFolder.sendKeys("in:draft");
        draftFolder.pressEnter();
    }

    public String takeEmailAddress() {
        return savedEmail.getAttribute("email");
    }

    public String takeLetterSubject() {
        return subjectField.getAttribute("value");
    }

    public String takeLetterText() {
        return textField.getText();

    }

    public void clickOnLastMessageInDraftFolder() {
        lastMessageInDraftFolder.click();
    }
}

