import driver.DriverManager;
import json.Data;
import json.Parser;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;
import page.objects.DraftMessagePage;
import page.objects.HomePage;
import page.objects.LoginPage;
import page.objects.WriteMessage;
import properties.FilePath;

import java.io.File;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;

public class TestGmail {

    private static FilePath filePath = new FilePath();
    private static File json = new File(filePath.propertyFile("fileWayJSON"));
    private static Parser parser = new Parser();
    private static List<Data> users = parser.getData(json);

    @DataProvider(parallel = true)
    public Iterator<Object[]> getUsers() {
        return Stream.of(
                new Object[]{users.get(0).getLogin(), users.get(0).getPassword(), users.get(0).getMessage(),
                        users.get(0).getSubject(), users.get(0).getWrongReceiver(), users.get(0).getCorrectReceiver()},
                new Object[]{users.get(1).getLogin(), users.get(1).getPassword(), users.get(1).getMessage(),
                        users.get(1).getSubject(), users.get(1).getWrongReceiver(), users.get(1).getCorrectReceiver()},
                new Object[]{users.get(2).getLogin(), users.get(2).getPassword(), users.get(2).getMessage(),
                       users.get(2).getSubject(), users.get(2).getWrongReceiver(), users.get(2).getCorrectReceiver()},
                new Object[]{users.get(3).getLogin(), users.get(3).getPassword(), users.get(3).getMessage(),
                        users.get(3).getSubject(), users.get(3).getWrongReceiver(), users.get(3).getCorrectReceiver()},
                new Object[]{users.get(4).getLogin(), users.get(4).getPassword(), users.get(4).getMessage(),
                        users.get(4).getSubject(), users.get(4).getWrongReceiver(), users.get(4).getCorrectReceiver()}).iterator();
    }

    @Test(dataProvider = "getUsers")
    public void LoginTest(String login, String password,String message, String subject, String wrongReceiver, String correctReceiver) {
        LoginPage loginPage = new LoginPage();
        loginPage.loginAndSubmit(login, password);

        HomePage homePage = new HomePage();
        homePage.goToWriteMessage();

        WriteMessage writeMessage = new WriteMessage();
        writeMessage.toWriteMessage(message, wrongReceiver, subject);
        writeMessage.toCloseMessage();

        DraftMessagePage draftMessagePage = new DraftMessagePage();
        new WebDriverWait(DriverManager.getDriver(), 10);

        draftMessagePage.goToDraftMessageAndCheck();
        draftMessagePage.send(correctReceiver);
    }

    @AfterMethod
    public void tearDown() {
        DriverManager.tearDown();
    }
}
