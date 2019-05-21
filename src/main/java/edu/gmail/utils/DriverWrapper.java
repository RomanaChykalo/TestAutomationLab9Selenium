package edu.gmail.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;


public class DriverWrapper {

    private static DriverWrapper instanceOfDriverWrapper = null;
    private WebDriver driver;

    private DriverWrapper() {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        driver = new ChromeDriver();
    }

    public static DriverWrapper getInstanceOfDriverWrapper() {
        if (instanceOfDriverWrapper == null) {
            instanceOfDriverWrapper = new DriverWrapper();
        }
        return instanceOfDriverWrapper;
    }

    public WebDriver getDriver() {
        return driver;
    }
}
