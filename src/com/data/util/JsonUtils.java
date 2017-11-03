package com.data.util;

import java.util.HashMap;
import java.util.Map;

/**
 * json格式
 *
 * @author:HingLo
 * @create 2017-09-27 18:13
 **/
public class JsonUtils {

    /**
     * 消息返回处理格式
     *
     * @param bool 是否成功
     * @param data 错误消息
     * @return
     */

    public static Map<String, Object> returnMassageUtils(boolean bool, Object data) {
        Map<String, Object> map = new HashMap<>();
        if (bool) {
            map.put("code", 200);
        } else {
            map.put("code", 400);
        }
        map.put("msg", data);
        return map;
    }

}
