package com.igor.business;

import com.igor.page.MainPage;
import com.igor.page.SentPage;
import com.igor.page.widget.AlertDialogWidget;
import com.igor.page.widget.NewMessageWidget;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static com.igor.utils.parser.JsonParser.*;

public class MessageBO {
    private static final Logger LOGGER = LogManager.getLogger(MessageBO.class);
    private MainPage mainPage;
    private NewMessageWidget newMessageWidget;
    private AlertDialogWidget alertDialogWidget;
    private SentPage sentPage;

    public boolean sendIncorrectMessage(String MESSAGE_TITLE) {
        LOGGER.info("Opening new message widget");
        mainPage = new MainPage();
        newMessageWidget = new NewMessageWidget();
        mainPage.clickToComposeButton();
        LOGGER.info("filling new letter");
        newMessageWidget.setReceiverField(getIncorrectReceiver());
        newMessageWidget.setTitleField(MESSAGE_TITLE);
        newMessageWidget.setMessageField(getMessage());
        newMessageWidget.clickToSendButton();
        LOGGER.info("Opening alert dialog");
        return new AlertDialogWidget().alertDialogIsEnable();
    }

    public void correctReceiverAndSend(){
        LOGGER.info("closing alert dialog");
        alertDialogWidget = new AlertDialogWidget();
        alertDialogWidget.clickToButtonOk();
        LOGGER.info("deleting incorrect receiver");
        newMessageWidget.clickToDeleteContact();
        LOGGER.info("writing correct receiver");
        newMessageWidget.setReceiverField(getReceiver());
        LOGGER.info("sending letter");
        newMessageWidget.clickToSendButton();
    }

    public String checkThatLetterIsSent(){
        LOGGER.info("opening sent page");
        sentPage = new SentPage();
        mainPage.goToSentPage();
        LOGGER.info("checking sent page");
        return sentPage.getLetter();
    }
}
