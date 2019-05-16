package businessObjects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pageObjects.LoginPage;

public class LoginBO {
    private LoginPage loginPage;
    private Logger logger = LogManager.getLogger(LoginBO.class);
    public LoginBO() {
        loginPage = new LoginPage();
    }

    public void enterEmailPassAndSubmit(String email, String password) {
        loginPage.typeEmailAndSubmit(email);
        loginPage.typePasswordAndSubmit(password);
        logger.info("User "+Thread.currentThread().getName()+" successfully log in");

    }
}
