package com.kryviak.pages.login;

import com.kryviak.decorator.ButtonDecor;
import com.kryviak.decorator.InputTextDecor;
import com.kryviak.pages.AbstractPage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.support.FindBy;

public class LoginEmailPage extends AbstractPage {

    private static Logger logger = LogManager.getLogger(LoginEmailPage.class);

    @FindBy(css = "#identifierId")
    private InputTextDecor userEmailTextFieldLocator;

    @FindBy(css = "#identifierNext span")
    private ButtonDecor nextButtonLocator;

    public LoginEmailPage setUserEmailTextField(String userEmail) {
        logger.info("Set user email: " + userEmail);
        userEmailTextFieldLocator.sendKeys(userEmail);
        return this;
    }

    public void clickNextButton() {
        logger.info("Click to the 'Next' on Email login page");
        nextButtonLocator.click();
    }
}
