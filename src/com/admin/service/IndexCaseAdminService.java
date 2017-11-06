package com.admin.service;

import com.admin.dao.IndexCaseAdminDao;
import com.data.bean.IndexCase;
import com.data.util.CheckDataUtils;
import com.data.util.DatabaseIdUtils;
import com.data.util.FinalStringUtils;
import com.data.util.ImageLookUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.util.Date;
import java.util.List;

/**
 * FileName: IndexCaseAdminService
 * Author:   HingLo
 * Date:     2017/11/6 10:05
 * Description: 案例管理的服务层
 **/
@Service
public class IndexCaseAdminService {
    @Autowired
    private IndexCaseAdminDao indexCaseAdminDao;

    public IndexCaseAdminDao getIndexCaseAdminDao() {
        return indexCaseAdminDao;
    }

    public void setIndexCaseAdminDao(IndexCaseAdminDao indexCaseAdminDao) {
        this.indexCaseAdminDao = indexCaseAdminDao;
    }

    public List<IndexCase> listIndexCaseService() {
        return this.indexCaseAdminDao.listIndexCaseDao();
    }

    public byte[] showImageService(String image) {
        return ImageLookUtils.readImage(FinalStringUtils.INDEXCASEIMAGEPAHT, image);
    }

    public boolean deleteIndexCaseService(String id) {
        if (CheckDataUtils.stringUtilsID(id)) {
            List<IndexCase> list = this.indexCaseAdminDao.deleteIndexCaseDao(id);
            if (list != null && list.size() == 1) {
                IndexCase indexCase = list.get(0);
                String imageName = indexCase.getImage();//获取文件的名称
                String path = FinalStringUtils.INDEXCASEIMAGEPAHT + imageName;
                return ImageLookUtils.deleteImage(path);
            }
        }
        return false;
    }

    public boolean changeStateService(String id, boolean state) {
        if (CheckDataUtils.stringUtilsID(id)) {
            return indexCaseAdminDao.changeStateDao(id, state);
        }
        return false;
    }

    public boolean submitIndexCaseService(CommonsMultipartFile image, IndexCase indexCase) {
        String isuffix = ImageLookUtils.getFileSuffix(image);
        if (CheckDataUtils.iconUtils(isuffix)) {
            String fileName = DatabaseIdUtils.getDataIdOne() + isuffix;
            if (ImageLookUtils.saveFile(FinalStringUtils.INDEXCASEIMAGEPAHT + fileName, image)) { //保存背景图
                indexCase.setId(DatabaseIdUtils.getDataIdOne());
                indexCase.setDate(new Date());
                //保存到数据库中
                return this.indexCaseAdminDao.saveMassageDao(indexCase);
            }


        }


        return false;

    }
}
