package com.kryviak.pages.login;

import com.kryviak.config.DriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginEmailPage {
    private static Logger logger = LogManager.getLogger(LoginEmailPage.class);

    private WebDriver webDriver = DriverManager.getInstance().getWebDriver();

    private By userPasswordTextFieldLocator = By.cssSelector("#identifierId");
    private By nextButtonLocator = By.cssSelector("#identifierNext span");

    public LoginEmailPage setUserEmailTextField(String userEmail) {
        DriverManager.getInstance().waitForElementVisible(userPasswordTextFieldLocator);
        webDriver.findElement(userPasswordTextFieldLocator).sendKeys(userEmail);
        logger.info("User email is: " + userEmail);
        return this;
    }

    public void clickNextButton() {
        DriverManager.getInstance().waitForElementVisible(nextButtonLocator);
        webDriver.findElement(nextButtonLocator).click();
        logger.info("Click to the 'Next' button on Email login page");
    }
}
