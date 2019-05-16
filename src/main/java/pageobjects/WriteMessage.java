package pageobjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class WriteMessage extends BasePage{

    @FindBy(css ="textarea[name='to']")
    private WebElement receiverInput;

    @FindBy(className = "editable")
    private WebElement messageInput;

    @FindBy(xpath = "//img[@class='Ha' and @src='images/cleardot.gif']")
    private WebElement closedButton;

    @FindBy(name = "subjectbox")
    private WebElement subjectInput;

    public void inputReceiver(String receiver){
        receiverInput.sendKeys(receiver);
    }

    public void inputMessage(String message){
        messageInput.sendKeys(message);
    }

    public void inputSubject(String subject){
        subjectInput.sendKeys(subject);
    }

    public void toCloseMessage(){
        closedButton.click();
    }
}
