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

public class DriverLoader {
    private static ThreadLocal<WebDriver> driverPool = new ThreadLocal<>();
    private static Map<String, String> configList = new DriverLoader().getInfoFromPropertyFile();

    static {
        System.setProperty(configList.get("driver"), configList.get("path"));
    }

    private DriverLoader() {
    }

    public static WebDriver getDriver() {
        if (Objects.isNull(driverPool.get())) {
            driverPool.set(new ChromeDriver());
            driverPool.get().manage().window().maximize();
            driverPool.get().manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        }
        return driverPool.get();
   }

    public static void tearDown() {
            driverPool.get().quit();
            driverPool.remove();
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
