import business.AutorizationBO;
import business.MarkAndDeleteImportantMessageBO;
import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;
import util.DriverManager;
import util.readers.UserData;

public class DeleteSomeImportantGmailMessage extends FunctionalTest {
    private static Logger LOG = Logger.getLogger(DeleteSomeImportantGmailMessage.class.getName());


    @Test(dataProvider = "user")
    public void chooseSomeImportantMessagesAndDeleteThem(UserData userData) throws InterruptedException {
        AutorizationBO autorizationBO = new AutorizationBO();
        MarkAndDeleteImportantMessageBO markAndDeleteImportantMessageBO = new MarkAndDeleteImportantMessageBO();

        autorizationBO.logInUser(userData.getUserEmail(), userData.getPassword());
        Assert.assertTrue(autorizationBO.verifyLogInSuccessful() , "User autorize successful");

        markAndDeleteImportantMessageBO.choseImportantMessages();
        Assert.assertTrue(markAndDeleteImportantMessageBO.messagesMarkedAsImportant(), "Messages is marked");
        markAndDeleteImportantMessageBO.deleteImportantMessages();
        Assert.assertTrue(markAndDeleteImportantMessageBO.messagesDeleted() , "Conversation moved to Trash.");
    }
}
