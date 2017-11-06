package com.data.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Random;
import java.util.UUID;

public class DatabaseIdUtils {
    private static final int SESSION_ID_BYTES = 16;


    /**
     * 随机生成一个不重复的唯一UU id
     *
     * @return
     */
    public static synchronized String getDataId() {
        String id = UUID.randomUUID().toString();
        return id;
    }

    /**
     * 生成一个10位数的唯一的个id，用于数据比较少的时候，
     *
     * @return
     */
    public static synchronized String getDataIdOne() {
        try {
            // 电脑休息1秒，免得重复了数据
            Thread.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        long currentTimeMillis = System.currentTimeMillis();
        return String.valueOf(currentTimeMillis);
    }

    /**
     * 生成一个唯一的id 与session相同的算法
     *
     * @return
     */
    public static synchronized String getSessionId() throws NoSuchAlgorithmException {
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        Random random = new SecureRandom();  // 取随机数发生器, 默认是SecureRandom
        byte bytes[] = new byte[SESSION_ID_BYTES];
        random.nextBytes(bytes); //产生16字节的byte
        bytes = md5.digest(bytes); // 取摘要,默认是"MD5"算法
        StringBuffer result = new StringBuffer();
        for (int i = 0; i < bytes.length; i++) {     //转化为16进制字符串
            byte b1 = (byte) ((bytes[i] & 0xf0) >> 4);
            byte b2 = (byte) (bytes[i] & 0x0f);
            if (b1 < 10)
                result.append((char) ('0' + b1));
            else
                result.append((char) ('A' + (b1 - 10)));
            if (b2 < 10)
                result.append((char) ('0' + b2));
            else
                result.append((char) ('A' + (b2 - 10)));
        }
        return (result.toString());
    }


}
