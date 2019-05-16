package com.igor.page.widget;

import com.igor.page.BasePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static com.igor.utils.constant.Constants.EXPLICIT_WAIT;

public class SendingMessageAlertDialogWidget extends BasePage {
    @FindBy(xpath = "//*[@role='alert']//*[@role='link' and @id='link_undo']")
    private WebElement undoButton;

    public void waitWhileMessageSending(){
        (new WebDriverWait(driver, EXPLICIT_WAIT)).until(ExpectedConditions.invisibilityOf(undoButton));
    }
}
