package com.cc.enums;

import lombok.Getter;
import org.aopalliance.reflect.Code;

/**
 * 商品状态枚举类
 *
 * @author cchen
 * @version 1.0
 * @date 2020/2/11 10:18
 */
@Getter
public enum ProductStatusEnum implements CodeEnum {
    UP(0,"在架"),
    DOWN(1, "下架")
    ;

    private Integer value;
    private String message;

    ProductStatusEnum(Integer value, String message) {
        this.value = value;
        this.message = message;
    }}
