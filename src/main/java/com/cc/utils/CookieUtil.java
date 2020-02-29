package com.cc.utils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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

    public static void get() {
    }
}
