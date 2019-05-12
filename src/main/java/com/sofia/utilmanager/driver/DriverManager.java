package com.sofia.utilmanager.driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

import static com.sofia.utilmanager.property.Property.getProperty;

public class DriverManager {
    private static WebDriver driver;
    private static final String DRIVER_PATH = getProperty("path");
    private static final String WEB_DRIVER_NAME = getProperty("name");

    private DriverManager() {
    }

    static {
        System.setProperty(WEB_DRIVER_NAME, DRIVER_PATH);
    }

    public static WebDriver getDriverInstance() {
        if (Objects.isNull(driver)) {
            driver = setSettings();
        }
        return driver;
    }

    private static WebDriver setSettings() {
        driver = new ChromeDriver();
        driver.manage().timeouts()
                .implicitlyWait(60, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        return driver;
    }
}
