package com.cc.constant;

/**
 * redis常量
 *
 * @author cchen
 * @version 1.0
 * @date 2020/2/20 21:06
 */
public interface RedisConstant {

    /**
     * 前缀
     */
    String TOKEN_PREFIX = "token_%s";

    /**
     * 过期时间
     */
    Integer EXPIRE = 7200;
}
