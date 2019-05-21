package business;

import org.apache.log4j.Logger;
import org.testng.Assert;
import pom.LoginPage;

public class AutorizationBO {
    private LoginPage loginPage = new LoginPage();
    private static Logger LOG = Logger.getLogger(AutorizationBO.class.getName());

    public void logInUser(String email, String password) throws InterruptedException {
        LOG.info("User enter email" + email+ " in the login text box");
        loginPage.typeLoginAndSubmit(email);
        Assert.assertTrue(verifyUserEnterCorrectEmail() , "Password page");
        LOG.info("Password enter password" + password+ "in the Password text box");
        loginPage.typePasswordAndSubmit(password);

    }

    public boolean verifyLogInSuccessful(){
        return loginPage.userLogInSuccessful();
    }

    public boolean verifyUserEnterCorrectEmail(){
        return loginPage.verifyUserEnterCorrectEmail();
    }




}
