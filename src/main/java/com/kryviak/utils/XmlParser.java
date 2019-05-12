package com.kryviak.utils;

import com.kryviak.models.LoginModel;
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

    private LoginModel readLoginModelData(Document doc) {
        doc.getDocumentElement().normalize();
        NodeList nodeList = doc.getElementsByTagName("loginData");
        LoginModel loginModel = new LoginModel();

        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element) node;
                loginModel.setUserLogin(element.getElementsByTagName("login").item(i).getTextContent());
                loginModel.setUserPassword(element.getElementsByTagName("password").item(i).getTextContent());
            }
        }
        return loginModel;
    }

    public MessageModel getMessageModelData() {
        File messageF = new File("src//main//resources//messageData.xml");
        return new XmlParser().readMessageModelData(new DOMDocCreator(messageF).getDocument());
    }

    public LoginModel getLoginModelData() {
        File loginF = new File("src//main//resources//loginData.xml");
        return new XmlParser().readLoginModelData(new DOMDocCreator(loginF).getDocument());
    }
}
