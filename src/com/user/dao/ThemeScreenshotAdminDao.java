package com.user.dao;

import com.data.bean.ThemeScreenshot;
import com.data.dao.BaseDao;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

/**
 * FileName: ThemeScreenshotAdminDao
 * Author:   HingLo
 * Date:     2017/11/6 12:02
 * Description:
 **/
@Repository
@Transactional
public class ThemeScreenshotAdminDao extends BaseDao {
    public List<ThemeScreenshot> listThemeScreenshotAdminDao(String id) {
        String hql = "from ThemeScreenshot where id=?";
        return (List<ThemeScreenshot>) super.findMassageDao(hql, id);

    }

    /**
     * 删除图片，首先保证这个主题没有上线，还在审核中
     *
     * @param id 图片的id
     * @return
     */
    public List<ThemeScreenshot> deleteThemeScreenshotAdminDao(String tid, String id) {
        String hql = "from ThemeScreenshot where id=? ";
        List<ThemeScreenshot> list = (List<ThemeScreenshot>) super.findMassageDao(hql, id);
        if (list != null && list.size() == 1) {
            hql = "delete ThemeScreenshot where id=? and  tid in (select id from Theme where id=? and state=? and status in (?,?))";
            if (updateOneMssageDao(hql, id, tid, false, 0, -1)) {
                return list;
            }

        }

        return null;
    }
}
