package com.kryviak.config;

import com.kryviak.utils.PropertyParser;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class DriverThreadInit {

    private PropertyParser propertyParser = new PropertyParser();

    private DriverThreadInit() {
    }

    private static DriverThreadInit instance = new DriverThreadInit();

    public static DriverThreadInit getInstance() {
        return instance;
    }

    private ThreadLocal<WebDriver> threadDriver = new ThreadLocal<WebDriver>() {
        @Override
        protected WebDriver initialValue() {
            WebDriver webDriver;
            webDriver = new ChromeDriver();
            System.setProperty(propertyParser.getSysDriverProp(), propertyParser.getDriverPath());
            webDriver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
            webDriver.manage().window().maximize();
            webDriver.get(propertyParser.getDriverUrl());
            return webDriver;
        }
    };

    public WebDriver getDriver() {
        return threadDriver.get();
    }

    public void removeDriver() {
        threadDriver.get().quit();
        threadDriver.remove();
    }
}
