package com.kryviak.decorator;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class LinkDecor extends MyElement{

    private static Logger logger = LogManager.getLogger(LinkDecor.class);

    LinkDecor(WebElement webElement) {
        super(webElement);
    }

    public void click() {
        logger.info("Link");
        webElement.click();
    }

    public boolean isDisplayed(){
        logger.info("Link is displayed");
        return webElement.isDisplayed();
    }

    public WebElement findElement(By mailTitleSelector) {
        return webElement;
    }
}
