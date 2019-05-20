package pom;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import util.DriverManager;
import util.PageObject;
import web_elements.Alert;
import web_elements.Button;
import web_elements.CheckBox;

import java.util.List;

public class GmailImportantPage extends PageObject {
    public static final Logger LOG = LogManager.getLogger(GmailImportantPage.class);
    private static final int EXPLICIT_WAIT_TIMEOUT = 7;
    private static final int SELECTED_MESSAGES = 3;

    @FindBy(xpath = "//*[@class='BltHke nH oy8Mbf']//*[@class = 'oZ-x3 xY']//div[@role='checkbox']")
    private List<CheckBox> allMessageCheckboxes;

    @FindBy(xpath = "//*[(@class='D E G-atb') and not(contains(@style,'display: none'))]//*[@title='Delete']")
    private Button deleteMessageButton;

    @FindBy(xpath = "//*[ contains ( text(), '3 conversations moved to Trash' )]")
    private Alert conversationsIsDeletedAllert;

    @FindBy(xpath = "//*[contains(@class,'aio UKr6le')]//a[contains(@title,'Important')]")
    private Button importantButton;


    public GmailImportantPage() {
        super();
    }


    public void chooseSomeImportantMessages() {
        importantButton.clickButton();
        new WebDriverWait(DriverManager.getWebDriver(), EXPLICIT_WAIT_TIMEOUT)
                .until(ExpectedConditions.urlContains("#imp"));
        for (int i = 0; i < SELECTED_MESSAGES; i++) {
            allMessageCheckboxes.get(i).setChecked(true);
        }
    }

    public void clickDeleteButton() {
        deleteMessageButton.clickButton();
    }

    public boolean verifyDeleteMessages() {
        if (conversationsIsDeletedAllert.isVisible()) ;
        else
            LOG.error("Test failed");
        return conversationsIsDeletedAllert.isVisible();
    }
}
