import data.DataProvidersSet;
import driver.DriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import pages.pagemodels.GmailPage;
import pages.pagemodels.LoginPage;
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

    @Test (dataProvider = "users")
    public void logInToGmail(String login, String passw) {
        LoginPage loginPage = new LoginPage();
        loginPage.navigateToLoginPg();
        loginPage.setEmailField(login);
        loginPage.clickLoginNextBtn();
        loginPage.setPasswrd(passw);
        GmailPage gmailPage = loginPage.clickPasswordNextBtn();
    }

    @AfterMethod
    public void tearDown() {
        DriverManager.driverQuit();
    }
}
