import driver.DriverLoader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.ImportantMessagePage;
import pages.LoginPage;
import pages.MainPage;
import parser.CSVParser;

import java.io.File;
import java.util.Iterator;

import static model.Consts.CITE;
import static model.Consts.LETTER_AMOUNT;

public class GmailTest {
   /* private LoginPage loginPage;
    private MainPage mainPage;
    private ImportantMessagePage importantMessagePage;*/
    private Logger logger = LogManager.getLogger(GmailTest.class);

    @DataProvider(parallel = true)
    private Iterator<Object[]> loginDataCSV() {
        return CSVParser.parseCSVFile(new File("src/main/resources/usersData.csv")).iterator();
    }
    @Test(dataProvider = "loginDataCSV")
    public void generalTest(String email, String password) {
      LoginPage  loginPage = new LoginPage();
      //  loginPage.signIn();
        loginPage.typeEmailAndSubmit(email);
        logger.info("Current Thread ID: " +  Thread.currentThread().getId());
        loginPage.typePasswordAndSubmit(password);
        //mark last 3 messages as important
       MainPage mainPage = new MainPage();
        mainPage.markImportantMessages();
        //visit important letters folder
        mainPage.openImportantFolder();
       ImportantMessagePage importantMessagePage = new ImportantMessagePage();
        String messAmountBeforeDeleting = importantMessagePage.getLetterAmount();
        logger.info("There are: " + messAmountBeforeDeleting + " messages in folder before deleting");
       importantMessagePage.selectLettersInImportantFolder();
        logger.info(LETTER_AMOUNT + " Letters are selected for deleting");
        //delete last 3 letters from important folder
        importantMessagePage.deleteSelectedLetters();
        String messAmountAfterDeleting = importantMessagePage.getLetterAmount();
        logger.info("There are: " + messAmountAfterDeleting + " messages after deleting");
        Assert.assertNotEquals(messAmountBeforeDeleting, messAmountAfterDeleting);
    }
   @BeforeMethod()
  public void setUp(){
   DriverLoader.getDriver().get(CITE);
  }
    @AfterMethod
    public void quitDriver()
    {
        DriverLoader.tearDown();
    }
}
