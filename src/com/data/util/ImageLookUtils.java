package com.data.util;

import org.apache.commons.io.FileUtils;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;

/**
 * 图片响应
 *
 * @author:HingLo
 * @create 2017-09-28 17:13
 **/
public class ImageLookUtils {

    public static void responseImage(byte[] inputStream, HttpServletResponse response) {
        try {
            response.setContentType("image/png");
            OutputStream stream = response.getOutputStream();
            stream.write(inputStream);
            stream.flush();
            stream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 保存
     * * @param path
     *
     * @param inputStream
     * @return
     */
    public static boolean uploadFile(String path, CommonsMultipartFile inputStream) {
        try {
            FileUtils.copyInputStreamToFile(inputStream.getInputStream(), new File(path));
            return true;
        } catch (IOException e) {
            return false;
        }


    }

    /**
     * 读取图片
     *
     * @param path
     * @return 返回文件二进制文件
     */
    public static byte[] readImage(String path) {
        File file = new File(path);
        try {
            return FileUtils.readFileToByteArray(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 读取图片
     *
     * @return 返回文件二进制文件
     */
    public static byte[] readImage(File file) {
        try {
            return FileUtils.readFileToByteArray(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
