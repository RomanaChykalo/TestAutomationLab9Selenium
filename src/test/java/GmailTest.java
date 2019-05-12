import driver.DriverLoader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.ImportantPage;
import pages.LoginPage;
import pages.MainPage;

import static model.Consts.PASS;
import static model.Consts.USER_MAIL;

public class GmailTest {
    LoginPage loginPage;
    MainPage mainPage;
    ImportantPage importantPage;
    WebDriver driver = DriverLoader.createDriverInstance();
    Logger logger = LogManager.getLogger(GmailTest.class);

    public GmailTest() {
        loginPage = new LoginPage(driver);
        mainPage = new MainPage(driver);
        importantPage = new ImportantPage(driver);
    }

    @Test
    public void generalTest() {
        loginPage.signIn();
        loginPage.typeEmailAndSubmit(USER_MAIL);
        loginPage.typePasswordAndSubmit(PASS);
        mainPage.markImportantMessages();

        mainPage.openImportantFolder();
        String messAmountBeforeDeleting = importantPage.getLetterAmount();
        logger.info("There are: " + messAmountBeforeDeleting + " messages in folder before deleting");

        importantPage.selectLettersInImportantFolder();
        logger.info("3 Letters are selected for deleting");

        importantPage.deleteSelectedLetters();
        String messAmountAfterDeleting = importantPage.getLetterAmount();
        logger.info("There are: " + messAmountAfterDeleting + " messages after deleting");
        Assert.assertNotEquals(messAmountBeforeDeleting, messAmountAfterDeleting);
        DriverLoader.tearDown();
    }

}
