package com.kryviak.pages;

import com.kryviak.config.DriverThreadInit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class AbstractPage {
    protected WebDriver webDriver = DriverThreadInit.getInstance().getDriver();

    protected void waitForElementVisible(By by) {
        (new WebDriverWait(this.webDriver, 10)).until(ExpectedConditions.visibilityOfElementLocated(by));
    }

    protected void waitForElementIsNotVisible(By by) {
        (new WebDriverWait(this.webDriver, 10)).until(ExpectedConditions.invisibilityOfElementLocated(by));
    }
}