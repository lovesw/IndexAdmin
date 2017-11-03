package com.data.listenner;

import com.data.bean.Account;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.util.HashMap;

/**
 * 单一地点登陆监听器
 *
 * @author:HingLo
 * @create 2017-10-12 17:03
 **/
public class SessionLoginListener implements HttpSessionListener {
    public static HashMap<String, HttpSession> sessionMap = new HashMap<>();

    @Override
    public void sessionCreated(HttpSessionEvent httpSessionEvent) {

    }

    /**
     * 销毁session前的执行操作
     *
     * @param httpSessionEvent
     */
    @Override
    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {
        HttpSession session = httpSessionEvent.getSession();
        delSession(session);
    }

    public static synchronized void delSession(HttpSession session) {
        if (session != null) {
            // 删除单一登录中记录的变量
            if (session.getAttribute("Account") != null) {
                Account user = (Account) session.getAttribute("Account");
                SessionLoginListener.sessionMap.remove(user.getId());
            }
        }
    }
}
