package edu.pageobject.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;


public class DriverUtil {

    private static DriverUtil driver;

    private DriverUtil() {
    }

    public static WebDriver getDriver() {
        if (driver == null) {
            driver = new DriverUtil();
        }
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        return new ChromeDriver();
    }
}
