package com.admin.util;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

public class VerificationCodeUtil {
    public static final int AUTHCODE_LENGTH = 5;// 验证码长度(个数)
    public static final int SINGLECODE_WIDTH = 15;// 单个验证码宽度
    public static final int SINGLECODE_HEIGHT = 30;// 单个验证码的高度
    public static final int SINGLECODE_GAP = 4;// 验证码之间的间隔
    public static final int IMG_WIDTH = AUTHCODE_LENGTH * (SINGLECODE_WIDTH + SINGLECODE_GAP);
    public static final int IMG_HEIGHT = SINGLECODE_HEIGHT;
    public static final String VERIFY_CODES = "23456789ABCDEFGHKMNPQRSTUVWXYZ";

    /**
     * 默认验证码长度的方式
     *
     * @return
     */
    public static String getAuthCode() {
        // 随机生产出验证码放在auth中
        int length = VERIFY_CODES.length();
        return getCode(length);
    }


    /**
     * 自定义长度的验证码生成方式
     *
     * @param length
     * @return
     */
    // 生成验证码
    public static String getAuthCode(int length) {
        // 随机生产出验证码放在auth中
        return getCode(length);
    }


    private static String getCode(int length) {
        StringBuilder auth = new StringBuilder(AUTHCODE_LENGTH);
        Random random = new Random();
        for (int i = 0; i < AUTHCODE_LENGTH; i++) {
            auth.append(VERIFY_CODES.charAt(random.nextInt(length)));
        }
        return auth.toString();
    }

    // 画验证码的图片
    public static BufferedImage getAuthImage(String authcode) {
        BufferedImage img = new BufferedImage(IMG_WIDTH, IMG_HEIGHT, BufferedImage.TYPE_INT_BGR);
        Graphics g = img.getGraphics();
        Random random = new Random();

        int a = random.nextInt(255);
        int b = random.nextInt(255);
        int cd = random.nextInt(255);
        Color color = new Color(a, b, cd);
        g.setColor(color);
        g.fillRect(0, 0, IMG_WIDTH, IMG_HEIGHT);
        g.setColor(Color.black);
        // 设置字体
        g.setFont(new Font("宋体", Font.PLAIN, SINGLECODE_HEIGHT + 5));
        char c;
        for (int i = 0, lenght = authcode.length(); i < lenght; i++) {
            c = authcode.charAt(i);
            g.drawString(c + "", i * (SINGLECODE_WIDTH + SINGLECODE_GAP) + SINGLECODE_GAP / 2, IMG_HEIGHT);

        }
        // 画干扰素
        ganRao(g);
        return img;
    }

    /**
     * 绘制干扰线的方法
     *
     * @param g
     */
    @SuppressWarnings("unused")
    private static void ganRao(Graphics g) {
        Random random = new Random();
        for (int i = 0; i < 20; i++) {
            int x = random.nextInt(IMG_WIDTH);
            int y = random.nextInt(IMG_HEIGHT);
            int x2 = random.nextInt(IMG_WIDTH);
            int y2 = random.nextInt(IMG_HEIGHT);
            g.drawLine(x, y, x + x2, y + y2);

        }
    }

}
