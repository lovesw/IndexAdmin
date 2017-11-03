package com.admin.service;

import com.admin.dao.AccountAdminDao;
import com.data.bean.Account;
import com.data.util.CheckDataUtils;
import com.data.util.DatabaseIdUtils;
import com.data.util.FinalStringUtils;
import com.data.util.Md5CodeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountAdminService {

    @Autowired
    private AccountAdminDao accountAdminDao;


    public AccountAdminDao getAccountAdminDao() {
        return accountAdminDao;
    }

    public void setAccountAdminDao(AccountAdminDao accountAdminDao) {
        this.accountAdminDao = accountAdminDao;
    }

    public List<Account> accountListService() {

        return accountAdminDao.accountListDao();
    }

    public boolean deleteAccountService(String id) {
        if (CheckDataUtils.stringUtilsID(id)) {
            id = id.trim();
            return accountAdminDao.deleteAccountDao(id);
        }
        return false;
    }

    public boolean changeAccountService(String id, boolean state) {
        if (CheckDataUtils.stringUtilsID(id)) {
            id = id.trim();
            return accountAdminDao.changeAccountDao(id, state);
        }
        return false;
    }

    public boolean addAccountService(Account account) {
        if (CheckDataUtils.stringUtils(account.getEmail(), account.getUsername(), account.getPhone())) {
            account.setId(DatabaseIdUtils.getDataIdOne());
            if (!CheckDataUtils.stringUtils(account.getPassword()))//检测是否初始化密码了，没有就默认使用用户名作为密码
                account.setPassword(Md5CodeUtils.md5(account.getUsername()));
            account.setPassword(Md5CodeUtils.md5(account.getPassword()));

            accountAdminDao.saveMassageDao(account);
        }
        return false;
    }

    public boolean checkAccountService(String username, String type) {
        if (CheckDataUtils.stringUtils(type, username)) {
            if (type.equals(FinalStringUtils.EMAIL)) {
                return accountAdminDao.checkAccountDao(username, 2);
            } else if (type.equals(FinalStringUtils.USERNAME)) {
                return accountAdminDao.checkAccountDao(username, 1);

            } else if (type.equals(FinalStringUtils.PHONE)) {
                return accountAdminDao.checkAccountDao(username, 3);

            } else return false;
        }
        return false;

    }
}
