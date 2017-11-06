package com.admin.action;

import com.admin.service.AdminIndexService;
import com.data.bean.Account;
import com.data.bean.IndexAdmin;
import com.data.util.FinalStringUtils;
import com.data.util.ImageLookUtils;
import com.data.util.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/adminIndex/admin/")
public class AdminIndexAction {
    @Autowired
    private AdminIndexService adminIndexService;


    /**
     * 进入到首页
     *
     * @param request
     * @return
     */
    @GetMapping("index")
    public String indexAdminAction(HttpServletRequest request) {
        Account account = (Account) request.getSession().getAttribute(FinalStringUtils.ACCOUNT);
        if (account.getTypes() == FinalStringUtils.ADMIN) {
            return "WEB-INF/admin/index";
        }
        return "redirect:/login";
    }

    /**
     * 进入到首页
     *
     * @param request
     * @return
     */
    @GetMapping("home")
    public String indexHomeAction(HttpServletRequest request) {
        //TODO://处理,统计信息，提示消息等
        return "WEB-INF/admin/home";

    }

    /**
     * 首页展示图信息展示
     *
     * @param request
     * @return
     */
    @GetMapping("list")
    public String indexBannerListAction(HttpServletRequest request) {
        List<IndexAdmin> list = adminIndexService.indexBannerListService();
        request.setAttribute("dataMap", list);
        return "WEB-INF/admin/index/indexList";
    }

    /**
     * 查看图片
     *
     * @param image
     * @param response
     */
    @GetMapping("image")
    public void showImageAction(String image, HttpServletResponse response) {
        byte[] inputStream = adminIndexService.showImageService(image);
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
    @GetMapping("delete")
    public Map<String, Object> deleteBannerListAction(String id, HttpServletRequest request) {
        boolean bool = adminIndexService.deleteBannerListService(id);
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
    public Map<String, Object> changeStateAction(String id, boolean state, HttpServletRequest request) {

        boolean bool = adminIndexService.changeStateService(id, state);
        return JsonUtils.returnMassageUtils(bool, bool ? "操作成功" : "操作失败");
    }

    /**
     * 进入到添加banner图的界面
     *
     * @param request
     * @return
     */
    @RequestMapping("addIndex")
    public String addIndexBannerAction(HttpServletRequest request) {

        return "WEB-INF/admin/index/addIndex";
    }

    /**
     * 添加banner的操作
     *
     * @param image   展示图
     * @param bImage  背景图
     * @param request
     * @return
     */
    @PostMapping("submitIndex")
    public String submitIndexBannerAction(@RequestParam("image") CommonsMultipartFile image, @RequestParam("bImage") CommonsMultipartFile bImage, String title, HttpServletRequest request) {
        boolean bool = adminIndexService.submitIndexBannerService(image, bImage, title);
        return bool ? "WEB-INF/admin/index/addIndex" : "404";
    }


    public AdminIndexService getAdminIndexService() {
        return adminIndexService;
    }

    public void setAdminIndexService(AdminIndexService adminIndexService) {
        this.adminIndexService = adminIndexService;
    }
}
