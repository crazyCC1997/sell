package com.cc.utils;

import java.util.Random;

/**
 * 数据库主键随机数工具类
 *
 * @author cchen
 * @version 1.0
 * @date 2020/2/13 0:07
 */
public class KeyUtil {

    /**
     * 生成唯一主键 时间+随机数
     * @return
     */
    public static synchronized String genUniqueKey(){
        Random random = new Random();
        Integer number = random.nextInt(9000000) + 10000;
        return System.currentTimeMillis() + String.valueOf(number);
    }
}
