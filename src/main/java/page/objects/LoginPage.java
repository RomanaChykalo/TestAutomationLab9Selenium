package page.objects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {

    @FindBy(id = "identifierId")
    private WebElement loginInput;

    @FindBy(id = "identifierNext")
    private WebElement buttonSubmitLogin;

    @FindBy(name = "password")
    private WebElement passwordInput;

    @FindBy(xpath = "//div[@id='passwordNext']")
    private WebElement buttonSubmitPassword;

    public void loginAndSubmit(String login, String password){
        loginInput.sendKeys(login);
        buttonSubmitLogin.click();
        passwordInput.sendKeys(password);
        buttonSubmitPassword.click();
    }

}
