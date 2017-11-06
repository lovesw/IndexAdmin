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
    /**
     * 图片响应
     *
     * @param inputStream
     * @param response
     */
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
    public static boolean saveFile(String path, CommonsMultipartFile inputStream) {
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
     * @param path 传入文件的路径
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
     * 读取图片，传入读取的文件对象
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

    /**
     * 读取图片，传入读取的文件对象
     *
     * @return 返回文件二进制文件
     */
    public static byte[] readImage(String PATH, String fileName) {
        String path;
        if (CheckDataUtils.stringUtils(fileName)) {
            path = PATH + fileName;
        } else {
            //默认图片
            path = PATH + FinalStringUtils.IMAGEDEFAULT;
        }
        File file = new File(path);
        if (file.isFile()) {
            return ImageLookUtils.readImage(file);
        }

        return null;
    }

    /**
     * 根据文件对象删除文件
     *
     * @param file
     * @return
     */
    public static boolean deleteImage(File file) {
        return file.delete();
    }

    /**
     * 根据文件对象删除文件
     *
     * @param path
     * @return
     */
    public static boolean deleteImage(String path) {
        File file = new File(path);
        if (file.isFile()) {
            return file.delete();
        }
        return false;
    }

    /**
     * 获取文件的后缀
     *
     * @param file
     * @return
     */
    public static String getFileSuffix(CommonsMultipartFile file) {
        if (file != null) {
            String filename = file.getOriginalFilename();
            String s = filename.substring(filename.lastIndexOf("."));//获取文件的后缀
            return s;
        }
        return null;


    }


}
