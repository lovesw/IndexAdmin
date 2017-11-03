package com.admin.dao;

import com.data.bean.IndexAdmin;
import com.data.dao.BaseDao;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class AdminIndexDao extends BaseDao {

    public List<IndexAdmin> indexBannerListDao() {
        String hql = "from IndexAdmin ";
        return (List<IndexAdmin>) super.findMassageDao(hql);
    }

    public List<IndexAdmin> deleteBannerListDao(String id) {
        String hql = "from IndexAdmin where id=?";
        List<IndexAdmin> list = (List<IndexAdmin>) super.findMassageDao(hql);
        hql = "delete IndexAdmin where id=?";
        if (super.updateOneMssageDao(hql, id)) {
            return list;
        }
        return null;


    }
}
