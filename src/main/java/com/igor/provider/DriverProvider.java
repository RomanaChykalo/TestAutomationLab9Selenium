package com.igor.provider;

import com.igor.utils.property.Property;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

import static com.igor.utils.constant.Constants.IMPLICIT_WAIT;

public class DriverProvider {
    private static ThreadLocal<WebDriver> DRIVER_POOL = new ThreadLocal<>();
    private static final String NAME = Objects.requireNonNull(Property.getProperty("name"));
    private static final String PATH = Objects.requireNonNull(Property.getProperty("path"));

    static {
        System.setProperty(NAME, PATH);
    }

    private DriverProvider() { }

    public static WebDriver getDriver()
    {
        if(Objects.isNull(DRIVER_POOL.get())) {
            DRIVER_POOL.set(new ChromeDriver());
            DRIVER_POOL.get().manage().timeouts().implicitlyWait(IMPLICIT_WAIT, TimeUnit.SECONDS);
        }
        return DRIVER_POOL.get();
    }

    public static void pageLoadTimeout(int seconds) {
        DRIVER_POOL.get().manage().timeouts().pageLoadTimeout(seconds, TimeUnit.SECONDS);
    }

    public static void quit() {
        DRIVER_POOL.get().quit();
        DRIVER_POOL.set(null);
    }
}
