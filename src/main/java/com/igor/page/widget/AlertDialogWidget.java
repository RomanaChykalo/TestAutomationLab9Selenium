package com.igor.page.widget;

import com.igor.decorator.element.Button;
import com.igor.page.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;

public class AlertDialogWidget extends BasePage {
    @FindBy(xpath = "//button[@name='ok']")
    private Button buttonOk;

    public boolean alertDialogIsEnable() {
        return driver.findElements(By.xpath("//div[@role='alertdialog']")).size() != 0;
    }

    public void clickToButtonOk() {
        buttonOk.click();
    }
}
