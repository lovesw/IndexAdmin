package com.data.util;

import java.util.regex.Pattern;

/**
 * 数据校验方法
 *
 * @author:HingLo
 * @create 2017-09-13 11:44
 **/
public class CheckDataUtils {

    /***
     * 邮箱格式校验
     * @param email
     * @return
     */
    public static boolean checkEmail(String email) {
        String pattern = "^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+$";
        return Pattern.matches(pattern, email);
    }


    /**
     * 判断字符串是空与“”
     *
     * @param str
     * @return
     */
    public static boolean stringUtils(String... str) {
        for (String s : str) {
            if (s == null || "".equals(s.trim()))
                return false;
        }
        return true;
    }

    /**
     * 只能包含，数字，字符，下划线，与中划线
     *
     * @param str
     * @return
     */
    public static boolean stringUtilsID(String... str) {
        String pattern = "^[A-Za-z0-9_-]+$";
        for (String s : str) {
            if (s == null || !Pattern.matches(pattern, s))
                return false;
        }
        return true;


    }

    /**
     * .
     * 检测后缀是是否是一个图片格式
     *
     * @param icon
     * @return
     */
    public static boolean iconUtils(String icon) {
        String[] s = {".PNG", ".JPG", ".GIF", ".JPEG"};
        if (stringUtils(icon)) {
            icon = icon.toUpperCase();
            for (String ss : s) {
                if (icon.equals(ss))
                    return true;
            }
        }
        return false;
    }

    /**
     * 视频格式检测
     *
     * @param icon
     * @return
     */
    public static boolean videoUtils(String icon) {
        String[] s = {".SWF", ".AVI", ".MP4", ".MRVB"};
        icon = icon.toUpperCase();
        for (String ss : s) {
            if (icon.equals(ss))
                return true;
        }
        return false;
    }

    public static void main(String[] args) {
        System.out.println(CheckDataUtils.stringUtilsID("123-fdas4_fd5"));
    }
}
