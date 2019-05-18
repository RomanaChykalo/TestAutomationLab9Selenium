package com.sofia.pageobjects.gmailpages;

import com.sofia.decorators.elements.Button;
import com.sofia.decorators.elements.Checkbox;
import com.sofia.pageobjects.GeneralGmailPage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class GmailHomePage extends GeneralGmailPage {
    @FindBy(xpath = "//div[@role='checkbox']")
    private List<Checkbox> emailCheckboxes;

    @FindBy(xpath = "(//div[@class='asa'])[3]")
    private Button deleteButton;

    @FindBy(id = "link_undo")
    private Button undoButton;

    @FindBy(className = "bAq")
    private WebElement undoWidget;

    public List<Checkbox> getCheckboxes(){
        return emailCheckboxes;
    }

    public boolean isCheckboxesListEmpty() {
        return getCheckboxes().isEmpty();
    }

    public void checkEmailsBoxes(int amount){
        for (int i = 0; i < amount; i++) {
            getCheckboxes().get(i).setChecked();
        }
    }

    public void clickDeleteButton() {
        (new WebDriverWait(driver, 30)).until(ExpectedConditions.elementToBeClickable(deleteButton.getButtonElement()));
        deleteButton.click();
    }

    public void clickUndoButton() {
        undoButton.click();
    }

    public String getUndoWidgetAttributeValue(){
        return undoWidget.getAttribute("innerHTML");
    }

}
