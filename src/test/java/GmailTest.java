import data.Consts;
import data.TestData;
import driver.DriverManager;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.businessobj.EmailBO;
import pages.businessobj.LoginBO;

public class GmailTest {

    @BeforeMethod
    public void setUp() {
        DriverManager.getDriver();
    }

    @Test(enabled = true, dataProvider = "recipientsMails", dataProviderClass = TestData.class)
    public void sendTestEmailsForUsers(String recipient, String subject, String mailBody){
        LoginBO loginBO = new LoginBO();
        EmailBO emailBO = new EmailBO(loginBO.login(Consts.EMAIL, Consts.PASSW));
        emailBO.sendEmail(recipient, subject, mailBody);
        Assert.assertTrue(emailBO.isViewEmailSentMsgBtnDisplayed());
    }

    @Test(enabled = true, dataProvider = "users", dataProviderClass = TestData.class, dependsOnMethods = {"sendTestEmailsForUsers"})
    public void delete3EmailsAndRevert(String login, String passw){
        LoginBO loginBO = new LoginBO();
        EmailBO emailBO = new EmailBO(loginBO.login(login, passw));
        emailBO.deleteUnreadEmail(Consts.COUNT_OF_EMAILS);
        Assert.assertTrue(emailBO.areEmailsDeleted());
        emailBO.revertDeletedEmails();
        Assert.assertTrue(emailBO.isActionCancelledMsgDispalyed());
    }

    @AfterMethod
    public void tearDown() {
        DriverManager.driverQuit();
    }

}
