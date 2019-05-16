package com.igor.page;

import com.igor.page.widget.NewMessageWidget;
import com.igor.provider.DriverProvider;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static com.igor.utils.constant.Constants.EXPLICIT_WAIT;

public class MainPage extends BasePage{
    @FindBy(xpath = "//div[@role='complementary']/..//div[@role='button']")
    private WebElement composeButton;
    @FindBy(xpath = "//form[@role='search']//input")
    private WebElement searchField;
    @FindBy(xpath = "//form[@role='search']/button[4]")
    private WebElement searchButton;

    public NewMessageWidget clickToComposeButton(){
        composeButton.click();
        return new NewMessageWidget();
    }

    public void goToSentPage(){
        searchField.clear();
        searchField.sendKeys("in:sent");
        searchButton.click();
        (new WebDriverWait(driver, EXPLICIT_WAIT)).until(ExpectedConditions.urlContains("sent"));
        DriverProvider.pageLoadTimeout(EXPLICIT_WAIT);
    }
}
