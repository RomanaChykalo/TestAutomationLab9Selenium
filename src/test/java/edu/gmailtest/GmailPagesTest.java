package edu.gmailtest;

import edu.gmail.bussinessobjects.GmailDraftMessageBO;
import edu.gmail.bussinessobjects.GmailLoginPageBO;
import edu.gmail.utils.DriverWrapper;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

import static edu.gmail.utils.Constants.ONE;
import static edu.gmail.utils.DriverWrapper.getInstanceOfDriverWrapper;


public class GmailPagesTest {

    private Logger logger = LogManager.getLogger("GmailPagesTest");
    private DriverWrapper driverWrapperInstance = getInstanceOfDriverWrapper();

    @Test
    public void gmailDraftTest() {
        GmailLoginPageBO loginPageBO = new GmailLoginPageBO();
        GmailDraftMessageBO draftMessageBO = new GmailDraftMessageBO();
        loginPageBO.loginToGmail();
        logger.info("Logged in successfully");
        draftMessageBO.composeDraftLetterAndExit();
        logger.info("Successfully composed drafted letter and exited");
        Integer expectedResult = draftMessageBO.getNumberOfDraftMessagesBeforeAddingNewDraftLetter() + ONE;
        Assert.assertEquals(Integer.valueOf(draftMessageBO.getNumberOfDraftMessagesAfterAddingNewDraftLetter()), expectedResult);
        logger.info("Successfully added letter to drafted section");
        draftMessageBO.openDraftMessagesAndSendDraftedLetter();
        logger.info("Email was sent successfully");
    }

    @AfterClass
    public void closeDriver() {
        final WebDriver driver = driverWrapperInstance.getDriver();
        driver.quit();
        logger.info("The driver has been closed");
    }
}
