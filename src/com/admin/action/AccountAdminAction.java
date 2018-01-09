package com.admin.action;

import com.admin.service.AccountAdminService;
import com.data.bean.Account;
import com.data.util.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * 账号管理
 */
@Controller
@RequestMapping("/accountAdmin/admin/")
public class AccountAdminAction {
    @Autowired
    private AccountAdminService accountAdminService;

    /**
     * 账号列表
     *
     * @param request
     * @return
     */
    @GetMapping("list")
    public String accountListAction(HttpServletRequest request) {
        List<Account> list = accountAdminService.accountListService();
        request.setAttribute("dataMap", list);

        return null;
    }

    /**
     * 删除账号
     *
     * @param id
     * @param request
     * @return
     */
    @PostMapping("delete")
    public Map<String, Object> deleteAccountAction(String id, HttpServletRequest request) {
        boolean bool = accountAdminService.deleteAccountService(id);
        return JsonUtils.returnMassageUtils(bool, bool ? "删除成功" : "删除失败");
    }

    /**
     * 修改账号状态
     *
     * @param id
     * @param state   账号的当当前状态
     * @param request
     * @return
     */
    @PostMapping("change")
    public Map<String, Object> changeAccountAction(String id, boolean state, HttpServletRequest request) {

        boolean bool = accountAdminService.changeAccountService(id, state);
        return JsonUtils.returnMassageUtils(bool, bool ? "修改成功" : "修改失败");
    }

    /**
     * 手工创建账号
     *
     * @param account 账号实体，包括用户名，邮箱，手机号，身份，状态
     * @param request
     * @return
     */
    @PostMapping("add")
    public Map<String, Object> addAccountAction(Account account, HttpServletRequest request) {

        boolean bool = accountAdminService.addAccountService(account);
        return JsonUtils.returnMassageUtils(bool, bool ? "创建成功" : "创建失败");
    }

    /**
     * @param username 用户名，邮箱，手机号
     * @param type     检测类型，email，username，phone
     * @param request
     * @return 如果是true，则表示验证通过，如果是false表示验证不通过
     */
    @PostMapping("check")
    public Map<String, Object> checkAccountAction(String username, String type, HttpServletRequest request) {
        boolean bool = accountAdminService.checkAccountService(username, type);
        return JsonUtils.returnMassageUtils(bool, bool ? "有效" : "无效");
    }


    public AccountAdminService getAccountAdminService() {
        return accountAdminService;
    }

    public void setAccountAdminService(AccountAdminService accountAdminService) {
        this.accountAdminService = accountAdminService;
    }
}
