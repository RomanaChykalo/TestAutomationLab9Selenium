package com.kryviak.config;

import com.kryviak.utils.PropertyParser;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class DriverManager {
    private static DriverManager driverManagerInstance = new DriverManager();
    private WebDriver webDriver = null;
    private PropertyParser propertyParser = new PropertyParser();

    private DriverManager() { }

    public static DriverManager getInstance() {
        if(driverManagerInstance == null) {
            driverManagerInstance = new DriverManager();
        }
        return driverManagerInstance;
    }

    public WebDriver getWebDriver() {
        if (webDriver == null) {
            webDriver = new ChromeDriver();
            System.setProperty(propertyParser.getSysDriverProp(), propertyParser.getDriverPath());
            webDriver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
            webDriver.manage().window().maximize();
            webDriver.get(propertyParser.getDriverUrl());
        }
            return webDriver;
    }

    public void waitForElementVisible(By by) {
        (new WebDriverWait(DriverManager.getInstance().getWebDriver(), 5))
                .until(ExpectedConditions.visibilityOfElementLocated(by));
    }

    public void waitForElementIsNotVisible(By by) {
        (new WebDriverWait(DriverManager.getInstance().getWebDriver(), 5))
                .until(ExpectedConditions.invisibilityOfElementLocated(by));
    }

    public void closeWebDriver() {
        webDriver.quit();
    }
}