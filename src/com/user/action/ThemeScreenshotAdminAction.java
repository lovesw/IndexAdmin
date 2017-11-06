package com.user.action;

import com.data.bean.ThemeScreenshot;
import com.data.util.ImageLookUtils;
import com.data.util.JsonUtils;
import com.user.service.ThemeScreenshotAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * FileName: ThemeScreenshotAdminAction
 * Author:   HingLo
 * Date:     2017/11/6 12:01
 * Description: 截图管理
 **/
@Controller
@RequestMapping("themeScreenshot/user/")
public class ThemeScreenshotAdminAction {
    @Autowired
    private ThemeScreenshotAdminService themeScreenshotAdminService;

    /**
     * 根据id查询截图
     *
     * @param id
     * @param request
     * @return
     */
    @RequestMapping("listThemeScreenshotAdmin")
    public String listThemeScreenshotAdminActon(String id, HttpServletRequest request) {
        List<ThemeScreenshot> list = this.themeScreenshotAdminService.listThemeScreenshotAdminService(id);
        return "WEB-INF/admin/themeScreenshot/listThemeScreenshot";
    }

    /**
     * 查看图片
     *
     * @param image    图片的名称
     * @param response
     */
    @GetMapping("image")
    public void showImageAction(String image, HttpServletResponse response) {
        byte[] inputStream = themeScreenshotAdminService.showImageService(image);
        //响应图片
        if (inputStream != null)
            ImageLookUtils.responseImage(inputStream, response);

    }

    /**
     * 根据id删除未上线的主题截图
     *
     * @param id      截图id
     * @param tid     截图所属的主题id
     * @param request
     * @return
     */
    @RequestMapping("deleteThemeScreenshotAdmin")
    @ResponseBody
    public Map<String, Object> deleteThemeScreenshotAdminActon(String id, String tid, HttpServletRequest request) {
        boolean bool = this.themeScreenshotAdminService.deleteThemeScreenshotAdminService(id, tid);
        return JsonUtils.returnMassageUtils(bool, bool ? "删除成功" : "删除失败");
    }

    /**
     * 添加截图
     *
     * @param themeScreenshot 截图实体信息
     * @param image           截图
     * @param request
     * @return
     */
    @RequestMapping("submitThemeScreenshotAdmin")
    @ResponseBody
    public Map<String, Object> submitThemeScreenshotAdminActon(ThemeScreenshot themeScreenshot, @RequestParam("image") CommonsMultipartFile image, HttpServletRequest request) {
        boolean bool = this.themeScreenshotAdminService.submitThemeScreenshotAdminService(themeScreenshot, image);
        return JsonUtils.returnMassageUtils(bool, bool ? "添加成功" : "添加失败");
    }


    public ThemeScreenshotAdminService getThemeScreenshotAdminService() {
        return themeScreenshotAdminService;
    }

    public void setThemeScreenshotAdminService(ThemeScreenshotAdminService themeScreenshotAdminService) {
        this.themeScreenshotAdminService = themeScreenshotAdminService;
    }
}
