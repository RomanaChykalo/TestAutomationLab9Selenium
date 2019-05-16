package pageObjects;

import driver.DriverLoader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class MainPage extends BasePage {
    @FindBy(xpath = "//span[@aria-label and @role='button']//following::td[@jscontroller]//div[@role]")
    private List<WebElement> importantMarkButtons;
    @FindBy(xpath = "//form[@role='search']//input")
    private WebElement gmailSearchInput;
    @FindBy(xpath = "//button[@aria-label and following-sibling::*[position()=1][name()='table']]")
    private WebElement gmailSearchButton;
    private Logger logger = LogManager.getLogger(MainPage.class);

    public void markImportantMessages(int letterAmount) {

        if (importantMarkButtons.size()>letterAmount) {
            try {
                Wait wait = new FluentWait(driver)
                        .withTimeout(50, TimeUnit.SECONDS)
                        .pollingEvery(30, TimeUnit.SECONDS)
                        .ignoring(org.openqa.selenium.StaleElementReferenceException.class);
                wait.until(ExpectedConditions.stalenessOf(importantMarkButtons.get(letterAmount-1)));
                importantMarkButtons.stream().limit(letterAmount).forEach(WebElement::click);
            } catch (org.openqa.selenium.StaleElementReferenceException ex){
                importantMarkButtons.stream().limit(letterAmount).forEach(WebElement::click);
            }

        } else {
            logger.info("We can't continue test, because we haven't enough messages for deleting");

        }
    }

    public void openImportantFolder() {
        gmailSearchInput.sendKeys("is:important ");
        gmailSearchButton.click();
        new WebDriverWait(driver, 10).until(ExpectedConditions.urlContains("imp"));
        logger.info("Navigated to: "+driver.getCurrentUrl());
    }
}
