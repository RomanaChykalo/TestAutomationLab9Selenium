package com.igor.provider;

import com.igor.property.Property;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

public class DriverProvider {
    private static final WebDriver driver;
    private static WebDriverWait driverWait;

    static {
        System.setProperty(Objects.requireNonNull(Property.getProperty("name")), Objects.requireNonNull(Property.getProperty("path")));
        ChromeOptions options = new ChromeOptions();
        options.addArguments(Property.getProperty("captcha"));
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get(Property.getProperty("initial_page"));
        driverWait = new WebDriverWait(driver, 10);
    }

    private DriverProvider() { }

    public static WebDriver getDriver() {
        return driver;
    }

    public static WebDriverWait getDriverWait() { return driverWait; }

    public static void pageLoadTimeout(int seconds) {
        driver.manage().timeouts().pageLoadTimeout(seconds, TimeUnit.SECONDS);
    }
}
