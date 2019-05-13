import driver.DriverLoader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.ImportantPage;
import pages.LoginPage;
import pages.MainPage;

import static model.Consts.LETTER_AMOUNT;
import static model.Consts.PASS;
import static model.Consts.USER_MAIL;

public class GmailTest {
    private LoginPage loginPage;
    private MainPage mainPage;
    private ImportantPage importantPage;
    private Logger logger = LogManager.getLogger(GmailTest.class);

    public GmailTest() {
        loginPage = new LoginPage();
        mainPage = new MainPage();
        importantPage = new ImportantPage();
    }

    @Test
    public void generalTest() {
        //sign in gmail
        loginPage.signIn();
        loginPage.typeEmailAndSubmit(USER_MAIL);
        loginPage.typePasswordAndSubmit(PASS);
        //mark last 3 messages as important
        mainPage.markImportantMessages();
        //visit important letters folder
        mainPage.openImportantFolder();
        String messAmountBeforeDeleting = importantPage.getLetterAmount();
        logger.info("There are: " + messAmountBeforeDeleting + " messages in folder before deleting");
        importantPage.selectLettersInImportantFolder();
        logger.info(LETTER_AMOUNT + " Letters are selected for deleting");
        //delete last 3 letters from important folder
        importantPage.deleteSelectedLetters();
        String messAmountAfterDeleting = importantPage.getLetterAmount();
        logger.info("There are: " + messAmountAfterDeleting + " messages after deleting");
        Assert.assertNotEquals(messAmountBeforeDeleting, messAmountAfterDeleting);
        DriverLoader.tearDown();
    }

}
