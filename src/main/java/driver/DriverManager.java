package driver;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.Objects;

public class DriverManager {
    static Logger logger = LogManager.getLogger(DriverManager.class);

    private static WebDriver driver;

    public static WebDriver getDriver(){
        return getWebDriverInstance();
    }

    private static WebDriver getWebDriverInstance(){
        if (Objects.isNull(driver)){
            logger.trace("Starting Chrome driver...");
            driver = new ChromeDriver();
        }
        return driver;
    }

    public static void driverQuit(){
        if (!Objects.isNull(driver)){
            driver.quit();
        }
    }
}
