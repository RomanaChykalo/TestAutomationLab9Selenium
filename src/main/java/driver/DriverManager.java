package driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import properties.FilePath;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

public class DriverManager {

    private static WebDriver driver;
    private static FilePath filePath = new FilePath();

    static {

        System.setProperty("webdriver.chrome.driver", filePath.propertyFile("fileWayChrome"));
    }

    private DriverManager() {
    }

    public static WebDriver getDriver() {
        if (Objects.isNull(driver)) {
            driver = new ChromeDriver();
            driver.get(filePath.propertyFile("fileWayGmail"));
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            driver.manage().window().maximize();
        }
        return driver;
    }

    public static void tearDown() {
        driver.quit();
    }
}
