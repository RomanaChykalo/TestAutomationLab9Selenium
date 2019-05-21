package com.kryviak.loginBO;

import com.kryviak.models.UserModel;
import com.kryviak.pages.login.LoginEmailPage;
import com.kryviak.pages.login.LoginPassPage;

public class LoginBO {

    private LoginEmailPage loginEmailPage = new LoginEmailPage();
    private LoginPassPage loginPassPage = new LoginPassPage();

    public void login(UserModel userModel) {
        loginEmailPage.setUserEmailTextField(userModel.getUserLogin()).clickNextButton();
        loginPassPage.setUserPasswordTextField(userModel.getUserPassword()).clickNextButton();
    }
}
