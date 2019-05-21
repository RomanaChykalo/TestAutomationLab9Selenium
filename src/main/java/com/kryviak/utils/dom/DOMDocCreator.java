package com.kryviak.utils.dom;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

public class DOMDocCreator {

    private static Logger logger = LogManager.getLogger(DOMDocCreator.class);
    private DocumentBuilder documentBuilder;
    private Document document;

    public DOMDocCreator(File xml) {
        createDOMBuilder();
        createDoc(xml);
    }

    private void createDOMBuilder() {
        DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
        try {
            documentBuilder = builderFactory.newDocumentBuilder();
        } catch (ParserConfigurationException ex) {
            logger.error(ex);
        }
    }

    private void createDoc(File xml) {
        try {
            document = documentBuilder.parse(xml);
        } catch (SAXException | IOException ex) {
            logger.error(ex);
        }
    }

    public Document getDocument() {
        return document;
    }
}
