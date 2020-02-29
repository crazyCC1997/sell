package com.cc.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.util.List;

/**
 * json格式化工具
 *
 * @author cchen
 * @version 1.0
 * @date 2020/2/16 14:23
 */
public class JsonUtil {

    /**
     * 将对象转换为json格式
     *
     * @param object
     * @return
     */
    public static String toJson(Object object){
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setPrettyPrinting();
        Gson gson = gsonBuilder.create();
        return gson.toJson(object);
    }

    /**
     * 解析json
     * @param gsonString
     * @param cls
     * @param <T>
     * @return
     */
    public static <T> List<T> jsonToList(String gsonString, Class<T> cls){
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setPrettyPrinting();
        Gson gson = gsonBuilder.create();
        List<T> list = gson.fromJson(gsonString, new TypeToken<List<T>>() {
        }.getType());
        return  list;
    }
}
