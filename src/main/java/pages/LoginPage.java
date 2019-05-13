package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {
    @FindBy(css = "input#identifierId")
    private WebElement userEmailInput;
    @FindBy(id = "identifierNext")
    private WebElement emailNextButton;
    @FindBy(xpath = "//div[@id='password']//input[@name='password']")
    private WebElement passwordInput;
    @FindBy (id = "passwordNext")
    private WebElement passNextButton;
    @FindBy(css = "ul li:nth-child(2) a")
    private WebElement signInButton;

    public void typeEmailAndSubmit(String userEmail){
        userEmailInput.sendKeys(userEmail);
        emailNextButton.click();
    }
    public void typePasswordAndSubmit(String userPassword){
        passwordInput.sendKeys(userPassword);
        passNextButton.click();
    }
    public void signIn(){
        signInButton.click();
    }

}
