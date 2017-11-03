package com.admin.service;

import com.admin.dao.AdminIndexDao;
import com.data.bean.IndexAdmin;
import com.data.util.CheckDataUtils;
import com.data.util.FinalStringUtils;
import com.data.util.ImageLookUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.List;

@Service
public class AdminIndexService {

    @Autowired
    private AdminIndexDao adminIndexDao;

    public AdminIndexDao getAdminIndexDao() {
        return adminIndexDao;
    }

    public void setAdminIndexDao(AdminIndexDao adminIndexDao) {
        this.adminIndexDao = adminIndexDao;
    }

    public List<IndexAdmin> indexBannerListService() {
        return adminIndexDao.indexBannerListDao();
    }

    public byte[] showImageService(String image) {
        String path;
        if (CheckDataUtils.stringUtils(image)) {
            path = FinalStringUtils.INDEXIMAGEPAHT + image;
        } else {
            //默认图片
            path = FinalStringUtils.INDEXIMAGEPAHT + FinalStringUtils.IMAGEDEFAULT;
        }
        File file = new File(path);
        if (file.isFile()) {
            return ImageLookUtils.readImage(file);
        }

        return null;
    }

    public boolean deleteBannerListService(String id) {
        List<IndexAdmin> list = this.adminIndexDao.deleteBannerListDao(id);
        if (list != null && !list.isEmpty()) {
            IndexAdmin indexAdmin = list.get(0);
            String bImage = indexAdmin.getBimage();
            indexAdmin.getImage();

        }
    }
}
