import data.DataProvidersSet;
import data.TestData;
import driver.DriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.bo.EmailManager;
import pages.bo.LoginManager;
import utils.PropertyManager;

import java.util.concurrent.TimeUnit;

public class GmailTest extends DataProvidersSet {
    Logger logger = LogManager.getLogger(GmailTest.class);
    private WebDriver driver;

    @BeforeMethod
    public void setUp() {
        PropertyManager.setSystemWebDriverProperty();
        driver = DriverManager.getDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
    }

    @Test(dataProvider = "recipients", priority = 1)
    public void sendTestEmailsForUsers(String recipient, String subject, String mailBody){
        LoginManager loginManager = new LoginManager();
        EmailManager emailManager = new EmailManager(loginManager.login(TestData.EMAIL, TestData.PASSW));
        emailManager.sendEmail(recipient, subject, mailBody);
        Assert.assertTrue(emailManager.getViewEmailSentMsgBtn().isDisplayed());
    }

    @Test(dataProvider = "users", priority = 2)
    public void delete3EmailsAndRevert(String login, String passw){
        LoginManager loginManager = new LoginManager();
        EmailManager emailManager = new EmailManager(loginManager.login(login, passw));
        emailManager.delete3UnreadEmailAndVerifyTheyAreReverted();
    }

    @AfterMethod
    public void tearDown() {
        DriverManager.driverQuit();
    }
}
