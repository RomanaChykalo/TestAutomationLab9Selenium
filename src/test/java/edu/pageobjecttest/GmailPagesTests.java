package edu.pageobjecttest;

import edu.pageobject.pagefactory.GmailDraftForm;
import edu.pageobject.pagefactory.GmailLoginPage;
import edu.pageobject.pagefactory.GmailMailForm;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.json.simple.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

import java.util.Properties;

import static edu.pageobject.utils.DriverUtil.getDriver;
import static edu.pageobject.utils.ParserJSON.getJsonFile;
import static edu.pageobject.utils.PropertiesReader.getLocatorsFile;


public class GmailPagesTests {

    private static Logger logger = LogManager.getLogger("GmailPagesTests");
    private WebDriver driver = getDriver();

    @Test
    public void gmailDraftTest() {
        driver.get("http://mail.google.com");
        GmailLoginPage gmailLoginPageObject = new GmailLoginPage(driver);
        GmailMailForm gmailMailFormObject = new GmailMailForm(driver);
        GmailDraftForm gmailDraftFormObject = new GmailDraftForm(driver);
        JSONObject jsonFile = getJsonFile();
        Properties locatorsFile = getLocatorsFile();
        gmailLoginPageObject.setUserLogin((String) jsonFile.get("login"));
        gmailLoginPageObject.submitLogin();
        gmailLoginPageObject.setPassword((String) jsonFile.get("password"));
        gmailLoginPageObject.submitPassword();
        (new WebDriverWait(driver, 10)).until((dr) -> dr.getTitle().toLowerCase().startsWith("gmail"));
        gmailMailFormObject.composeEmail();
        int numberOfDraftMessages = Integer.valueOf(driver.findElement
                (By.xpath(locatorsFile.getProperty("numberOfDraftMessages"))).getText());
        String topic = jsonFile.get("subject") + String.valueOf(Math.random());
        gmailMailFormObject.setAddress((String) jsonFile.get("address"));
        gmailMailFormObject.setTopic(topic);
        gmailMailFormObject.writeMessage((String) jsonFile.get("message"));
        gmailMailFormObject.exitFromLetter();
        Integer expectedResult = numberOfDraftMessages + 1;
        (new WebDriverWait(driver, 15))
                .until(ExpectedConditions.invisibilityOfElementWithText
                        (By.xpath(locatorsFile.getProperty("numberOfDraftMessages")), String.valueOf(numberOfDraftMessages)));////div[@class = 'bsU']
        Assert.assertEquals(Integer.valueOf(driver.findElement
                (By.xpath(locatorsFile.getProperty("numberOfDraftMessages"))).getText()), expectedResult);
        gmailDraftFormObject.clickDraftSection();
        gmailDraftFormObject.clickDraftEmail(topic);
        gmailDraftFormObject.clickSendButton();
        (new WebDriverWait(driver, 30))
                .until(ExpectedConditions.invisibilityOfElementLocated
                        (By.xpath(locatorsFile.getProperty("successfulEmailSendingIndicator"))));
        logger.info("Email was sent successfully");
    }

    @AfterClass
    public void closeDriver() {
        driver.quit();
        logger.info("The driver has been closed");
    }
}
