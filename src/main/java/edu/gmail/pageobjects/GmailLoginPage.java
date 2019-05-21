package edu.gmail.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static edu.gmail.utils.ParserJSON.getLoginProperty;
import static edu.gmail.utils.ParserJSON.getPasswordProperty;


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

    public void setUserLogin() {
        userLogin.sendKeys(getLoginProperty());
    }

    public void submitLogin() {
        buttonSubmitLogin.click();
    }

    public void setPassword() {
        (new WebDriverWait(driver, 10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[type = password]")));
        userPassword.sendKeys(getPasswordProperty());
    }

    public void submitPassword() {
        buttonSubmitPassword.click();
    }
}
