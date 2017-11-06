package com.admin.action;

import com.admin.service.ThemeClassService;
import com.data.bean.ThemeClass;
import com.data.util.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * FileName: ThemeClassAction
 * Author:   HingLo
 * Date:     2017/11/6 11:02
 * Description: 主题案例分类
 **/
@Controller
@RequestMapping("themeClass/admin/")
public class ThemeClassAction {
    @Autowired
    private ThemeClassService themeClassService;

    /****
     *
     * 查看主题分类信息
     * @param request
     * @return
     */
    @RequestMapping("listThemeClass")
    public String listThemeClassAction(HttpServletRequest request) {
        List<ThemeClass> list = this.themeClassService.listThemeClassService();
        request.setAttribute("datMap", list);
        return "WEB-INF/admin/case/listCase";
    }

    /**
     * 修改分类信息
     *
     * @param id
     * @param name
     * @param request
     * @return
     */
    @PostMapping("changeThemeClass")
    @ResponseBody
    public Map<String, Object> changeThemeClassAction(String id, String name, HttpServletRequest request) {
        boolean bool = this.themeClassService.changeThemeClassService(id, name);

        return JsonUtils.returnMassageUtils(bool, bool ? "修改成功" : "修改失败");
    }

    /**
     * 进入到添加主题界面
     *
     * @param request
     * @return
     */
    @RequestMapping("addThemeClass")
    public String addThemeClassAction(HttpServletRequest request) {

        return "WEB-INF/admin/case/addCase";
    }

    /**
     * 提交添加主题分类
     *
     * @param name
     * @param request
     * @return
     */
    @PostMapping("submitThemeClass")
    @ResponseBody
    public Map<String, Object> submitThemeClassAction(String name, HttpServletRequest request) {
        boolean bool = this.themeClassService.submitThemeClassService(name);
        return JsonUtils.returnMassageUtils(bool, bool ? "添加成功" : "添加失败");
    }
    /**
     * 删除主题分类
     *
     * @param id
     * @param request
     * @return
     */
    @PostMapping("deleteThemeClass")
    @ResponseBody
    public Map<String, Object> deleteThemeClassAction(String id, HttpServletRequest request) {
        boolean bool = this.themeClassService.deleteThemeClassService(id);
        return JsonUtils.returnMassageUtils(bool, bool ? "删除成功" : "删除失败");
    }


    public ThemeClassService getThemeClassService() {
        return themeClassService;
    }

    public void setThemeClassService(ThemeClassService themeClassService) {
        this.themeClassService = themeClassService;
    }
}
