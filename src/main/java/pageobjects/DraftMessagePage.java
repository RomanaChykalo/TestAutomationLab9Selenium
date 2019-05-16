package pageobjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;

import java.util.List;

public class DraftMessagePage extends BasePage {

    @FindBy(css = "input[name='q']")
    private WebElement searchInput;

    @FindBy(xpath = "//form[@role='search']/button[4]")
    private WebElement goToDraftMessage;

    @FindBy(xpath = "//span[./span[contains(.,'Subject')]]")
    private List<WebElement> messages;

    @FindBy(xpath = "//textarea[@name='to']")
    private WebElement receiverInput;

    @FindBy(xpath = "//div[@class='T-I J-J5-Ji aoO v7 T-I-atl L3' and @role='button']")
    private WebElement sendButton;

    @FindBy(className = "bAq")
    private WebElement wasSent;

    public void goToDraftMessage() {
        searchInput.clear();
        searchInput.sendKeys("in:draft");
        goToDraftMessage.click();
    }

    public void chooseCorrectMessage(){
        messages.get(0).click();
    }

    public void send() {
        sendButton.click();
        new FluentWait<>(driver).until(ExpectedConditions.visibilityOf(wasSent));
    }
}
