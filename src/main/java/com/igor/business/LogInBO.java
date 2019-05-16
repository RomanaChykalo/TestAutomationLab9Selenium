package com.igor.business;

import com.igor.page.LogInPage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LogInBO {
    private static final Logger LOGGER = LogManager.getLogger(LogInBO.class);

    public void logIn(String username, String password){
        LogInPage logInPage = new LogInPage();
        LOGGER.info("Logging in");
        logInPage.setUsernameAndSubmit(username);
        logInPage.setPasswordAndSubmit(password);
    }
}
