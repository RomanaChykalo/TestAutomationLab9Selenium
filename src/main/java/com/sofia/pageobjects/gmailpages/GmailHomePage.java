package com.sofia.pageobjects.gmailpages;

import com.sofia.pageobjects.GeneralGmailPage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class GmailHomePage extends GeneralGmailPage {
    @FindBy(xpath = "//div[@role='checkbox']")
    private List<WebElement> emailCheckboxes;

    @FindBy(xpath = "(//div[@class='asa'])[3]")
    private WebElement deleteButton;

    @FindBy(id = "link_undo")
    private WebElement undoButton;

    @FindBy(className = "bAq")
    private WebElement undoWidget;

    public List<WebElement> getCheckboxes(){
        return emailCheckboxes;
    }

    public boolean isCheckboxesListEmpty() {
        return getCheckboxes().isEmpty();
    }

    public void checkEmailsBoxes(int amount){
        for (int i = 0; i < amount; i++) {
            getCheckboxes().get(i).click();
        }
    }

    public void clickDeleteButton() {
        (new WebDriverWait(driver, 30)).until(ExpectedConditions.elementToBeClickable(deleteButton));
        deleteButton.click();
    }

    public void clickUndoButton() {
        undoButton.click();
    }

    public String getUndoWidgetAttributeValue(){
        return undoWidget.getAttribute("innerHTML");
    }

}
