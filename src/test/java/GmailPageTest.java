import business.LoginPageBO;
import business.MainPageBO;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.testng.annotations.*;
import pages.DriverFactory;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class GmailPageTest {
    private static final String DRIVER_NAME = "webdriver.chrome.driver";
    private static final String PATH_TO_CHROME_DRIVER = "src\\main\\resources\\chromedriver.exe";
    private static final String PATH_TO_PROPERTIES = "src\\main\\resources\\messagedata.xml";
    private static final String URL = "http://mail.google.com";
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
        LoginPageBO loginBO = new LoginPageBO();
        MainPageBO mainPageBO = new MainPageBO();

        try (InputStream input = new FileInputStream(PATH_TO_PROPERTIES)) {
            Properties prop = new Properties();
            prop.loadFromXML(input);
            String receiverEmail = prop.getProperty("receiver_email");
            String subject = prop.getProperty("subject");
            String text = prop.getProperty("text");

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
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @AfterMethod
    public void tearDown() {
        DriverFactory.removeDriver();
    }
}