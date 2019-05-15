package com.kryviak.pages.login;

import com.kryviak.pages.AbstractPage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;

public class LoginEmailPage extends AbstractPage {
    private static Logger logger = LogManager.getLogger(LoginEmailPage.class);

    private By userPasswordTextFieldLocator = By.cssSelector("#identifierId");
    private By nextButtonLocator = By.cssSelector("#identifierNext span");

    public LoginEmailPage setUserEmailTextField(String userEmail) {
        waitForElementVisible(userPasswordTextFieldLocator);
        webDriver.findElement(userPasswordTextFieldLocator).sendKeys(userEmail);
        logger.info("User email is: " + userEmail);
        return this;
    }

    public void clickNextButton() {
        waitForElementVisible(nextButtonLocator);
        webDriver.findElement(nextButtonLocator).click();
        logger.info("Click to the 'Next' button on Email login page");
    }
}
