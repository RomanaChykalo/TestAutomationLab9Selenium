import business.LoginPageBO;
import business.MainPageBO;
import dataproviders.DataProviderClass;
import dataproviders.MessageProperties;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.testng.annotations.*;
import driver.DriverFactory;
import static constants.Constants.*;

public class GmailPageTest {
    private static final Logger logger = LogManager.getLogger(GmailPageTest.class);

    @BeforeMethod
    public void setUp() {
        System.setProperty(DRIVER_NAME, PATH_TO_CHROME_DRIVER);
        DriverFactory.getWebDriver().get(URL);
    }

    @DataProvider(parallel = true)
    private Object[][] loginData() {
        return DataProviderClass.getDataFromDataProvider();
    }

    @Test(dataProvider = "loginData")
    public void correctlySavedDataTest(String email, String password) {
        String receiverEmail = MessageProperties.getProperties().getProperty("receiver_email");
        String subject = MessageProperties.getProperties().getProperty("subject");
        String text = MessageProperties.getProperties().getProperty("text");
        LoginPageBO loginBO = new LoginPageBO();
        MainPageBO mainPageBO = new MainPageBO();
        loginBO.login(email, password);
        mainPageBO.composeEmail(receiverEmail, subject, text);
        mainPageBO.openSavedDraft();
        Assert.assertTrue(mainPageBO.isSavedEmailInDraftEqualsToEntered(receiverEmail));
        logger.info("Saved email address equals to entered");
        Assert.assertTrue(mainPageBO.isSavedSubjectEqualsToEntered(subject));
        logger.info("Saved subject of letter equals to entered");
        Assert.assertTrue(mainPageBO.isSavedLetterTextEqualsToEntered(text));
        logger.info("Saved text message equals to entered");
        mainPageBO.sendLetter();
    }

    @AfterMethod
    public void tearDown() {
        DriverFactory.removeDriver();
    }
}