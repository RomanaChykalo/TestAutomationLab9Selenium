package pages.businessobj;

import pages.pagemodels.GmailPage;
import pages.pagemodels.LoginPage;

public class LoginBO {
    private LoginPage loginPage;
    private GmailPage gmailPage;

    public LoginBO() {
    }

    public LoginPage getLoginPage() {
        return loginPage;
    }

    public GmailPage getGmailPage() {
        return gmailPage;
    }

    public GmailPage login(String login, String passw){
        loginPage = new LoginPage();
        loginPage.navigateToLoginPg();
        loginPage.setEmailField(login);
        loginPage.clickLoginNextBtn();
        loginPage.setPasswrd(passw);
        gmailPage = loginPage.clickPasswordNextBtn();
        return gmailPage;
    }

}
