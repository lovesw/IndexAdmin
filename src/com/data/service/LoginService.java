package com.data.service;

import com.data.bean.Account;
import com.data.dao.LoginDao;
import com.data.util.CheckDataUtils;
import com.data.util.Md5CodeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoginService {

    @Autowired
    private LoginDao loginDao;

    /**
     * 登录相关的服务层
     *
     * @param username
     * @param password
     * @return
     */
    public Account loginService(String username, String password) {
        if (CheckDataUtils.stringUtils(username, password)) {
            username = username.trim();
            password = password.trim();
            password = Md5CodeUtils.md5(password);
            List<Account> list = loginDao.loginDao(username, password);
            if (list != null && list.size() == 1)
                return list.get(0);
        }
        return null;
    }

    public LoginDao getLoginDao() {
        return loginDao;
    }

    public void setLoginDao(LoginDao loginDao) {
        this.loginDao = loginDao;
    }

}
