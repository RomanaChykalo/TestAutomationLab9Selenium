package page.objects;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

public class DraftMessagePage extends BasePage {

    @FindBy(css = "input.gb_He[name='q']")
    private WebElement searchInput;

    @FindBy(css = "button.gb_Qe.gb_Re")
    private WebElement goToDraftMessage;

    @FindBy(xpath = "//span[./span[contains(.,'Subject')]]")
    private List<WebElement> messages;

    @FindBy(xpath = "//textarea[@name='to']")
    private WebElement receiverInput;

    @FindBy(xpath = "//div[@class='T-I J-J5-Ji aoO v7 T-I-atl L3' and @role='button']")
    private WebElement sendButton;

    @FindBy(className = "bAq")
    private WebElement wasSent;

    public void goToDraftMessageAndCheck() {
        searchInput.sendKeys("in:draft");
        goToDraftMessage.click();
        System.out.println(messages.get(0).getText());
    }

    public void send(String correctReceiver) {
        messages.get(0).click();
        receiverInput.sendKeys(correctReceiver);
        sendButton.click();
        new FluentWait<>(driver).until(ExpectedConditions.visibilityOf(wasSent));
    }
}
