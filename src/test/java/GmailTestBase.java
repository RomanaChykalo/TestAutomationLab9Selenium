import data.DataProvidersSet;
import data.TestData;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pagemodels.GmailPage;
import pagemodels.LoginPage;

public class GmailTestBase extends DataProvidersSet {

    @BeforeClass
    @Test(dataProvider = "recipients")
    public void sendTestEmailsForUsers(String recipient, String subject, String mailBody){
        LoginPage loginPage = new LoginPage();
        loginPage.navigateToLoginPg();
        loginPage.setEmailField(TestData.EMAIL);
        loginPage.clickLoginNextBtn();
        loginPage.setPasswrd(TestData.PASSW);
        GmailPage gmailPage = loginPage.clickPasswordNextBtn();

        gmailPage.clickWriteNewEmailBtn();
        gmailPage.setReceiver(recipient);
        gmailPage.setEmailSubject(subject);
        gmailPage.setEmailBody(mailBody);
        gmailPage.clickSendBtn();
    }
}
