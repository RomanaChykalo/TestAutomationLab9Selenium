package com.kryviak.loginBO;

import com.kryviak.models.LoginModel;
import com.kryviak.pages.login.LoginEmailPage;
import com.kryviak.pages.login.LoginPassPage;

public class LoginBO {

    public void login(LoginModel loginModel) {
        new LoginEmailPage().setUserEmailTextField(loginModel.getUserLogin()).clickNextButton();
        new LoginPassPage().setUserPasswordTextField(loginModel.getUserPassword()).clickNextButton();
    }
}
