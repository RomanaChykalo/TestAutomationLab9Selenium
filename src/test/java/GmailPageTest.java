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
    private MainPage mainPage;
    private LoginPage loginPage;
    private String user;
    private String password;
    private String customerEmail;
    private static final String DRIVER_NAME = "webdriver.chrome.driver";
    public static final String PATH_TO_CHROME_DRIVER = "src\\main\\resources\\chromedriver.exe";
    public static final String PATH_TO_PROPERTIES = "src\\main\\resources\\user.properties";
    public static final String URL = "http://mail.google.com";
    private static final Logger logger = LogManager.getLogger(GmailPageTest.class);

    @Before
    public void setUp() {
        System.setProperty(DRIVER_NAME, PATH_TO_CHROME_DRIVER);
        driver = DriverManager.getDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get(URL);
        mainPage = PageFactory.initElements(driver, MainPage.class);
        loginPage = PageFactory.initElements(driver, LoginPage.class);
        try (InputStream input = new FileInputStream(PATH_TO_PROPERTIES)) {
            Properties prop = new Properties();
            prop.load(input);
            user = prop.getProperty("email");
            password = prop.getProperty("password");
            customerEmail = prop.getProperty("customer_email");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void loginTest() {
        loginPage.typeUsername(user);
        loginPage.clickNextButton();
        loginPage.typePassword(password);
        loginPage.clickNextButton();
        mainPage.clickWriteButton();
        mainPage.typeWhomField(customerEmail);
        mainPage.typeSubjectField("Тест");
        mainPage.typeTextField("Привіт друже");
        mainPage.clickOnCloseMessageButton();
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.urlToBe("https://mail.google.com/mail/u/0/#inbox"));
        mainPage.clickOnDraftButton();
        wait.until(ExpectedConditions.urlToBe("https://mail.google.com/mail/u/0/#drafts"));

        String foundSubject = mainPage.foundSubjectOfMessage();
        Assert.assertEquals("Тест", foundSubject);

        String foundMessage = mainPage.foundTextMessage();
        Assert.assertEquals(" - \nПривіт друже", foundMessage);

        mainPage.clickOnDraftButton();
        wait.until(ExpectedConditions.urlToBe("https://mail.google.com/mail/u/0/#drafts"));

        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class = 'ae4 UI']//tbody/tr[2]")));
        mainPage.clickOnMessage();

        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class = 'vT']")));
        String foundEmailAddress = mainPage.foundEmailAddress();

        Assert.assertEquals("stepankish@gmail.com", foundEmailAddress);
        mainPage.clickSendButton();

        wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@class = 'vh']")));
    }

    @After
    public void tearDown() {
        driver.close();
    }
}