package po;

import decorator.elements.Button;
import decorator.elements.Input;
import decorator.elements.Span;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class DraftMessagePage extends BasePage {

    @FindBy(css = "input[name='q']")
    private Input searchInput;

    @FindBy(xpath = "//form[@role='search']/button[4]")
    private Button goToDraftMessage;

    @FindBy(xpath = "//span[./span[contains(.,'Subject')]]")
    private List<Span> messages;

    @FindBy(xpath = "//textarea[@name='to']")
    private Input receiverInput;

    @FindBy(xpath = "//div[@class='T-I J-J5-Ji aoO v7 T-I-atl L3' and @role='button']")
    private Button sendButton;

    public void goToDraftMessage() {
        searchInput.clearAndSendKeys("in:draft");
        goToDraftMessage.click();
    }

    public void chooseLastMessage() {
        messages.get(0).click();
    }


    public void sendMessage() {
        sendButton.click();
    }
}
