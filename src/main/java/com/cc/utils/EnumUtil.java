package com.cc.utils;

import com.cc.enums.CodeEnum;

/**
 * 枚举工具类
 *
 * @author cchen
 * @version 1.0
 * @date 2020/2/18 13:18
 */
public class EnumUtil {
    public static <T extends CodeEnum> T getByCode(Integer code, Class<T> enumClass) {
        for (T each : enumClass.getEnumConstants()) {
            if(code.equals(each.getValue())){
                return each;
            }
        }
        return null;
    }
}
