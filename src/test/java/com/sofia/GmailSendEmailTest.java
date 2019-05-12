package com.sofia;

import com.sofia.drivermanager.DriverManager;
import com.sofia.pageobjects.GmailHomePage;
import com.sofia.pageobjects.GmailSignInPageObj;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterTest;
import org.testng.Assert.*;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.assertTrue;
import static org.testng.AssertJUnit.assertEquals;

public class GmailSendEmailTest {
    private static final Logger LOG = LogManager.getLogger(GmailSendEmailTest .class);
    private static final String TEST_USERNAME = "sonyachanter@gmail.com";
    private static final String TEST_PASSWORD = "sonichka13";
    private static final String UNDO_DELETE_EMAIL_WIDGET = "Дію скасовано.";

    private WebDriver driver = DriverManager.getInstance();

    @Test
    public void logInAndSendEmail() {
        GmailSignInPageObj loginPage = new GmailSignInPageObj(driver);
        loginPage.navigateToLoginPage(driver);
        loginPage.typeUernameAndSubmit(TEST_USERNAME);

        assertEquals(loginPage.getActiveUsernameAttributeValue(), TEST_USERNAME);
        LOG.info("Username Correct");
        loginPage.typePasswordAndSubmit(TEST_PASSWORD);
        LOG.info("Log in successfully! ");
        GmailHomePage homePage = new GmailHomePage(driver);
        List<WebElement> checkboxes = homePage.getCheckboxes();
        assertTrue(!checkboxes.isEmpty());
        LOG.info("There are enough messages to delete");
        for (int i = 0; i < 3; i++) {
            checkboxes.get(i).click();
        }
        homePage.clickDeleteButton();
        homePage.clickUndoButton();
        assertEquals(homePage.getUndoWidgetAttributeValue(), UNDO_DELETE_EMAIL_WIDGET);
        LOG.info("Test passed successfully");
    }

    @AfterTest
    public void endTest() {
        driver.quit();
    }
}
