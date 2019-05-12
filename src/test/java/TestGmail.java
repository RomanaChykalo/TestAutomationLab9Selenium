import driver.DriverManager;
import json.Data;
import json.Parser;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;
import page.objects.DraftMessagePage;
import page.objects.HomePage;
import page.objects.LoginPage;
import page.objects.WriteMessage;
import properties.FilePath;

import java.io.File;

public class TestGmail {

    @Test
    public void LoginTest() {
        FilePath filePath = new FilePath();
        File json = new File(filePath.propertyFile("fileWayJSON"));
        Parser parser = new Parser();
        Data data = parser.getData(json);

        LoginPage loginPage = new LoginPage();
        loginPage.loginAndSubmit(data.getLogin(), data.getPassword());

        HomePage homePage = new HomePage();
        homePage.goToWriteMessage();

        WriteMessage writeMessage = new WriteMessage();
        writeMessage.toWriteMessage(data.getMessage(), data.getWrongReceiver(), data.getSubject());
        writeMessage.toCloseMessage();

        DraftMessagePage draftMessagePage = new DraftMessagePage();
        new WebDriverWait(DriverManager.getDriver(), 10);

        Assert.assertEquals(draftMessagePage.goToDraftMessageAndCheck(), data.getSubject());
        draftMessagePage.send(data.getCorrectReceiver());
    }

    @AfterTest
    public void tearDown() {
        DriverManager.tearDown();
    }
}
