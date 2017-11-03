package com.data.action;

import com.data.bean.Account;
import com.data.service.LoginService;
import com.data.util.FinalStringUtils;
import com.data.util.VerificationCodeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.imageio.ImageIO;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;

@Controller
public class LoginAction {


    @Autowired
    private LoginService loginService;

    @RequestMapping("login")
    public String loginInputAction(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null)
            for (Cookie c : cookies) {
                if (c.getName().equals("username")) {
                    request.setAttribute("username", c.getValue());
                } else if (c.getName().equals("password")) {
                    request.setAttribute("password", c.getValue());
                }
            }
        return "login";
    }


    /**
     * 请求验证码
     *
     * @param request
     * @param response
     * @throws IOException
     */
    @RequestMapping(value = "/authcode", method = RequestMethod.GET)
    public void getAuthCodeAction(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String authcode = VerificationCodeUtil.getAuthCode();//获取验证码
        request.getSession().setAttribute("authcode", authcode);//添加验证码到session中
        BufferedImage authImage = VerificationCodeUtil.getAuthImage(authcode);
        byte[] inputStream = convertImageToStream(authImage);
        response.setContentType("image/png");
        OutputStream stream = response.getOutputStream();
        stream.write(inputStream);
        stream.flush();
        stream.close();
    }


    @GetMapping("submitLogin")
    public String loinAction(String username, String password, String ucode, HttpServletRequest request) {
        String ERROR = "验证码错误";
        if (authCodeCheckAction(ucode, request)) {
            Account account = loginService.loginService(username, password);
            if (account != null) {
                request.getSession().setAttribute(FinalStringUtils.ACCOUNT, account);
                if (account.getTypes() == FinalStringUtils.ADMIN) {
                    return "redirect:/";
                } else if (account.getTypes() == FinalStringUtils.USER) {
                    return "redirect:/";
                } else {
                    ERROR = "登录出错";
                }

            } else
                ERROR = "账号或者密码错误";
        }
        request.setAttribute("username", username);
        request.setAttribute("error", ERROR);
        return "login";
    }

    @GetMapping("index")
    public String loginIndex(HttpServletRequest request) {
        Account account = (Account) request.getSession().getAttribute(FinalStringUtils.ACCOUNT);
        if (account.getTypes() == FinalStringUtils.ADMIN) {
            return "redirect:/";
        } else if (account.getTypes() == FinalStringUtils.USER) {
            return "redirect:/";
        }
        return "login";


    }


    /**
     * 验证码校验方式
     *
     * @param ucode
     * @param request
     * @return
     */
    private boolean authCodeCheckAction(String ucode, HttpServletRequest request) {
        String authcode = (String) request.getSession().getAttribute("authcode");
        ucode = ucode.trim().toLowerCase();
        // 如果验证码是空，就赋值为空格
        authcode = authcode != null ? authcode.toLowerCase() : " ";
        boolean bool = authcode.equals(ucode) ? true : false;
        return bool;
    }

    /**
     * 将图片转化为字节流
     *
     * @param image
     * @return
     */
    private byte[] convertImageToStream(BufferedImage image) {
        ByteArrayInputStream inputStream = null;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        try {
            ImageIO.write(image, "jpeg", out);// 将图片流写入发哦输出流中
        } catch (IOException e) {

            e.printStackTrace();
        }
        byte[] bts = out.toByteArray();// 讲图片流转化为字节数组流
        return bts;
    }

    public LoginService getLoginService() {
        return loginService;
    }

    public void setLoginService(LoginService loginService) {
        this.loginService = loginService;
    }
}
