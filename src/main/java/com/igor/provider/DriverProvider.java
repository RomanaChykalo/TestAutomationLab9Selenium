package com.igor.provider;

import com.igor.property.Property;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

public class DriverProvider {
    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    static {
        System.setProperty(Objects.requireNonNull(Property.getProperty("name")), Objects.requireNonNull(Property.getProperty("path")));
    }

    private DriverProvider() { }

    public static WebDriver getDriver()
    {
        if(Objects.isNull(driver.get())) {
            driver.set(new ChromeDriver());
            driver.get().manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
            driver.get().manage().window().maximize();
        }
        return driver.get();
    }

    public static void pageLoadTimeout(int seconds) {
        driver.get().manage().timeouts().pageLoadTimeout(seconds, TimeUnit.SECONDS);
    }

    public static void quit() {
        driver.get().quit();
        driver.set(null);
    }
}
