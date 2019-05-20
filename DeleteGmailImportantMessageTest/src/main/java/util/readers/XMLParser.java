package util.readers;


import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class XMLParser {
    private static List<Message> parseMessagesXML() throws ParserConfigurationException, SAXException, IOException {
        List<Message> messages = new ArrayList<Message>();
        Message message = null;
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(new File("message.xml"));
        document.getDocumentElement().normalize();
        NodeList nList = document.getElementsByTagName("message");
        for (int temp = 0; temp < nList.getLength(); temp++) {
            Node node = nList.item(temp);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element eElement = (Element) node;
                message = new Message();
                message.setRecipient(eElement.getElementsByTagName("recipients").item(0).getTextContent());
                message.setSubject(eElement.getElementsByTagName("subject").item(0).getTextContent());
                message.setMessage_text(eElement.getElementsByTagName("message_text").item(0).getTextContent());
                messages.add(message);
            }
        }
        return messages;
    }
}
