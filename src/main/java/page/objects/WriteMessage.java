package page.objects;

import driver.DriverManager;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class WriteMessage extends BasePage{

    @FindBy(name = "to")
    private WebElement receiverInput;

    @FindBy(className = "editable")
    private WebElement messageInput;

    @FindBy(xpath = "//img[@class='Ha' and @src='images/cleardot.gif']")
    private WebElement closedButton;

    @FindBy(name = "subjectbox")
    private WebElement subjectInput;

    public void toWriteMessage(String message,String receiver, String subject){
        receiverInput.sendKeys(receiver);
        subjectInput.sendKeys(subject);
        messageInput.sendKeys(message);
    }

    public void toCloseMessage(){
        closedButton.click();
    }
}
