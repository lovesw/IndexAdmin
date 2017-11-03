package com.admin.service;

import com.admin.bean.Account;
import com.admin.dao.LoginDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    @Autowired
    private LoginDao loginDao;


    public Account loginService(String username, String password) {


        return null;
    }

    public LoginDao getLoginDao() {
        return loginDao;
    }

    public void setLoginDao(LoginDao loginDao) {
        this.loginDao = loginDao;
    }

}
