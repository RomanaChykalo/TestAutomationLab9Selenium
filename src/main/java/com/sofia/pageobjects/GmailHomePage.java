package com.sofia.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.*;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class GmailHomePage {
    private static final String GMAIL_HOME_PAGE = "https://mail.google.com/mail/u/0/#inbox";

    @FindBy(xpath = "//div[@role='checkbox']")
    private List<WebElement> emailCheckboxes;

    @FindBy(className = "asa")
    private WebElement deleteButton;

    @FindBy(id = "link_undo")
    private WebElement undoButton;

    @FindBy(className = "bAq")
    private WebElement undoWidget;

    public GmailHomePage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public List<WebElement> getCheckboxes(){
        return emailCheckboxes;
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
