package com.halas.parsers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;

public class JsonParser {
    private static final Logger LOG = LogManager.getLogger(JsonParser.class);
    private static JSONObject jsonObject;

    static {
        try {
            jsonObject = (JSONObject) (new JSONParser().parse(new FileReader("src/main/resources/data/gmail.json")));
        } catch (IOException | ParseException e) {
            LOG.error("Cannot find file json..");
            LOG.error("Class: " + e.getClass());
            LOG.error("Message: " + e.getMessage());
            LOG.error(e.getStackTrace());
        }
    }

    public static String getBaseUrl() {
        return jsonObject.get("url-base").toString();
    }

    public static String getUserLogin() {
        return jsonObject.get("user-login").toString();
    }

    public static String getUserPassword() {
        return jsonObject.get("user-password").toString();
    }

    public static String getWhoReceiveMessage() {
        return jsonObject.get("who-receive-message").toString();
    }

    public static String getWhoReceiveCopyMessage() {
        return jsonObject.get("who-receive-copy-message").toString();
    }

    public static String getWhoReceiveHiddenCopyMessage() {
        return jsonObject.get("who-receive-hidden-copy-message").toString();
    }

    public static String getThemeMessage() {
        return jsonObject.get("theme-message").toString();
    }

    public static String getMessage() {
        return jsonObject.get("message").toString();
    }
}
