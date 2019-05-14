package com.igor.page;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SentPage extends BasePage{
    @FindBy(xpath = "//div[@role='main']//tbody/tr[1]/td[6]//span[@class='bog']/span")
    private WebElement sentLetter;

    public String getLetter(){
        return sentLetter.getText();
    }
}

