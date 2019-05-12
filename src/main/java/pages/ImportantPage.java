package pages;

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

    public ImportantPage(WebDriver driver) {
        super(driver);
    }

    public void selectLettersInImportantFolder() {
        String jsClickCode = "arguments[0].scrollIntoView(true); arguments[0].click();";
        List<WebElement> elementToClick = driver.findElements(By.cssSelector("td.oZ-x3"));
        ((JavascriptExecutor) driver).executeScript(jsClickCode, elementToClick.get(0));
        ((JavascriptExecutor) driver).executeScript(jsClickCode, elementToClick.get(1));
        ((JavascriptExecutor) driver).executeScript(jsClickCode, elementToClick.get(2));
    }

    public void deleteSelectedLetters() {
        this.deleteButton.click();
        new FluentWait(driver)
                .until(ExpectedConditions.visibilityOfElementLocated((By.cssSelector("span.bAq"))));
    }

    public String getLetterAmount() {
        return lettersAmount.getText();
    }
}
