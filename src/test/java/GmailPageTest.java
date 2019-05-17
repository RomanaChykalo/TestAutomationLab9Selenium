import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class GmailPageTest {

    private static final String DRIVER_NAME = "webdriver.chrome.driver";
    private static final String PATH_TO_CHROME_DRIVER = "src\\main\\resources\\chromedriver.exe";
    private static final String PATH_TO_PROPERTIES = "src\\main\\resources\\messagedata.xml";
    private static final String URL = "http://mail.google.com";
    private static final Logger logger = LogManager.getLogger(GmailPageTest.class);

    @BeforeMethod
    public void setUp() {
        System.setProperty(DRIVER_NAME, PATH_TO_CHROME_DRIVER);
    }

    @DataProvider(parallel = true)
    private  Object[][] loginData(){
        return DataProviderClass.getDataFromDataProvider();
        }

    @Test(dataProvider = "loginData")
    public void correctlySavedDataTest(String email, String password) {

        try (InputStream input = new FileInputStream(PATH_TO_PROPERTIES)) {
        WebDriver driver = DriverFactory.getInstance().getDriver();
        driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get(URL);
            Properties prop = new Properties();
            prop.loadFromXML(input);
            String receiverEmail = prop.getProperty("receiver_email");
            String subject = prop.getProperty("subject");
            String text = prop.getProperty("text");

            LoginPage loginPage = new LoginPage();
            WebDriverWait wait = new WebDriverWait(driver, 30);
            loginPage.typeUsername(email);
            loginPage.clickNextButton();
            wait.until(ExpectedConditions.elementToBeClickable(By.name(Locators.LOCATOR_FOR_PASSWORD_FIELD)));
            loginPage.typePassword(password);
            loginPage.clickNextButton();
            logger.info("Login was successful");

            MainPage mainPage = new MainPage();
            mainPage.clickComposeButton();
            mainPage.typeWhomField(receiverEmail);
            mainPage.typeSubjectField(subject);
            mainPage.typeTextField(text);
            mainPage.clickOnCloseMessageButton();
            logger.info("The draft was created");

            wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//a[contains(@href, 'drafts')]")));
            mainPage.clickOnDraftButton();
            mainPage.clickOnDraft();

            wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[@class='vN bfK a3q']")));
            String emailAddress = mainPage.takeEmailAddress();
            Assert.assertEquals(receiverEmail, emailAddress);
            logger.info("Saved email address equals to entered");

              String letterSubject = mainPage.takeLetterSubject();
            Assert.assertEquals(subject, letterSubject);
            logger.info("Saved subject of letter equals to entered");

            String letterText = mainPage.takeLetterText();
            Assert.assertEquals(text, letterText);
            logger.info("Saved text message equals to entered");

            mainPage.clickSendButton();
            wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(Locators.XPATH_TO_SENT_MESSAGE)));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @AfterMethod
    public void tearDown() {
       DriverFactory.getInstance().removeDriver();
    }
}