import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

public class DriverManager {

    private static WebDriver driver;

    static {
        System.setProperty("webdriver.chrome.driver","src/main/resource/chromedriver.exe");
    }

    private DriverManager(){}

    public static WebDriver getDriver(){
        if(Objects.isNull(driver)){
            driver = new ChromeDriver();
        }
        return driver;
    }

    public static WebDriver createDriverInstance(WebDriver driver){
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        return driver;
    }
}
