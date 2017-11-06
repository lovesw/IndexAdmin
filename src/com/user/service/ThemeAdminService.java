package com.user.service;

import com.data.bean.Theme;
import com.data.util.CheckDataUtils;
import com.data.util.DatabaseIdUtils;
import com.data.util.FinalStringUtils;
import com.data.util.ImageLookUtils;
import com.user.dao.ThemeAdminDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.util.Date;
import java.util.List;

/**
 * FileName: ThemeAdminService
 * Author:   HingLo
 * Date:     2017/11/6 11:41
 * Description: 主题管理的服务层
 **/
@Service
public class ThemeAdminService {
    @Autowired
    private ThemeAdminDao themeAdminDao;

    public ThemeAdminDao getThemeAdminDao() {
        return themeAdminDao;
    }

    public void setThemeAdminDao(ThemeAdminDao themeAdminDao) {
        this.themeAdminDao = themeAdminDao;
    }

    public List<Theme> listThemeAdminService(String id) {
        if (CheckDataUtils.stringUtilsID(id)) {
            return themeAdminDao.listThemeAdminDao(id);
        }
        return null;
    }

    public byte[] showImageService(String imageName) {
        return ImageLookUtils.readImage(FinalStringUtils.THEME, imageName);
    }

    public boolean submitThemeAdminService(CommonsMultipartFile image, CommonsMultipartFile bImage, Theme theme) {
        String isuffix = ImageLookUtils.getFileSuffix(image);
        String bsuffix = ImageLookUtils.getFileSuffix(bImage);
        if (CheckDataUtils.iconUtils(isuffix) && CheckDataUtils.iconUtils(bsuffix)) {
            String imageName = DatabaseIdUtils.getDataIdOne() + isuffix;
            theme.setImage(imageName);
            if (ImageLookUtils.saveFile(FinalStringUtils.THEME + imageName, image)) {//保存前景图
                imageName = DatabaseIdUtils.getDataIdOne() + bsuffix;
                if (ImageLookUtils.saveFile(FinalStringUtils.THEME + imageName, image)) { //保存背景图
                    //保存到数据库中
                    theme.setBimage(imageName);
                    theme.setId(DatabaseIdUtils.getDataIdOne());
                    theme.setDate(new Date());
                    theme.setState(false);//默认状态未为上线
                    theme.setStatus(0);//默认状态未提交审核
                    return this.themeAdminDao.saveMassageDao(theme);
                }
            }
        }
        return false;
    }

    public boolean changeThemeAdminService(String id) {
        if (CheckDataUtils.stringUtilsID(id))
            return themeAdminDao.changeThemeAdminDao(id);
        return false;
    }

    public boolean deleteThemeAdminService(String id) {
        if (CheckDataUtils.stringUtilsID(id))
            return themeAdminDao.deleteThemeAdminDao(id);
        return false;
    }
}
