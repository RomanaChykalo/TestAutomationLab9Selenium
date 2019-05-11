package com.igor.page;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class SentPage extends BasePage{
    @FindBy()
    private List<WebElement> sentLetters;

    public WebElement getLetter(int index){
        return sentLetters.get(index);
    }
}

