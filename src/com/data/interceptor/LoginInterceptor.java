package com.data.interceptor;

import com.data.bean.Account;
import com.data.util.FinalStringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginInterceptor implements HandlerInterceptor {
    /**
     * preHandler ：在进入Handler方法之前执行了，使用于身份认证，身份授权，登陆校验等，比如身份认证，用户没有登陆，拦截不再向下执行，返回值为 false ，即可实现拦截；否则，返回true时，拦截不进行执行；
     *
     * @param request
     * @param response
     * @param o
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
        Account account = (Account) request.getSession().getAttribute(FinalStringUtils.ACCOUNT);
        if (account == null) {
//            重定向到登录界面
            String url = "/login";
            response.sendRedirect(url);
            return false;
        } else {
            return true;
        }

    }

    /**
     * @param httpServletRequest
     * @param httpServletResponse
     * @param o
     * @param modelAndView
     * @throws Exception
     */
    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    /**
     * afterHandler : 在执行Handler完成后执行此方法，使用于统一的异常处理，统一的日志处理等；
     *
     * @param httpServletRequest
     * @param httpServletResponse
     * @param o
     * @param e
     * @throws Exception
     */
    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
