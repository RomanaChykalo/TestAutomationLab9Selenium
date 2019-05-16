package json;

public class Data {
    private String login;
    private String password;
    private String receiver;
    private String subject;
    private String message;

  public Data(String login, String password, String receiver, String subject, String message){
        this.login = login;
        this.password = password;
        this.receiver = receiver;
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

    public String getReceiver() {
        return receiver;
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

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }
}
