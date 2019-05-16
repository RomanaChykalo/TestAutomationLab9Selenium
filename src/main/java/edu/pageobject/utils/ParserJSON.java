package edu.pageobject.utils;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.io.FileReader;
import java.io.IOException;


public class ParserJSON {

    private static Logger logger = LogManager.getLogger("ParserJSON");

    public static JSONObject getJsonFile() {
        JSONParser jsonParser = new JSONParser();
        JSONObject jsonObject = null;
        try {
            Object object = jsonParser.parse(new FileReader("src/main/resources/objectrepository.json"));
            jsonObject = (JSONObject) object;
        } catch (IOException | ParseException e) {
            logger.info("JSON parser error: " + e.getMessage());
        }
        return jsonObject;
    }
}
