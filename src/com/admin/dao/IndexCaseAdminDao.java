package com.admin.dao;

import com.data.bean.IndexCase;
import com.data.dao.BaseDao;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

/**
 * FileName: IndexCaseAdminDao
 * Author:   HingLo
 * Date:     2017/11/6 10:04
 * Description: 案例管理的数据层
 **/
@Repository
@Transactional
public class IndexCaseAdminDao extends BaseDao {
    public List<IndexCase> listIndexCaseDao() {
        String hql = "from IndexCase ";
        return (List<IndexCase>) super.findMassageDao(hql);
    }

    public List<IndexCase> deleteIndexCaseDao(String id) {
        String hql = "from IndexCase where id=?";
        List<IndexCase> list = (List<IndexCase>) super.findMassageDao(hql);
        hql = "delete IndexCase where id=?";
        if (super.updateOneMssageDao(hql, id)) {
            return list;
        }
        return null;
    }

    public boolean changeStateDao(String id, boolean state) {
        String hql = "update IndexCase set state=? where id=? and state=?";
        return super.updateOneMssageDao(hql, !state, id, state);
    }
}
