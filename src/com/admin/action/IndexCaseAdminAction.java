package com.admin.action;

import com.admin.service.IndexCaseAdminService;
import com.data.bean.IndexCase;
import com.data.util.ImageLookUtils;
import com.data.util.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * FileName: IndexCaseAdminAction
 * Author:   HingLo
 * Date:     2017/11/6 10:02
 * Description: 首页中的案例管理
 **/
@Controller
@RequestMapping("indexCase/admin/")
public class IndexCaseAdminAction {
    @Autowired
    private IndexCaseAdminService indexCaseAdminService;


    @RequestMapping("listIndexCase")
    public String listIndexCaseAction(HttpServletRequest request) {

        List<IndexCase> list = this.indexCaseAdminService.listIndexCaseService();
        request.setAttribute("datMap", list);
        return "WEB-INF/admin/index/indexCase";
    }

    /**
     * 查看图片
     *
     * @param image    图片的名称
     * @param response
     */
    @GetMapping("image")
    public void showImageAction(String image, HttpServletResponse response) {
        byte[] inputStream = indexCaseAdminService.showImageService(image);
        //响应图片
        if (inputStream != null)
            ImageLookUtils.responseImage(inputStream, response);

    }

    /**
     * 删除展示图信息展示
     *
     * @param request
     * @return
     */
    @PostMapping("delete")
    @ResponseBody
    public Map<String, Object> deleteIndexCaseAction(String id, HttpServletRequest request) {
        boolean bool = indexCaseAdminService.deleteIndexCaseService(id);
        return JsonUtils.returnMassageUtils(bool, bool ? "删除成功" : "删除失败");
    }

    /**
     * 修改这个首页的展示图的状态，是否启用，或者禁用
     *
     * @param id
     * @param state
     * @param request
     * @return
     */
    @PostMapping("changeState")
    @ResponseBody
    public Map<String, Object> changeStateAction(String id, boolean state, HttpServletRequest request) {

        boolean bool = indexCaseAdminService.changeStateService(id, state);
        return JsonUtils.returnMassageUtils(bool, bool ? "操作成功" : "操作失败");
    }

    /**
     * 进入到添加banner图的界面
     *
     * @param request
     * @return
     */
    @RequestMapping("addIndex")
    public String addIndexCaseAction(HttpServletRequest request) {

        return "WEB-INF/admin/index/addCase";
    }

    /**
     * 添加banner的操作
     *
     * @param image   展示图
     * @param request
     * @return
     */
    @PostMapping("submitIndex")
    public String submitIndexCaseAction(@RequestParam("image") CommonsMultipartFile image, IndexCase indexCase, HttpServletRequest request) {
        boolean bool = indexCaseAdminService.submitIndexCaseService(image, indexCase);
        return bool ? "WEB-INF/admin/index/addCase" : "404";
    }


    public IndexCaseAdminService getIndexCaseAdminService() {
        return indexCaseAdminService;
    }

    public void setIndexCaseAdminService(IndexCaseAdminService indexCaseAdminService) {
        this.indexCaseAdminService = indexCaseAdminService;
    }
}
