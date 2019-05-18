package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage extends BasePage {

    @FindBy(id = "identifierId")
    private WebElement emailField;

    @FindBy(xpath = "//span[@class='RveJvd snByac']")
    private WebElement nextButton;

    @FindBy(name = "password")
    private WebElement passwordField;

    public void typeUsername(String username) {
        emailField.sendKeys(username);
    }

    public void clickNextButton() {
               nextButton.click();
    }

    public void typePassword(String password) {
       new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(By.name("password")));
        passwordField.sendKeys(password);
    }
}


