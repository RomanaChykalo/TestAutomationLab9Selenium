package businessObjects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pageObjects.ImportantMessagePage;
import pageObjects.MainPage;

import static utils.model.Consts.IMPORTANT_FOLDER_SEARCH_VALUE;

public class MessagesBO {
    private Logger logger = LogManager.getLogger(MessagesBO.class);
    private MainPage mainPage;
    private ImportantMessagePage importantMessagePage;

    public MessagesBO() {
        mainPage = new MainPage();
        importantMessagePage = new ImportantMessagePage();
    }

    public void markAsImportant(int letterAmount) {
        mainPage.markImportantMessages(letterAmount);
        logger.info("Messages from inbox folder are marked as important");
    }

    public boolean isMarkAsImportantLabelPresent() {
        logger.info("User mark letter as important and see label with text: "+mainPage.getActionDoneLabelText());
        return mainPage.isActionDoneLabelDisplayed();
    }

    public int openImportantFolder() {
        mainPage.openImportantFolder(IMPORTANT_FOLDER_SEARCH_VALUE);
        int letterAmountBeforeDeleting=checkLettersAmount();
        logger.info("There are: " + letterAmountBeforeDeleting + " messages in folder before deleting");
        return letterAmountBeforeDeleting;
    }

    private int checkLettersAmount() {
        return importantMessagePage.getLetterInFolderAmount();
    }

    public int deleteMessages(int letterAmount) {
        importantMessagePage.selectLettersInImportantFolder(letterAmount);
        logger.info(letterAmount + " messages are selected for deleting");
        importantMessagePage.deleteSelectedLetters();
        logger.info("Click \"Delete\" icon");
        int letterAmountAfterDeleting=checkLettersAmount();
        logger.info("There are: " + letterAmountAfterDeleting + " messages in folder after deleting");
        return letterAmountAfterDeleting;
    }

}