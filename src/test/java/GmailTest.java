import data.TestData;
import driver.DriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pagemodels.LoginPage;

import java.util.concurrent.TimeUnit;

public class GmailTest {
    Logger logger = LogManager.getLogger(GmailTest.class);
    private WebDriver driver;

    @BeforeTest
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/drivers/chromedriver.exe");
        driver = DriverManager.getDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
    }

    @Test
    public void deleteAndRevert3EmailsTest() throws InterruptedException {
        LoginPage loginPage = new LoginPage();

        loginPage.navigateToLoginPg();
        loginPage.setEmailField(TestData.EMAIL);
        loginPage.clickLoginNextBtn();
        loginPage.setPasswrd(TestData.PASSW);
        loginPage.clickPasswordNextBtn();
        Thread.sleep(350000);
    }

    @AfterTest
    public void tearDown() {
        DriverManager.driverQuit();
    }
}
