package driver;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.Objects;

public class DriverManager {
    static Logger logger = LogManager.getLogger(DriverManager.class);

    private static ThreadLocal<WebDriver> DRIVER_POOL = new ThreadLocal<>();;

    public static WebDriver getDriver(){
        return getWebDriverInstance();
    }

    private static WebDriver getWebDriverInstance(){
        if (Objects.isNull(DRIVER_POOL.get())){
            logger.info("Starting Chrome driver...");
            DRIVER_POOL.set(new ChromeDriver());
        }
        return DRIVER_POOL.get();
    }

    public static void driverQuit(){
        if (!Objects.isNull(getDriver())){
            getDriver().quit();
            DRIVER_POOL.set(null);
            logger.info("WebDriver is down.");
        }
    }
}
