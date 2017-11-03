package com.data.dao;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 所有继承的Dao的父类
 *
 * @author:HingLo
 * @create 2017-08-29 9:55
 **/
@Repository
public class BaseDao extends HibernateDaoSupport {

    @Autowired
    @Qualifier("sessionFactory")
    public void setSessionFactary(SessionFactory sessionFactory) {
        super.setSessionFactory(sessionFactory);
    }

    /**
     * 保存或者更新信息
     *
     * @param o
     * @return
     */

    public boolean saveMassageDao(Object... o) {
        try {
            for (Object o1 : o) {
                this.getHibernateTemplate().saveOrUpdate(o1);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

    /**
     * 单条数据更新
     *
     * @param hql
     * @param value
     * @return
     */
    public boolean updateOneMssageDao(String hql, Object... value) {
        int rs = this.getHibernateTemplate().bulkUpdate(hql, value);
        return rs == 1 ? true : false;

    }

    /**
     * 批量更新
     *
     * @param hql
     * @param value
     * @return 返回更新的条数
     */
    public int updateManyMssageDao(String hql, Object... value) {
        return this.getHibernateTemplate().bulkUpdate(hql, value);
    }

    /**
     * 条件查询
     *
     * @param hql
     * @param value
     * @return
     */
    public List<?> findMassageDao(String hql, Object... value) {

        return this.getHibernateTemplate().find(hql, value);
    }

    /**
     * 使用mysql方式查询
     *
     * @param sql
     * @param value
     * @return
     */
    public List<?> findMysqlMethodDao(String sql, Object... value) {
        sql = sql.toLowerCase();
        Session session = this.getSessionFactory().openSession();
        Query query = session.createSQLQuery(sql);
        for (int i = 0; i < value.length; i++) {
            query.setParameter(i, value[i]);
        }
        List<?> list = query.list();
        session.close();
        return list;
    }

    /**
     * mysql 方式更新
     *
     * @param sql
     * @param value
     * @return
     */

    public int updateMysqlMethodDao(String sql, Object... value) {
        sql = sql.toLowerCase();
        Session session = this.getSessionFactory().openSession();
        Query query = session.createSQLQuery(sql);
        for (int i = 0; i < value.length; i++) {
            query.setParameter(i, value[i]);
        }
        int ex = query.executeUpdate();
        session.close();
        return ex;


    }


}
