package com.kryviak.models;

public class UserModel {

    private String login;
    private String password;

    public String getUserLogin() {
        return login;
    }

    public void setUserLogin(String userLogin) {
        this.login = userLogin;
    }

    public String getUserPassword() {
        return password;
    }

    public void setUserPassword(String userPassword) {
        this.password = userPassword;
    }
}
