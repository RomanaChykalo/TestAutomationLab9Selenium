package util.readers;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;

public class JAXBParser {
    public static Message getMessage(String messagePath) {
        JAXBContext jaxbContext;
        Message message = null;
        try {
            jaxbContext = JAXBContext.newInstance(Message.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            message = (Message) jaxbUnmarshaller.unmarshal(new File("src/main/resources/test-data/message.xml"));
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return message;
    }
}

