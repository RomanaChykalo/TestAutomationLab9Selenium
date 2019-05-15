package json;

public class Data {
    private String login;
    private String password;
    private String wrongReceiver;
    private String correctReceiver;
    private String subject;
    private String message;

  public Data(String login, String password, String wrongReceiver,String correctReceiver, String subject, String message){
        this.login = login;
        this.password = password;
        this.wrongReceiver = wrongReceiver;
        this.correctReceiver = correctReceiver;
        this.subject = subject;
        this.message = message;
    }
    public Data(){}

    public String getLogin() {
        return login;
    }

    public String getMessage() {
        return message;
    }

    public String getPassword() {
        return password;
    }

    public String getCorrectReceiver() {
        return correctReceiver;
    }

    public String getWrongReceiver() {
        return wrongReceiver;
    }

    public String getSubject() {
        return subject;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setCorrectReceiver(String correctReceiver) {
        this.correctReceiver = correctReceiver;
    }

    public void setWrongReceiver(String wrongReceiver) {
        this.wrongReceiver = wrongReceiver;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }
}
