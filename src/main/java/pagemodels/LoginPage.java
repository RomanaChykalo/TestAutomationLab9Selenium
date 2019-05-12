package pagemodels;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage{
    Logger logger = LogManager.getLogger(LoginPage.class);

    @FindBy(xpath = "//input[@type='email']")
    private WebElement emailInput;

    @FindBy(xpath = "//content/span[contains(text(),'Далі')]")
    private WebElement loginNextBtn;

    @FindBy(xpath = "//h1/content")
    private WebElement helloField;

    @FindBy(css = "input[type=\"password\"]")
    private WebElement passwField;

    @FindBy(xpath = "//content/span[contains(text(),'Далі')]")
    private WebElement passwNextBtn;

    public WebElement getEmailInput() {
        return emailInput;
    }

    public WebElement getLoginNextBtn() {
        return loginNextBtn;
    }

    public WebElement getHelloField() {
        return helloField;
    }

    public WebElement getPasswField() {
        return passwField;
    }

    public WebElement getPasswNextBtn() {
        return passwNextBtn;
    }

    public void navigateToLoginPg(){
        logger.trace("Navigating to 'https://mail.google.com'...");
        getDriver().navigate().to("https://mail.google.com");
    }

    public void setEmailField(String email){
        logger.trace("Typing email...");
        getEmailInput().sendKeys(email);
    }

    public void clickLoginNextBtn(){
        logger.trace("Clicking Next btn...");
        getLoginNextBtn().click();
    }

    public void setPasswrd(String passw){
        getPasswField().sendKeys(passw);
    }

    public void clickPasswordNextBtn() {
        logger.trace("Clicking Next btn...");
        getPasswNextBtn().click();
    }
}
