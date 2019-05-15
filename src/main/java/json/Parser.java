package json;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class Parser {

    private ObjectMapper objectMapper;
    private Logger logger = LogManager.getLogger(Parser.class);

    public Parser() {
        this.objectMapper = new ObjectMapper();
    }

    public List<Data> getData(File jsonFile) {
        Data[] data = new Data[0];
        try {
            data = objectMapper.readValue(jsonFile, Data[].class);
        } catch (IOException e) {
            logger.error(e.getClass());
            e.printStackTrace();
        }

        return Arrays.asList(data);
    }
}
