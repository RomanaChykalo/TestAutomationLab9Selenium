package edu.gmail.bussinessobjects;

import edu.gmail.pageobjects.GmailDraftForm;
import edu.gmail.pageobjects.GmailMailForm;
import edu.gmail.utils.DriverWrapper;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static edu.gmail.utils.DriverWrapper.getInstanceOfDriverWrapper;


public class GmailDraftMessageBO {

    private DriverWrapper driverWrapperInstance = getInstanceOfDriverWrapper();
    private WebDriver driver;
    private GmailMailForm gmailMailFormObject;
    private GmailDraftForm gmailDraftFormObject;
    private int numberOfDraftMessagesBeforeAddingNewDraftLetter;
//    private int numberOfDraftMessagesAfterAddingNewDraftLetter;

    public GmailDraftMessageBO() {
        driver = driverWrapperInstance.getDriver();
        gmailMailFormObject = new GmailMailForm(driver);
        gmailDraftFormObject = new GmailDraftForm(driver);
    }

    public void composeDraftLetterAndExit() {
        (new WebDriverWait(driver, 10)).until((dr) -> dr.getTitle().toLowerCase().startsWith("gmail"));
        gmailMailFormObject.composeEmail();
        numberOfDraftMessagesBeforeAddingNewDraftLetter = Integer.valueOf
                ((gmailDraftFormObject.getNumberOfDraftMessagesElement()).getText());
        gmailMailFormObject.setAddress();
        gmailMailFormObject.setSubject();
        gmailMailFormObject.writeMessage();
        gmailMailFormObject.exitFromLetter();
        (new WebDriverWait(driver, 15))
                .until(ExpectedConditions.invisibilityOfElementWithText
                        (gmailDraftFormObject.getNumberOfDraftMessages(),
                                String.valueOf(numberOfDraftMessagesBeforeAddingNewDraftLetter)));
    }

    public void openDraftMessagesAndSendDraftedLetter() {
        gmailDraftFormObject.clickDraftSection();
        gmailDraftFormObject.clickDraftEmail();
        gmailDraftFormObject.clickSendButton();
        (new WebDriverWait(driver, 30))
                .until(ExpectedConditions.invisibilityOfElementLocated
                        (gmailDraftFormObject.getSuccessfulEmailSendingIndicator()));
    }

    public int getNumberOfDraftMessagesBeforeAddingNewDraftLetter() {
        return numberOfDraftMessagesBeforeAddingNewDraftLetter;
    }

    public int getNumberOfDraftMessagesAfterAddingNewDraftLetter() {
        return Integer.valueOf((gmailDraftFormObject.getNumberOfDraftMessagesElement()).getText());
    }
}
