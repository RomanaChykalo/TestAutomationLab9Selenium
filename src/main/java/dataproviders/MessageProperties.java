package dataproviders;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import static constants.Constants.PATH_TO_PROPERTIES;

public class MessageProperties {

    public static Properties getProperties() {
        Properties properties = new Properties();
        try {
            properties.loadFromXML(new FileInputStream(PATH_TO_PROPERTIES));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties;
    }
}