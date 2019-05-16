import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class GmailPageTest {
    private WebDriver driver;
    private static final String DRIVER_NAME = "webdriver.chrome.driver";
    private static final String PATH_TO_CHROME_DRIVER = "src\\main\\resources\\chromedriver.exe";
    private static final String PATH_TO_PROPERTIES = "src\\main\\resources\\messagedata.xml";
    private static final String URL = "http://mail.google.com";
    private static final String INBOX_URL = "https://mail.google.com/mail/u/0/#inbox";
    private static final String DRAFTS_URL = "https://mail.google.com/mail/u/0/#drafts";
    private static final String XPATH_TO_LAST_SAVED_DRAFT ="//div[@class = 'ae4 UI']//tbody/tr[1]";
    private static final String XPATH_TO_WHOM_FIELD = "//div[@class = 'vT']";
    private static final String XPATH_TO_SENT_MESSAGE ="//div[@class = 'vh']";
    private static final Logger logger = LogManager.getLogger(GmailPageTest.class);

    @Before
    public void setUp() {
        System.setProperty(DRIVER_NAME, PATH_TO_CHROME_DRIVER);
        driver = DriverManager.getDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get(URL);
    }

    @Test
    public void correctlySavedDataTest() {
        try (InputStream input = new FileInputStream(PATH_TO_PROPERTIES)) {
            Properties prop = new Properties();
            prop.loadFromXML(input);
            String senderEmail = prop.getProperty("sender_email");
            String password = prop.getProperty("password");
            String receiverEmail = prop.getProperty("receiver_email");
            String subject = prop.getProperty("subject");
            String text = prop.getProperty("text");

            LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
            loginPage.typeUsername(senderEmail);
            loginPage.clickNextButton();
            loginPage.typePassword(password);
            loginPage.clickNextButton();
            logger.info("Login was successful");

            MainPage mainPage = PageFactory.initElements(driver, MainPage.class);
            mainPage.clickComposeButton();
            mainPage.typeWhomField(receiverEmail);
            mainPage.typeSubjectField(subject);
            mainPage.typeTextField(text);
            mainPage.clickOnCloseMessageButton();
            logger.info("The draft was created");

            WebDriverWait wait = new WebDriverWait(driver, 30);
            wait.until(ExpectedConditions.urlToBe(INBOX_URL));
            mainPage.clickOnDraftButton();
            wait.until(ExpectedConditions.urlToBe(DRAFTS_URL));

            String letterSubject = mainPage.takeLetterSubject();
            Assert.assertEquals(subject, letterSubject);
            logger.info("Saved subject of letter equals to entered");

            String letterText = mainPage.takeLetterText();
            Assert.assertEquals(" - \n" + text, letterText);
            logger.info("Saved text message equals to entered");

            mainPage.clickOnDraftButton();
            wait.until(ExpectedConditions.urlToBe(DRAFTS_URL));

            wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(XPATH_TO_LAST_SAVED_DRAFT)));
            mainPage.clickOnDraft();

            wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(XPATH_TO_WHOM_FIELD)));
            String emailAddress = mainPage.takeEmailAddress();
            Assert.assertEquals(receiverEmail, emailAddress);
            mainPage.clickSendButton();
            logger.info("Saved email address equals to entered");

            wait = new WebDriverWait(driver, 30);
            wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(XPATH_TO_SENT_MESSAGE)));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @After
    public void tearDown() {
        driver.close();
    }
}