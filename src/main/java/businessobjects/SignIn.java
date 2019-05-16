package businessobjects;

import pageobjects.LoginPage;

public class SignIn {

    private  LoginPage loginPage = new LoginPage();

    public void login(String login, String password){
        loginPage.inputLogin(login);
        loginPage.inputPassword(password);
    }
}
