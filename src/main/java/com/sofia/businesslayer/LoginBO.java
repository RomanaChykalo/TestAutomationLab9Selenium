package com.sofia.businesslayer;

import com.sofia.pageobjects.gmailpages.GmailSignInPageObj;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LoginBO {
    private static final Logger LOG = LogManager.getLogger(LoginBO.class);

    public String loginIntoAccount(String testUsername, String testPassword){
        GmailSignInPageObj loginPage = new GmailSignInPageObj();
        LOG.info(testUsername + " Making username input");
        loginPage.typeUernameAndSubmit(testUsername);
        LOG.info(testUsername + " Username Correct. Making password input");
        loginPage.typePasswordAndSubmit(testPassword);
        LOG.info(testUsername + " Log in successfully!");
        return loginPage.getActiveUsernameAttributeValue();
    }
}
