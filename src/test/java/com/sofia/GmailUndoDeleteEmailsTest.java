package com.sofia;

import com.sofia.utilmanager.driver.DriverManager;
import com.sofia.pageobjects.gmailpages.GmailHomePage;
import com.sofia.pageobjects.gmailpages.GmailSignInPageObj;
import com.sofia.utilmanager.property.Property;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Iterator;
import java.util.stream.Stream;

import static com.sofia.utilmanager.driver.DriverManager.quitDriver;
import static com.sofia.utilmanager.jsonparser.JsonParser.*;
import static org.testng.Assert.assertTrue;
import static org.testng.AssertJUnit.assertEquals;

public class GmailUndoDeleteEmailsTest {
    private static final Logger LOG = LogManager.getLogger(GmailUndoDeleteEmailsTest.class);
    private static final String UNDO_DELETE_EMAIL_WIDGET = getWidgetText();
    private static final int CHECKBOX_AMOUNT = 3;

    @BeforeMethod()
    public void setStartedPage(){
        DriverManager.getDriverInstance().get(Property.getProperty("login_page"));
    }

    @DataProvider(parallel = true)
    public Iterator<Object[]> users(){
        Object[][] objects = new Object[4][];
        for (int i = 0; i < 4; i++) {
            objects[i] = new Object[]{getUsername(i), getPassword(i)};
        }

        return Stream.of(objects).iterator();
    }

    @Test(dataProvider = "users")
    public void logInAndSendEmail(String testUsername, String testPassword) {
        GmailSignInPageObj loginPage = new GmailSignInPageObj();
        loginPage.typeUernameAndSubmit(testUsername);
        assertEquals(loginPage.getActiveUsernameAttributeValue(), testUsername);
        LOG.info("Username Correct");
        loginPage.typePasswordAndSubmit(testPassword);
        LOG.info("Log in successfully! ");

        GmailHomePage homePage = new GmailHomePage();
        assertTrue(!homePage.isCheckboxesListEmpty());
        LOG.info("There are enough messages to delete");
        homePage.checkEmailsBoxes(CHECKBOX_AMOUNT);
        homePage.clickDeleteButton();
        homePage.clickUndoButton();
        assertEquals(homePage.getUndoWidgetAttributeValue(), UNDO_DELETE_EMAIL_WIDGET);
        LOG.info("Test passed successfully");
    }

    @AfterMethod
    public void endTest() {
        quitDriver();
    }
}
