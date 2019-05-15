package pages;

import driver.DriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class BasePage {
    Logger logger = LogManager.getLogger(BasePage.class);

    private WebDriver driver;

    public BasePage() {
        this.driver = DriverManager.getDriver();
        PageFactory.initElements(driver, this);
    }

    public WebDriver driver(){
        return this.driver;
    }

    public Wait expWait(){
        Wait expWait = new WebDriverWait(driver, 25);
        return expWait;
    }

}
