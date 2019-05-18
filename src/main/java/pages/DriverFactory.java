package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

public class DriverFactory {
    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    private DriverFactory() {
    }

    public static WebDriver getWebDriver() {
        if (Objects.isNull(driver.get())) {
            driver.set(new ChromeDriver());
            driver.get().manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
            driver.get().manage().window().maximize();
        }
        return driver.get();
    }

    public static void removeDriver() {
        driver.get().quit();
        driver.remove();
    }
}




