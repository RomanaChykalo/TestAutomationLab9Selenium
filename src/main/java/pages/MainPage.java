package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MainPage extends BasePage {

    @FindBy(className = "aic")
    private WebElement composeButton;

    @FindBy(name = "to")
    private WebElement whomField;

    @FindBy(xpath = "//span[@class='vN bfK a3q']")
    private WebElement savedEmail;

    @FindBy(className = "aoT")
    private WebElement subjectField;

    @FindBy(css = ".Ar.Au div")
    private WebElement textField;

    @FindBy(css = ".dC div")
    private WebElement sendButton;

    @FindBy(xpath = "//img[@class ='Ha']")
    private WebElement closeButton;

    @FindBy(xpath = "//div[@class = 'TO']//span[@class='nU n1']/a")
    private WebElement draftButton;

    @FindBy(xpath = "//div[@class = 'ae4 UI']//tbody/tr[1]")
    private WebElement draft;

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
       new WebDriverWait(driver, 30).until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@class = 'vh']")));
    }

    public void clickOnCloseMessageButton() {
        closeButton.click();
    }

    public void clickOnDraftButton() {
        new WebDriverWait(driver, 30).until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//div[@class = 'TO']//span[@class='nU n1']/a")));
        draftButton.click();
    }

    public String takeEmailAddress() {
        new WebDriverWait(driver, 30).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[@class='vN bfK a3q']")));
        return savedEmail.getAttribute("email");
    }

    public String takeLetterSubject() {
        return subjectField.getAttribute("value");
    }

    public String takeLetterText() {
        return textField.getText();
    }

    public void clickOnDraft() {
        draft.click();
    }
}

