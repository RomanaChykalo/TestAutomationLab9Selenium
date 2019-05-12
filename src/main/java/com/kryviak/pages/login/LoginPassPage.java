package com.kryviak.pages.login;

import com.kryviak.config.DriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPassPage {
    private static Logger logger = LogManager.getLogger(LoginEmailPage.class);

    private WebDriver webDriver = DriverManager.getInstance().getWebDriver();

    private By userPasswordTextFieldLocator = By.cssSelector("input[type='password']");
    private By nextButtonLocator = By.cssSelector("#passwordNext span");

    public LoginPassPage setUserPasswordTextField(String userPassword) {
        DriverManager.getInstance().waitForElementVisible(userPasswordTextFieldLocator);
        webDriver.findElement(userPasswordTextFieldLocator).sendKeys(userPassword);
        logger.info("User password is: " + userPassword);
        return this;
    }

    public void clickNextButton() {
        DriverManager.getInstance().waitForElementVisible(nextButtonLocator);
        webDriver.findElement(nextButtonLocator).click();
        logger.info("Click to the 'Next' button on Password login page");
    }
}