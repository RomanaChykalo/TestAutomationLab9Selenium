package com.igor.parser;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.simple.parser.JSONParser;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;

public class JsonParser {
    private static final Logger LOGGER = LogManager.getLogger(JsonParser.class);
    private static JSONObject jsonObject;

    static {
        try {
            jsonObject = (JSONObject) (new JSONParser().parse(new FileReader("src/main/resources/data.json")));
        } catch (IOException | ParseException e) {
            LOGGER.error(e.getMessage());
        }
    }

    public static String getUserName(){
        return (String) jsonObject.get("username");
    }

    public static String getPassword(){
        return (String) jsonObject.get("password");
    }

    public static String getReceiver(int index){
        return (String) ((JSONArray)jsonObject.get("receivers")).get(index);
    }

    public static String getTitle(){
        return (String) jsonObject.get("title");
    }

    public static String getMessage(){
        JSONArray jsonArray = (JSONArray) jsonObject.get("messages");
        return (String) jsonArray.get((int)(jsonArray.size()*Math.random()));
    }
}
