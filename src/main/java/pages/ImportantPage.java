package pages;

import driver.DriverLoader;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;

import java.util.List;

public class ImportantPage extends BasePage {
    @FindBy(css = "td.oZ-x3")
    private List<WebElement> checkboxes;
    @FindBy(css = "[gh] [act='10']")
    private WebElement deleteButton;
    @FindBy(css = "[gh] .Dj > .ts:nth-of-type(2)")
    private WebElement lettersAmount;

    public void selectLettersInImportantFolder() {
        String jsClickCode = "arguments[0].scrollIntoView(true); arguments[0].click();";
        for(int i=0;i<3;i++){
            ((JavascriptExecutor) driver).executeScript(jsClickCode, checkboxes.get(i));
        }
    }

    public void deleteSelectedLetters() {
        this.deleteButton.click();
        DriverLoader.getFluentWait()
                .until(ExpectedConditions.visibilityOfElementLocated((By.cssSelector("span.bAq"))));
    }

    public String getLetterAmount() {
        return lettersAmount.getText();
    }
}
