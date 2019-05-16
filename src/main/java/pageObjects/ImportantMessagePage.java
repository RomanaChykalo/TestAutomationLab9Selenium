package pageObjects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class ImportantMessagePage extends BasePage {
    @FindBy(xpath = "//div[@class='AO']//div[@class='nH']/div[2]//tr/td[2]/div")
    private List<WebElement> checkboxes;
    @FindBy(css = "[gh] [act='10']")
    private WebElement deleteButton;
    @FindBy(css = "[gh] .Dj > .ts:nth-of-type(2)")
    private WebElement lettersAmount;
    private Logger logger = LogManager.getLogger(ImportantMessagePage.class);

    public void selectLettersInImportantFolder(int letterAmount) {
        /*String jsClickCode = "arguments[0].scrollIntoView(true); arguments[0].click();";
        for(int i=0;i<letterAmount;i++){
            ((JavascriptExecutor) driver).executeScript(jsClickCode, checkboxes.get(i));
        }*/
        /*checkboxes.stream().limit(3).forEach(checkboxes -> checkboxes.click());*/
        /*Wait wait = new FluentWait(driver)
                .withTimeout(50, TimeUnit.SECONDS)
                .pollingEvery(30, TimeUnit.SECONDS)
                .ignoring(org.openqa.selenium.StaleElementReferenceException.class);
        wait.until(ExpectedConditions.elementToBeClickable(checkboxes.get(letterAmount-1)));*/

        for (int i = 0; i < 3; i++) {
            checkboxes.get(i).click();
        }
    }

    public void deleteSelectedLetters() {
        if(deleteButton.isDisplayed()) {
            this.deleteButton.click();
            Wait wait = new FluentWait(driver);
            wait.until(ExpectedConditions.visibilityOfElementLocated((By.cssSelector("span.bAq"))));
        }
    }

    public String getLetterAmount() {
        try {
            return lettersAmount.getText();
        } catch (org.openqa.selenium.NoSuchElementException ex){
            return "0";
        }
    }
}
