package com.admin.dao;

import com.data.bean.Account;
import com.data.dao.BaseDao;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class AccountAdminDao extends BaseDao {
    /**
     * 查询全部账号
     *
     * @return
     */
    public List<Account> accountListDao() {
        String hql = "from Account";
        return (List<Account>) super.findMassageDao(hql);
    }

    /**
     * 根据指定的id删除账号
     *
     * @param id
     * @return
     */
    public boolean deleteAccountDao(String id) {
        String hql = "delete Account where id=?";
        return updateManyMssageDao(hql, id) > 0 ? true : false;
    }

    /**
     * 修改账号的状态
     *
     * @param id
     * @param state
     * @return
     */
    public boolean changeAccountDao(String id, boolean state) {

        String hql = "update Account set state=? where id=? and state=?";
        return super.updateOneMssageDao(hql, !state, id, state);
    }

    /**
     * 检测账号是否被占用
     *
     * @param username
     * @param i
     * @return
     */
    public boolean checkAccountDao(String username, int i) {
        String hql = "from Account where ";
        switch (i) {
            case 1:
                hql += "email=?";
                break;
            case 2:
                hql += "username=?";
                break;
            case 3:
                hql += "phone=?";
                break;

        }
        List list = super.findMassageDao(hql, username);
        return list == null || list.isEmpty() ? true : false;
    }
}
