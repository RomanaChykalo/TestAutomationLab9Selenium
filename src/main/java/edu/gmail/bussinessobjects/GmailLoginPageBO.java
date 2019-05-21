package edu.gmail.bussinessobjects;

import edu.gmail.pageobjects.GmailLoginPage;
import edu.gmail.utils.DriverWrapper;
import org.openqa.selenium.WebDriver;

import static edu.gmail.utils.DriverWrapper.getInstanceOfDriverWrapper;


public class GmailLoginPageBO {

    private DriverWrapper driverWrapperInstance = getInstanceOfDriverWrapper();
    private WebDriver driver;
    private GmailLoginPage gmailLoginPageObject;

    public GmailLoginPageBO() {
        driver = driverWrapperInstance.getDriver();
        gmailLoginPageObject = new GmailLoginPage(driver);
    }

    public void loginToGmail() {
        driver.get("http://mail.google.com/");
        gmailLoginPageObject.setUserLogin();
        gmailLoginPageObject.submitLogin();
        gmailLoginPageObject.setPassword();
        gmailLoginPageObject.submitPassword();
    }
}
