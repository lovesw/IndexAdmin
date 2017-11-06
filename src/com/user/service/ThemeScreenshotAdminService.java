package com.user.service;

import com.data.bean.ThemeScreenshot;
import com.data.util.CheckDataUtils;
import com.data.util.DatabaseIdUtils;
import com.data.util.FinalStringUtils;
import com.data.util.ImageLookUtils;
import com.user.dao.ThemeScreenshotAdminDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.util.Date;
import java.util.List;

/**
 * FileName: ThemeScreenshotAdminService
 * Author:   HingLo
 * Date:     2017/11/6 12:02
 * Description: 截图管理的服务层
 **/
@Service
public class ThemeScreenshotAdminService {
    @Autowired
    private ThemeScreenshotAdminDao themeScreenshotAdminDao;

    public ThemeScreenshotAdminDao getThemeScreenshotAdminDao() {
        return themeScreenshotAdminDao;
    }

    public void setThemeScreenshotAdminDao(ThemeScreenshotAdminDao themeScreenshotAdminDao) {
        this.themeScreenshotAdminDao = themeScreenshotAdminDao;
    }

    public List<ThemeScreenshot> listThemeScreenshotAdminService(String id) {
        if (CheckDataUtils.stringUtilsID(id)) {
            return themeScreenshotAdminDao.listThemeScreenshotAdminDao(id);
        }
        return null;
    }

    /**
     * 删除图片
     *
     * @param id
     * @param tid
     * @return
     */
    public boolean deleteThemeScreenshotAdminService(String id, String tid) {
        if (CheckDataUtils.stringUtilsID(id, tid)) {
            List<ThemeScreenshot> list = themeScreenshotAdminDao.deleteThemeScreenshotAdminDao(id, tid);
            ThemeScreenshot themeScreenshot = list.get(0);
            String fileName = themeScreenshot.getImage();
            fileName = FinalStringUtils.THEMESCREENSHOT + fileName;
            return ImageLookUtils.deleteImage(fileName);
        }
        return false;

    }

    public byte[] showImageService(String imageName) {
        return ImageLookUtils.readImage(FinalStringUtils.THEMESCREENSHOT, imageName);
    }

    public boolean submitThemeScreenshotAdminService(ThemeScreenshot themeScreenshot, CommonsMultipartFile image) {
        if (CheckDataUtils.stringUtilsID(themeScreenshot.getTid())) {// 检测图片的主题的id是否有效
            String isuffix = ImageLookUtils.getFileSuffix(image);
            if (CheckDataUtils.iconUtils(isuffix)) {//检测截图是否是指定的图片后缀
                String imageName = DatabaseIdUtils.getDataIdOne() + isuffix;
                if (ImageLookUtils.saveFile(FinalStringUtils.THEMESCREENSHOT + imageName, image)) { //保存截图
                    themeScreenshot.setId(DatabaseIdUtils.getDataIdOne());// 生成图片名称
                    themeScreenshot.setDate(new Date());
                    themeScreenshot.setImage(imageName);
                    //保存到数据库中
                    return this.themeScreenshotAdminDao.saveMassageDao(themeScreenshot);
                }


            }


        }
        return false;

    }
}
