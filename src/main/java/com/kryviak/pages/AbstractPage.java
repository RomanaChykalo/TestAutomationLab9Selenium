package com.kryviak.pages;

import com.kryviak.config.DriverThreadInit;
import com.kryviak.decorator.CustomFieldDecor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.DefaultElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class AbstractPage {

    private WebDriver webDriver = DriverThreadInit.getInstance().getDriver();

    public AbstractPage() {
        PageFactory.initElements(new CustomFieldDecor(new DefaultElementLocatorFactory(webDriver)), this);
    }

    protected void waitForElementVisible(WebElement webElement) {
        (new WebDriverWait(this.webDriver, 30)).until(ExpectedConditions.visibilityOf(webElement));
    }

    protected void waitForElementIsNotVisible(WebElement webElement) {
        (new WebDriverWait(this.webDriver, 30)).until(ExpectedConditions.invisibilityOf(webElement));
    }

    protected void checkIfDisplayedElement(WebElement webElement) {
        try {
            webElement.isDisplayed();
        } catch (StaleElementReferenceException ex) {
            ex.printStackTrace();
        }
    }
}
