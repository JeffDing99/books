package com.ding.books.utils;

import com.alibaba.fastjson.JSONObject;

/**
 * @Description TODO
 * @Author 丁帅帅
 * @Date 21/11/21 09:58
 * @Version 1.0
 */
public class JSONUtils {
    /**
     * 转换json对象
     */
    public static JSONObject parseJSONP(String jsonp) {
        int startIndex = jsonp.indexOf("(");
        int endIndex = jsonp.lastIndexOf(")");
        String json = jsonp.substring(startIndex + 1, endIndex);
        return JSONObject.parseObject(json);
    }
}
