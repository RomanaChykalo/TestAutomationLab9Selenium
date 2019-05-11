package com.igor.provider;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class DriverProvider {
    private static final WebDriver driver;
    private static WebDriverWait driverWait;

    static {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("disable-infobars");
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get("https://mail.google.com/");
        driverWait = new WebDriverWait(driver, 10);
    }

    private DriverProvider() {
    }

    public static WebDriver getDriver() {
        return driver;
    }

    public static WebDriverWait getDriverWait() { return driverWait; }

    public static void pageLoadTimeout(int seconds) {
        driver.manage().timeouts().pageLoadTimeout(seconds, TimeUnit.SECONDS);
    }

    public static void scriptLoadTimeout(int seconds){
        driver.manage().timeouts().setScriptTimeout(seconds, TimeUnit.SECONDS);
    }
}
