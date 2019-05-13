package com.igor.page;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MainPage extends BasePage{
    @FindBy(xpath = "//div[@role='complementary']/..//div[@role='button']")
    private WebElement composeButton;
    @FindBy(xpath = "//form[@role='search']//input")
    private WebElement searchField;
    @FindBy(xpath = "//form[@role='search']/button[4]")
    private WebElement searchButton;

    public NewMessagePage clickToComposeButton(){
        composeButton.click();
        return new NewMessagePage();
    }

    public SentPage goToSentPage(){
        searchField.clear();
        searchField.sendKeys("in:sent");
        searchButton.click();
        (new WebDriverWait(driver, 15)).until(ExpectedConditions.urlContains("sent"));
        return new SentPage();
    }
}
