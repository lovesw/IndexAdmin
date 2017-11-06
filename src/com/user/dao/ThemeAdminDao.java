package com.user.dao;

import com.data.bean.Theme;
import com.data.dao.BaseDao;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

/**
 * FileName: ThemeAdminDao
 * Author:   HingLo
 * Date:     2017/11/6 11:41
 * Description: 主题管理的到
 **/
@Repository
@Transactional
public class ThemeAdminDao extends BaseDao {
    public List<Theme> listThemeAdminDao(String id) {
        String hql = "from Theme where uid=?";
        return (List<Theme>) super.findMassageDao(hql, id);
    }

    /**
     * status 0:表示默认状态，1：表示待审核状态，2：表示通过状态，-1:表示未通过状态
     *
     * @param id
     * @return
     */

    public boolean changeThemeAdminDao(String id) {
        String hql = "update set status=? Theme where id=? and status=?";
        return super.updateOneMssageDao(hql, 1, id, 0);
    }

    public boolean deleteThemeAdminDao(String id) {
        String hql = "delete  Theme where id=? and state=? and  status in(?,?)";
        return super.updateOneMssageDao(hql, id, false, 0, -1);

    }
}
