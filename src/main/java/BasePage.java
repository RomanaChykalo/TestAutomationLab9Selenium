import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {
    protected WebDriver driver;
    protected WebDriverWait webDriverWait;

    public BasePage(){
        this.driver = DriverManager.getDriver();
        this.webDriverWait = new WebDriverWait(driver, 3);
    }
}
