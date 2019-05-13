package com.igor.provider;

import com.igor.property.Property;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

public class DriverProvider {
    private static WebDriver driver;

    static {
        System.setProperty(Objects.requireNonNull(Property.getProperty("name")), Objects.requireNonNull(Property.getProperty("path")));
    }

    private DriverProvider() { }

    public static WebDriver getDriver()
    {
        if(Objects.isNull(driver)) {
            driver = new ChromeDriver();
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            driver.manage().window().maximize();
        }
        return driver;
    }

    public static void pageLoadTimeout(int seconds) {
        driver.manage().timeouts().pageLoadTimeout(seconds, TimeUnit.SECONDS);
    }

    public static void quit() {
        driver.quit();
        driver = null;
    }
}
