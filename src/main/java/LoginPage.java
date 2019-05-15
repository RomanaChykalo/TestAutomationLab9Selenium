import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LoginPage extends BasePage {

    @FindBy(id = "identifierId")
    private WebElement emailField;

    @FindBy(xpath = "//span[@class='RveJvd snByac']")
    private WebElement nextButton;

    @FindBy(name = "password")
    private WebElement passwordField;

    public void typeUsername(String username) {
        emailField.sendKeys(username);
    }

    public void clickNextButton() {
        nextButton.click();
    }

    public void typePassword(String password) {
        webDriverWait.until(ExpectedConditions.elementToBeClickable(passwordField));
         passwordField.sendKeys(password);
    }
}


