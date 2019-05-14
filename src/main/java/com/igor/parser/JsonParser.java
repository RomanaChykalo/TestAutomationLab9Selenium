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

    public static String getUserName(int index){
        return (String) ((JSONArray)((JSONObject)jsonObject.get("users")).get("email_address")).get(index);
    }

    public static int getNumberOfUsers(){
        return ((JSONArray)((JSONObject)jsonObject.get("users")).get("email_address")).size();
    }

    public static String getPassword(int index){
        return (String) ((JSONArray)((JSONObject)jsonObject.get("users")).get("passwords")).get(index);
    }

    public static String getIncorrectReceiver(){
        return (String) ((JSONObject)jsonObject.get("receivers")).get("incorrect_receiver");
    }

    public static String getReceiver(){
        return (String) ((JSONObject)jsonObject.get("receivers")).get("receiver");
    }

    public static String getMessage(){
        JSONArray jsonArray = (JSONArray) jsonObject.get("messages");
        return (String) jsonArray.get((int)(jsonArray.size()*Math.random()));
    }
}
