package com.igor.page.widget;

import com.igor.page.BasePage;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AlertDialogWidget extends BasePage {
    @FindBy(xpath = "//button[@name='ok']")
    private WebElement buttonOk;

    public boolean alertDialogIsEnable() {
        try {
            buttonOk.getText();
        } catch (NoSuchElementException e) {
            return false;
        }
        return true;
    }
    public void clickToButtonOk() {
        buttonOk.click();
    }
}
