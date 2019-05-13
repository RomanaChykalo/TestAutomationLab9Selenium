package pages;

import driver.DriverLoader;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import static model.Consts.LETTER_AMOUNT;

public class MainPage extends BasePage {
    @FindBy(css = "td.WA.xY")
    private List<WebElement> importantMarkButtons;
    @FindBy(css = "input.gb_He")
    private WebElement gmailSearchInput;
    @FindBy(css = "#aso_search_form_anchor > button.gb_Qe.gb_Re")
    private WebElement gmailSearchButton;

    public void markImportantMessages() {
        Wait wait = DriverLoader.getFluentWait()
                .withTimeout(10, TimeUnit.SECONDS)
                .pollingEvery(5, TimeUnit.SECONDS)
                .ignoring(org.openqa.selenium.StaleElementReferenceException.class);
        wait.until(ExpectedConditions.stalenessOf(importantMarkButtons.get(LETTER_AMOUNT)));
        importantMarkButtons.stream().limit(LETTER_AMOUNT).forEach(WebElement::click);
    }

    public void openImportantFolder() {
        gmailSearchInput.sendKeys("is:important ");
        gmailSearchButton.click();
        new WebDriverWait(driver, 10).until(ExpectedConditions.urlContains("imp"));
    }
}
