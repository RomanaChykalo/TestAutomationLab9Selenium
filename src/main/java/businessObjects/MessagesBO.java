package businessObjects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import pageObjects.ImportantMessagePage;
import pageObjects.MainPage;

public class MessagesBO {
    private Logger logger = LogManager.getLogger(MessagesBO.class);
    private MainPage mainPage;
    private ImportantMessagePage importantMessagePage;
    public MessagesBO(){
        mainPage = new MainPage();
        importantMessagePage = new ImportantMessagePage();
    }
    public void markAsImportantAndNavigateToImpFolder(int letterAmount){
        mainPage.markImportantMessages(letterAmount);
        mainPage.openImportantFolder();
    }
    public String checkLettersAmount(){
       return importantMessagePage.getLetterAmount();
    }
    public void deleteMessages(int letterAmount){
        importantMessagePage.selectLettersInImportantFolder(letterAmount);
        importantMessagePage.deleteSelectedLetters();
    }
}
