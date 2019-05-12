package page.objects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class DraftMessagePage extends BasePage {

    @FindBy(css = "input.gb_He[name='q']")
    private WebElement searchInput;

    @FindBy(css = "button.gb_Qe.gb_Re")
    private WebElement goToDraftMessage;

    @FindBy(xpath = "//span[./span[contains(.,'Subject')]]")
    private List<WebElement> messages;

    @FindBy(name = "to")
    private WebElement receiverInput;

    @FindBy(xpath = "//div[@class='T-I J-J5-Ji aoO v7 T-I-atl L3' and @role='button']")
    private WebElement sendButton;

    public String goToDraftMessageAndCheck(){
        searchInput.sendKeys("in:draft");
        goToDraftMessage.click();
        return messages.get(0).getText();
    }

    public void send(String correctReceiver){
        messages.get(0).click();
        receiverInput.sendKeys(correctReceiver);
        sendButton.click();
    }
}
