package com.data.dao;

import com.data.bean.Account;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class LoginDao extends BaseDao {
    public List<Account> loginDao(String username, String password) {
        String hql = "from Account where username=? and password=?";
        return  (List<Account>) super.findMassageDao(hql, username, password);

    }
}
