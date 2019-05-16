package businessobjects;

import pageobjects.DraftMessagePage;

public class SendFromDraft {

    private DraftMessagePage draftMessagePage = new DraftMessagePage();

    public void sendMessage(){
        draftMessagePage.goToDraftMessage();
        draftMessagePage.chooseCorrectMessage();
        draftMessagePage.send();
    }
}
