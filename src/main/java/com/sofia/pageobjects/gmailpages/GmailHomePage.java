package com.sofia.pageobjects.gmailpages;

import com.sofia.pageobjects.GeneralGmailPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.*;

import java.util.List;

import static com.sofia.utilmanager.property.Property.getProperty;

public class GmailHomePage extends GeneralGmailPage {
    private static final String GMAIL_HOME_PAGE = getProperty("home_email_page");
    @FindBy(xpath = "//div[@role='checkbox']")
    private List<WebElement> emailCheckboxes;

    @FindBy(className = "asa")
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
        deleteButton.click();
    }

    public void clickUndoButton() {
        undoButton.click();
    }

    public String getUndoWidgetAttributeValue(){
        return undoWidget.getAttribute("innerHTML");
    }

    public void navigateToLoginPage(WebDriver driver) {
        driver.navigate().to(GMAIL_HOME_PAGE);
    }

}
