import data.DataProvidersSet;
import data.TestData;
import driver.DriverManager;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.businessobj.EmailBO;
import pages.businessobj.LoginBO;
import pages.pagemodels.GmailPage;
import pages.pagemodels.LoginPage;
import utils.CSVParser;

public class GmailTest {

    @BeforeMethod
    public void setUp() {
        DriverManager.getDriver();
    }

    @Test(enabled = true, dataProvider = "recipientsTest", dataProviderClass = DataProvidersSet.class,  priority = 1)
    public void sendTestEmailsForUsers(String recipient, String subject, String mailBody){
        LoginBO loginManager = new LoginBO();
        EmailBO emailManager = new EmailBO(loginManager.login(TestData.EMAIL, TestData.PASSW));
        emailManager.sendEmail(recipient, subject, mailBody);
        Assert.assertTrue(emailManager.getViewEmailSentMsgBtn().isDisplayed());
    }

    @Test(enabled = false, dataProvider = "users", dataProviderClass = DataProvidersSet.class, priority = 2)
    public void delete3EmailsAndRevert(String login, String passw){
        LoginBO loginManager = new LoginBO();
        EmailBO emailManager = new EmailBO(loginManager.login(login, passw));
        emailManager.delete3UnreadEmailAndVerifyTheyAreReverted();
    }

    @Test
    public void test(){
        LoginPage loginPage = new LoginPage();
        loginPage.navigateToLoginPg();
        loginPage.setEmailField(TestData.EMAIL);
        loginPage.clickLoginNextBtn();
        loginPage.setPasswrd(TestData.PASSW);
        GmailPage gmailPage = loginPage.clickPasswordNextBtn();
        System.out.println(gmailPage.isPageLoaded());
    }

    @AfterMethod
    public void tearDown() {
        DriverManager.driverQuit();
    }

    public static void main(String[] args) {
        CSVParser.parseCSV("src/test/resources/testEmails.csv").stream().forEach((s)-> System.out.print(s));
    }
}
