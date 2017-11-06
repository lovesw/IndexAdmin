package com.admin.service;

import com.admin.dao.AdminIndexDao;
import com.data.bean.IndexAdmin;
import com.data.util.CheckDataUtils;
import com.data.util.DatabaseIdUtils;
import com.data.util.FinalStringUtils;
import com.data.util.ImageLookUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.File;
import java.util.Date;
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
        return ImageLookUtils.readImage(FinalStringUtils.INDEXIMAGEPAHT, image);
    }

    /**
     * 删除失败
     *
     * @param id
     * @return
     */
    public boolean deleteBannerListService(String id) {
        List<IndexAdmin> list = this.adminIndexDao.deleteBannerListDao(id);
        if (list != null && list.size() == 0) {
            IndexAdmin indexAdmin = list.get(0);
            //获取删除的图片的名称
            String bImage = indexAdmin.getBimage();
            String imageName = indexAdmin.getImage();
            //组装图片的路径
            String[] path = {FinalStringUtils.INDEXIMAGEPAHT + imageName, FinalStringUtils.INDEXIMAGEPAHT + bImage};
            //循环删除图片，这个过程可能会删除失败，但是数据库中已经删除，如果删除失败，文件就是垃圾文件。只能远程手工删除
            for (String fileName : path) {
                ImageLookUtils.deleteImage(fileName);
            }
            return true;
        }
        return false;
    }

    public boolean changeStateService(String id, boolean state) {
        if (CheckDataUtils.stringUtilsID(id)) {
            this.adminIndexDao.changeStateDao(id, state);
        }
        return false;
    }

    /**
     * 服务层的保存图片
     *
     * @param image
     * @param bImage
     * @param title
     * @return
     */
    public boolean submitIndexBannerService(CommonsMultipartFile image, CommonsMultipartFile bImage, String title) {
        String isuffix = ImageLookUtils.getFileSuffix(image);
        String bsuffix = ImageLookUtils.getFileSuffix(bImage);
        if (CheckDataUtils.iconUtils(isuffix) && CheckDataUtils.iconUtils(bsuffix)) {
            IndexAdmin indexAdmin = new IndexAdmin();
            //前景图的名称
            String path = DatabaseIdUtils.getDataIdOne() + isuffix;
            indexAdmin.setImage(path);//设置第一张图
            if (ImageLookUtils.saveFile(FinalStringUtils.INDEXIMAGEPAHT + path, image)) {//保存前景图
                path = DatabaseIdUtils.getDataIdOne() + bsuffix;
                if (ImageLookUtils.saveFile(FinalStringUtils.INDEXIMAGEPAHT + path, image)) { //保存背景图
                    //保存到数据库中
                    indexAdmin.setBimage(path);//设置第二张图
                    indexAdmin.setId(DatabaseIdUtils.getDataIdOne());
                    indexAdmin.setTitle(title);
                    indexAdmin.setDate(new Date());
                    return this.adminIndexDao.saveMassageDao(indexAdmin);
                }
            }
        }
        return false;
    }
}
