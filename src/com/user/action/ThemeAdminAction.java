package com.user.action;

import com.data.bean.Account;
import com.data.bean.Theme;
import com.data.util.FinalStringUtils;
import com.data.util.ImageLookUtils;
import com.data.util.JsonUtils;
import com.user.service.ThemeAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * FileName: ThemeAdminAction
 * Author:   HingLo
 * Date:     2017/11/6 11:40
 * Description: 主题管理
 **/
@Controller
@RequestMapping("themeAdmin/user/")
public class ThemeAdminAction {
    @Autowired
    private ThemeAdminService themeAdminService;


    @GetMapping("listTheme")
    public String listThemeAdminAction(HttpServletRequest request) {
        Account account = (Account) request.getSession().getAttribute(FinalStringUtils.ACCOUNT);
        List<Theme> list = themeAdminService.listThemeAdminService(account.getId());
        request.setAttribute("dataMap", list);
        return "WEB-INF/admin/theme/listTheme";
    }

    /**
     * 查看图片
     *
     * @param image    图片的名称
     * @param response
     */
    @GetMapping("image")
    public void showImageAction(String image, HttpServletResponse response) {
        byte[] inputStream = themeAdminService.showImageService(image);
        //响应图片
        if (inputStream != null)
            ImageLookUtils.responseImage(inputStream, response);

    }

    @GetMapping("addTheme")
    public String addThemeAdminAction(HttpServletRequest request) {

        return "WEB-INF/admin/theme/listTheme";
    }


    /**
     * 添加主题
     *
     * @param image
     * @param bImage
     * @param theme
     * @param request
     * @return
     */
    @PostMapping("submitThemeAdmin")
    @ResponseBody
    public Map<String, Object> submitThemeAdminAction(@RequestParam("image") CommonsMultipartFile image, @RequestParam("bImage") CommonsMultipartFile bImage, Theme theme, HttpServletRequest request) {

        Account account = (Account) request.getSession().getAttribute(FinalStringUtils.ACCOUNT);
        theme.setUid(account.getId());

        boolean bool = themeAdminService.submitThemeAdminService(image, bImage, theme);
        return JsonUtils.returnMassageUtils(bool, bool ? "添加成功" : "添加失败");
    }

    /**
     * 提交审核
     *
     * @param id
     * @return
     */

    @PostMapping("changeTheme")
    @ResponseBody
    public Map<String, Object> changeThemeAdminAction(String id) {
        boolean bool = themeAdminService.changeThemeAdminService(id);
        return JsonUtils.returnMassageUtils(bool, bool ? "提交成功" : "提交失败");
    }

    /**
     * 删除未上线的主题
     *
     * @param id
     * @return
     */

    @PostMapping("deleteTheme")
    @ResponseBody
    public Map<String, Object> deleteThemeAdminAction(String id) {
        boolean bool = themeAdminService.deleteThemeAdminService(id);
        return JsonUtils.returnMassageUtils(bool, bool ? "删除成功" : "删除失败");
    }


}
