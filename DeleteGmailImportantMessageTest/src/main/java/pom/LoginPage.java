package pom;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import util.DriverManager;
import util.PageObject;
import web_elements.Button;
import web_elements.TextInput;

public class LoginPage extends PageObject {

    private static final int EXPLICIT_WAIT_TIMEOUT = 7;
    private static final int PAGE_UPDATE_TIMEOUT = 10;

    @FindBy(xpath = "//*[@id='identifierId']")
    private TextInput loginInput;

    @FindBy(xpath = "//*[@id='identifierNext']")
    private Button nextButton;

    @FindBy(xpath = "//*[contains(@name,'password')]")
    private TextInput passwordInput;

    @FindBy(id = "passwordNext")
    private Button passwordNextButton;

    @FindBy(id = "forgotPassword")
    private WebElement forgotPasswordButton;

    @FindBy(xpath = "//*[contains(@class,'aio UKr6le')]//a[contains(@title,'Important')]")
    private WebElement importantButton;

    public LoginPage() {
        super();
    }

    public void typeLoginAndSubmit(String email) {
        loginInput.typeText(email);
        nextButton.clickButton();
        new WebDriverWait(DriverManager.getWebDriver(), EXPLICIT_WAIT_TIMEOUT)
                .until(ExpectedConditions.visibilityOf(forgotPasswordButton));
    }

    public void typePasswordAndSubmit(String password) {
        passwordInput.typeText(password);
        passwordNextButton.clickButton();
        waitPageUpdate();

    }

    public boolean verifyUserEnterCorrectEmail(){
        return forgotPasswordButton.isDisplayed();
    }

    public boolean userLogInSuccessful(){
        return importantButton.isDisplayed();
    }

    private void waitPageUpdate() {
        try {
            (new WebDriverWait(DriverManager.getWebDriver(), PAGE_UPDATE_TIMEOUT)).until(ExpectedConditions.urlContains("inbox"));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}
