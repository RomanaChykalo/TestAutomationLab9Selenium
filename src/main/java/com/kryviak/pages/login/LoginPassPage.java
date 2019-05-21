package com.kryviak.pages.login;

import com.kryviak.decorator.ButtonDecor;
import com.kryviak.decorator.InputTextDecor;
import com.kryviak.pages.AbstractPage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.support.FindBy;

public class LoginPassPage extends AbstractPage {

    private static Logger logger = LogManager.getLogger(LoginPassPage.class);

    @FindBy(css = "input[type='password']")
    private InputTextDecor userPasswordTextFieldLocator;

    @FindBy(css = "#passwordNext span")
    private ButtonDecor nextButtonLocator;

    public LoginPassPage setUserPasswordTextField(String userPassword) {
        waitForElementVisible(userPasswordTextFieldLocator);
        logger.info("Set user password: " + userPassword);
        userPasswordTextFieldLocator.sendKeys(userPassword);
        return this;
    }

    public void clickNextButton() {
        waitForElementVisible(nextButtonLocator);
        logger.info("Click to the 'Next' button on Password login page");
        nextButtonLocator.click();
    }
}
