package com.kryviak.decorator;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class InputTextDecor extends MyElement {

    private static Logger logger = LogManager.getLogger(InputTextDecor.class);

    InputTextDecor(WebElement webElement) {
        super(webElement);
    }

    public void sendKeys(String keysToSend) {
        logger.info("Input Text Area");
        webElement.sendKeys(keysToSend);
    }

    public boolean isDisplayed(){
        logger.info("Text Area is displayed");
        return webElement.isDisplayed();
    }
}
