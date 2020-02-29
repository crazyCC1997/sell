package com.cc.utils;

/**
 * Math工具类
 *
 * @author cchen
 * @version 1.0
 * @date 2020/2/17 17:54
 */
public class MathUtil {

    private static final  Double MONEY_RANGE = 0.01;

    /**
     * 比较两个金额是否相等
     *
     * @param d1
     * @param d2
     * @return
     */
    public static boolean equals(Double d1, Double d2){
        if((Math.abs(d1 - d2)) < 0.01){
            return true;
        }else {
            return false;
        }
    }
}
