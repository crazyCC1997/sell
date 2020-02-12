package com.cc.enums;

import lombok.Getter;

/**
 * 支付状态枚举类
 *
 * @author cchen
 * @version 1.0
 * @date 2020/2/12 11:21
 */
@Getter
public enum PayStatusEnum {

    WAIT(0, "等待支付"),
    SUCCESS(1, "支付成功")
    ;

    private Integer value;

    private String message;

    PayStatusEnum(Integer value, String message) {
        this.value = value;
        this.message = message;
    }}
