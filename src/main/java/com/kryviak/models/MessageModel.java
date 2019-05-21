package com.kryviak.models;

public class MessageModel {

    private String emailTo;
    private String subjectTo;
    private String messageTo;

    public String getEmailTo() {
        return emailTo;
    }

    public void setEmailTo(String emailTo) {
        this.emailTo = emailTo;
    }

    public String getSubjectTo() {
        return subjectTo;
    }

    public void setSubjectTo(String subjectTo) {
        this.subjectTo = subjectTo;
    }

    public String getMessageTo() {
        return messageTo;
    }

    public void setMessageTo(String messageTo) {
        this.messageTo = messageTo;
    }
}
