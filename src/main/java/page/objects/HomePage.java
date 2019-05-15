package page.objects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage {

    @FindBy(xpath = "//div[@class='T-I J-J5-Ji T-I-KE L3' and @role='button']")
    private WebElement buttonToWriteMessage;

    public void goToWriteMessage() {
        buttonToWriteMessage.click();

    }


}
