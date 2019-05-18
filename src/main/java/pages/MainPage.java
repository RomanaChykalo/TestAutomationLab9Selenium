package pages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;


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
    private WebElement draftFolder;

    @FindBy(xpath = "//div[@class = 'ae4 UI']//tbody/tr[1]")
    private WebElement lastMessageInDraftList;

    @FindBy(xpath = "//div[@class = 'vh']")
    private WebElement popUpMessage;


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
      wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//div[@class = 'TO']//span[@class='nU n1']/a")));
        draftFolder.click();
    }

    public String takeEmailAddress() {
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[@class='vN bfK a3q']")));
        return savedEmail.getAttribute("email");
    }

    public String takeLetterSubject() {
        return subjectField.getAttribute("value");
    }

    public String takeLetterText() {
        return textField.getText();
    }

    public void clickOnLastMessageInDraftList() {
        lastMessageInDraftList.click();
    }
}

