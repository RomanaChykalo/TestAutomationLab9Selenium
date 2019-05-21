package com.kryviak.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyParser {

    private String sysDriverProp;
    private String driverUrl;
    private String driverPath;

    public String getSysDriverProp() {
        return this.sysDriverProp;
    }

    public String getDriverUrl() {
        return this.driverUrl;
    }

    public String getDriverPath() {
        return this.driverPath;
    }

    public PropertyParser() {
        loadProperties();
    }

    private void loadProperties() {
        Properties prop = new Properties();
        InputStream input = null;

        try {
            input = new FileInputStream("src//main//resources//config.properties");
            prop.load(input);
            driverUrl = prop.getProperty("url");
            this.sysDriverProp = prop.getProperty("name.driver");
            driverPath = prop.getProperty("path.driver");
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
