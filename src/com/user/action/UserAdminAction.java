package com.user.action;

import com.data.bean.Account;
import com.data.util.FinalStringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;


@Controller
@RequestMapping("/adminIndex/user/")
public class UserAdminAction {


    @GetMapping("index")
    public String indexAdminAction(HttpServletRequest request) {
        Account account = (Account) request.getSession().getAttribute(FinalStringUtils.ACCOUNT);
        if (account.getTypes() == FinalStringUtils.USER) {
            //处理首页相关


        }
        return "redirect:/login";


    }
}
