package edu.pageobject.pagefactory;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class GmailLoginPage {

    private WebDriver driver;

    @FindBy(css = "input[type = 'email']")
    private WebElement userLogin;

    @FindBy(css = "div[role = 'button']")
    private WebElement buttonSubmitLogin;

    @FindBy(css = "input[type = password]")
    private WebElement userPassword;

    @FindBy(css = "div[id = 'passwordNext']")
    private WebElement buttonSubmitPassword;

    public GmailLoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void setUserLogin(String login) {
        userLogin.sendKeys(login);
    }

    public void submitLogin() {
        buttonSubmitLogin.click();
    }

    public void setPassword(String password) {
        (new WebDriverWait(driver, 10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[type = password]")));
        userPassword.sendKeys(password);
    }

    public void submitPassword() {
        buttonSubmitPassword.click();
    }
}
