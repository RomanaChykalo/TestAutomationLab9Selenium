package com.sofia.utilmanager;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.simple.parser.JSONParser;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;

public class JsonParser {
    private static final Logger LOG = LogManager.getLogger(JsonParser.class);
    private static JSONObject jsonObject;

    static {
        try {
            jsonObject = (JSONObject) (new JSONParser().parse(new FileReader("src/main/resources/data.json")));
        } catch (IOException | ParseException e) {
            LOG.error(e.getMessage());
        }
    }

    public static String getUsername(){
        return (String) jsonObject.get("username");
    }

    public static String getPassword(){
        return (String) jsonObject.get("password");
    }

    public static String getWidgetText(){
        return (String) (jsonObject.get("delete_email_widget_text"));
    }

}
