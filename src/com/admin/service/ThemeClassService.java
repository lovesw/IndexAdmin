package com.admin.service;

import com.admin.dao.ThemeClassDao;
import com.data.bean.ThemeClass;
import com.data.util.CheckDataUtils;
import com.data.util.DatabaseIdUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * FileName: ThemeClassService
 * Author:   HingLo
 * Date:     2017/11/6 11:04
 * Description: 主题分类管理的服务层
 **/
@Service
public class ThemeClassService {
    @Autowired
    private ThemeClassDao themeClassDao;

    public ThemeClassDao getThemeClassDao() {
        return themeClassDao;
    }

    public void setThemeClassDao(ThemeClassDao themeClassDao) {
        this.themeClassDao = themeClassDao;
    }

    public List<ThemeClass> listThemeClassService() {
        return themeClassDao.listThemeClassDao();
    }

    public boolean changeThemeClassService(String id, String name) {
        if (CheckDataUtils.stringUtilsID(id) && CheckDataUtils.stringUtils(name)) {
            return themeClassDao.changeThemeClassDao(id, name);
        }
        return false;
    }

    public boolean submitThemeClassService(String name) {
        if (CheckDataUtils.stringUtils(name)) {
            ThemeClass themeClass = new ThemeClass();
            themeClass.setName(name);
            themeClass.setId(DatabaseIdUtils.getDataIdOne());
            themeClass.setDate(new Date());
            return themeClassDao.saveMassageDao(themeClass);
        }
        return false;
    }

    public boolean deleteThemeClassService(String id) {
        if (CheckDataUtils.stringUtilsID(id)) {
            return this.themeClassDao.deleteThemeClassDao(id);
        }
        return false;
    }
}
