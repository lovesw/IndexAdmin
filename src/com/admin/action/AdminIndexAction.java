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
import org.springframework.web.bind.annotation.RequestMapping;

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
            //处理首页相关
            //TODO://处理
        }
        return "redirect:/login";
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
        return null;
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


    public AdminIndexService getAdminIndexService() {
        return adminIndexService;
    }

    public void setAdminIndexService(AdminIndexService adminIndexService) {
        this.adminIndexService = adminIndexService;
    }
}
