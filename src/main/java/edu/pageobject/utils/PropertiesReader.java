package edu.pageobject.utils;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;


public class PropertiesReader {

    public static Properties getLocatorsFile() {
        Properties object = new Properties();
        try {
            object.load(new FileReader("src/main/resources/locators.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return object;
    }
}
