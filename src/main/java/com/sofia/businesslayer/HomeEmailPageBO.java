package com.sofia.businesslayer;

import com.sofia.pageobjects.gmailpages.GmailHomePage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class HomeEmailPageBO {
    private static final Logger LOG = LogManager.getLogger(LoginBO.class);
    private GmailHomePage homePage = new GmailHomePage();

    public boolean deleteCheckedEmails(int checkboxAmount) {
        LOG.info("There are enough messages to delete");
        homePage.checkEmailsBoxes(checkboxAmount);
        homePage.clickDeleteButton();
        return !homePage.isCheckboxesListEmpty();
    }

    public String undoEmailDeletion() {
        homePage.clickUndoButton();
        return homePage.getUndoWidgetAttributeValue();
    }
}
