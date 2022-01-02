package com.ding.books.utils;

import cn.hutool.crypto.SecureUtil;
import sun.misc.BASE64Decoder;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.Security;

/**
 * 工具类
 */
public class CommonUtils {


    /**
     * MD5加密工具类
     * @param data
     * @return
     */
    public static String MD5(String data)  {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] array = md.digest(data.getBytes("UTF-8"));
            StringBuilder sb = new StringBuilder();
            for (byte item : array) {
                sb.append(Integer.toHexString((item & 0xFF) | 0x100).substring(1, 3));
            }

            return sb.toString().toUpperCase();
        } catch (Exception exception) {
        }
        return null;

    }






}
