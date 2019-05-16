import businessObjects.LoginBO;
import businessObjects.MessagesBO;
import driver.DriverLoader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.*;
import parser.CSVParser;
import utils.PropertyUtils;

import java.io.File;
import java.util.Iterator;

public class GmailTest {
    private Logger logger = LogManager.getLogger(GmailTest.class);
    public final int LETTER_AMOUNT = 3;
    @DataProvider(parallel = true)
    private Iterator<Object[]> loginDataCSV() {
        return CSVParser.parseCSVFile(new File(PropertyUtils.configList.get("csv_path"))).iterator();
    }

    @Test(dataProvider = "loginDataCSV")
    public void generalTest(String email, String password) {
        LoginBO loginBO = new LoginBO();
        loginBO.enterEmailPassAndSubmit(email, password);
        MessagesBO messagesBO = new MessagesBO();
        messagesBO.markAsImportantAndNavigateToImpFolder(LETTER_AMOUNT);
        String lettersAmountBeforeDeleting = messagesBO.checkLettersAmount();
        logger.info("There are: " + lettersAmountBeforeDeleting + " messages in folder before deleting");
        messagesBO.deleteMessages(LETTER_AMOUNT);
        String lettersAmountAfterDeleting = messagesBO.checkLettersAmount();
        logger.info("There are: " + lettersAmountAfterDeleting + " messages in folder after deleting");
        Assert.assertNotEquals(lettersAmountBeforeDeleting, lettersAmountAfterDeleting);
    }

    @BeforeMethod()
    public void setUp() {
        DriverLoader.getDriver().get(PropertyUtils.configList.get("cite"));
    }

    @AfterMethod
    public void quitDriver() {
        DriverLoader.tearDown();
    }

    @BeforeTest
    public void logTestStart() {
        logger.info(" Test START");
    }

    @AfterTest
    public void logTestEnd() {
        logger.info("Test END ");
    }
}

