package com.cc.utils;

import org.apache.http.HttpResponse;

import javax.crypto.MacSpi;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.Key;
import java.util.HashMap;
import java.util.Map;

/**
 * cookie工具类
 *
 * @author cchen
 * @version 1.0
 * @date 2020/2/20 21:28
 */
public class CookieUtil {

    /**
     * 设置Cookie
     *
     * @param response
     * @param name
     * @param value
     * @param maxAge 过期时间
     */
    public static void set(HttpServletResponse response, String name, String value, Integer maxAge) {
        Cookie cookie = new Cookie(name, value);
        cookie.setPath("/");
        cookie.setMaxAge(maxAge);
        response.addCookie(cookie);
    }

    /**
     * 获取Cookie
     *
     * @param request
     * @param name
     * @return
     */
    public static Cookie get(HttpServletRequest request, String name) {
        Map<String, Cookie> cookieMap = readCookie2Map(request);
        if(cookieMap.containsKey(name)) {
            return cookieMap.get(name);
        }else {
            return null;
        }
    }

    /**
     * 将cookie封装成map
     *
     * @param request
     * @return
     */
    public static Map<String, Cookie> readCookie2Map(HttpServletRequest request) {
        HashMap<String, Cookie> cookieMap = new HashMap<>();
        Cookie[] cookies = request.getCookies();
        if(null != cookies) {
            for (Cookie cookie : cookies) {
                cookieMap.put(cookie.getName(), cookie);
            }
        }
        return cookieMap;
    }
}
