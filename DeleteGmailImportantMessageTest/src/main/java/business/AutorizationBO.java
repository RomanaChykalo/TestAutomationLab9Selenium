package business;

import org.apache.log4j.Logger;
import org.testng.Assert;
import pom.LoginPage;

public class AutorizationBO {
    private LoginPage loginPage = new LoginPage();
    private static Logger LOG = Logger.getLogger(AutorizationBO.class.getName());

    public void logInUser(String email, String password) throws InterruptedException {
        loginPage.typeLoginAndSubmit(email);
        LOG.info("User entered in the Email text box");
        Assert.assertTrue(verifyUserEnterCorrectEmail() , "Password page");
        loginPage.typePasswordAndSubmit(password);
        LOG.info("Password entered in the Password text box");
    }

    public boolean verifyLogInSuccessful(){
       return loginPage.userLogInSuccessful();
    }

    public boolean verifyUserEnterCorrectEmail(){
        return loginPage.verifyUserEnterCorrectEmail();
    }




}
