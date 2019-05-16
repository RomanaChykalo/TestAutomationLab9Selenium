package com.igor.business;

import com.igor.page.MainPage;
import com.igor.page.SentPage;
import com.igor.page.widget.AlertDialogWidget;
import com.igor.page.widget.NewMessageWidget;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.UUID;

import static com.igor.utils.parser.JsonParser.*;

public class MessageBO {
    private static final Logger LOGGER = LogManager.getLogger(MessageBO.class);
    private final String MESSAGE_TITLE;
    private MainPage mainPage;
    private SentPage sentPage;
    private NewMessageWidget newMessageWidget;
    private AlertDialogWidget alertDialogWidget;


    public MessageBO(){
        mainPage = new MainPage();
        sentPage = new SentPage();
        newMessageWidget = new NewMessageWidget();
        alertDialogWidget = new AlertDialogWidget();
        MESSAGE_TITLE = UUID.randomUUID().toString();
    }

    public void sendIncorrectMessage() {
        LOGGER.info("Opening new message widget");
        mainPage.clickToComposeButton();
        LOGGER.info("filling new letter");
        newMessageWidget.setReceiverField(getIncorrectReceiver());
        newMessageWidget.setTitleField(MESSAGE_TITLE);
        newMessageWidget.setMessageField(getMessage());
        newMessageWidget.clickToSendButton();
        LOGGER.info("Opening alert dialog");
    }

    public void correctReceiverAndSend(){
        LOGGER.info("closing alert dialog");
        alertDialogWidget.clickToButtonOk();
        LOGGER.info("deleting incorrect receiver");
        newMessageWidget.clickToDeleteContact();
        LOGGER.info("writing correct receiver");
        newMessageWidget.setReceiverField(getReceiver());
        LOGGER.info("sending letter");
        newMessageWidget.clickToSendButton();
    }

    public boolean checkThatAlertWidgetAppeared(){
        return alertDialogWidget.alertDialogIsEnable();
    }

    public boolean checkThatLetterIsSent(){
        LOGGER.info("opening sent page");
        mainPage.goToSentPage();
        LOGGER.info("checking sent page");
        return sentPage.getLetter().equals(MESSAGE_TITLE);
    }
}
