package com.admin.dao;

import com.data.bean.IndexAdmin;
import com.data.dao.BaseDao;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class AdminIndexDao extends BaseDao {
    /**
     * 查询首页展示图的全部内容
     *
     * @return
     */
    public List<IndexAdmin> indexBannerListDao() {
        String hql = "from IndexAdmin ";
        return (List<IndexAdmin>) super.findMassageDao(hql);
    }

    /**
     * 删除首页展示图
     *
     * @param id
     * @return
     */
    public List<IndexAdmin> deleteBannerListDao(String id) {
        String hql = "from IndexAdmin where id=?";
        List<IndexAdmin> list = (List<IndexAdmin>) super.findMassageDao(hql);
        hql = "delete IndexAdmin where id=?";
        if (super.updateOneMssageDao(hql, id)) {
            return list;
        }
        return null;


    }

    /**
     * 修改展示的状态
     *
     * @param id
     * @param state
     * @return
     */
    public boolean changeStateDao(String id, boolean state) {
        String hql = "update IndexAdmin set state=? where id=? and state=?";
        return super.updateOneMssageDao(hql, !state, id, state);
    }
}
