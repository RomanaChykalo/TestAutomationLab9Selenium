package pages.pagemodels;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pages.BasePage;

public class LoginPage extends BasePage {
    Logger logger = LogManager.getLogger(LoginPage.class);

    @FindBy(xpath = "//input[@type='email']")
    private WebElement emailInput;

    @FindBy(xpath = "//div[@id='identifierNext']//span")
    private WebElement loginNextBtn;

    @FindBy(xpath = "//h1/content")
    private WebElement helloField;

    @FindBy(xpath = "//input[@type='password']")
    private WebElement passwField;

    @FindBy(xpath = "//div[@id='passwordNext']//span")
    private WebElement passwNextBtn;

    public LoginPage() {
    }

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
        driver().navigate().to("https://mail.google.com");
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
        logger.trace("Typing password...");
        expWait(35).until(ExpectedConditions.visibilityOf(getPasswField()));
        getPasswField().sendKeys(passw);
    }

    public GmailPage clickPasswordNextBtn() {
        logger.trace("Clicking Next btn...");
        getPasswNextBtn().click();
        return new GmailPage();
    }
}
