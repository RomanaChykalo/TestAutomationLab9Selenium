package com.kryviak.utils;

import com.kryviak.models.MessageModel;
import com.kryviak.utils.dom.DOMDocCreator;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.File;


public class XmlParser {

    private MessageModel readMessageModelData(Document doc) {
        doc.getDocumentElement().normalize();
        NodeList nodeList = doc.getElementsByTagName("messageData");
        MessageModel messageModel = new MessageModel();

        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element) node;
                messageModel.setEmailTo(element.getElementsByTagName("emailTo").item(i).getTextContent());
                messageModel.setMessageTo(element.getElementsByTagName("messageTo").item(i).getTextContent());
                messageModel.setSubjectTo(element.getElementsByTagName("subjectTo").item(i).getTextContent());
            }
        }
        return messageModel;
    }

    public MessageModel getMessageModelData() {
        File messageF = new File("src//main//resources//messageData.xml");
        return new XmlParser().readMessageModelData(new DOMDocCreator(messageF).getDocument());
    }
}
