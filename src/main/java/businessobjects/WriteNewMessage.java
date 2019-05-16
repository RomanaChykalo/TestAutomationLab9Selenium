package businessobjects;

import pageobjects.HomePage;
import pageobjects.WriteMessage;

public class WriteNewMessage {

    private HomePage homePage = new HomePage();
    private WriteMessage writeMessage = new WriteMessage();

    public void tryToWriteMessage(String receiver, String subject, String message){
        homePage.goToWriteMessage();
        writeMessage.inputReceiver(receiver);
        writeMessage.inputSubject(subject);
        writeMessage.inputMessage(message);
        writeMessage.toCloseMessage();
    }

}
