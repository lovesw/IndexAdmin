package com.admin.dao;

import com.data.bean.ThemeClass;
import com.data.dao.BaseDao;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

/**
 * FileName: ThemeClassDao
 * Author:   HingLo
 * Date:     2017/11/6 11:03
 * Description: 主题分类的Dao
 **/
@Repository
@Transactional
public class ThemeClassDao extends BaseDao {
    public List<ThemeClass> listThemeClassDao() {
        String hql = "from ThemeClass";
        return (List<ThemeClass>) super.findMassageDao(hql);
    }

    public boolean changeThemeClassDao(String id, String name) {
        String hql = "update ThemeClass set name=? where id=?";
        return updateOneMssageDao(hql, name, id);
    }

    public boolean deleteThemeClassDao(String id) {
        String hql = "delete ThemeClass where id=?";
        return super.updateManyMssageDao(hql, id) > 0 ? true : false;
    }
}
