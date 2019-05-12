package driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import static model.Consts.CITE;

public class DriverLoader {

    private static WebDriver driver;
    private static Map<String, String> configList = new DriverLoader().getInfoFromPropertyFile();

    static {
        System.setProperty(configList.get("driver"),configList.get("path"));
    }

    private DriverLoader(){}

    public static WebDriver getDriver(){
        if(Objects.isNull(driver)){
            driver = createDriverInstance();
        }
        return driver;
    }

    public static WebDriver createDriverInstance(){
        driver=new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(CITE);
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        return driver;
    }
    public static void tearDown() {
            driver.quit();
    }
    private Map<String, String> getInfoFromPropertyFile() {
        Properties prop = new Properties();
        try (InputStream input = getClass().getClassLoader().getResourceAsStream("application.properties")) {
            prop.load(input);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        configList = new HashMap<>();
        configList.put("driver", prop.getProperty("driver"));
        configList.put("path", prop.getProperty("path"));
        return configList;
    }
}
