package com.cc.enums;

import com.cc.pojo.OrderMaster;
import lombok.Getter;

/**
 * 订单状态枚举类
 *
 * @author cchen
 * @version 1.0
 * @date 2020/2/12 11:12
 */
@Getter
public enum OrderStatusEnum implements CodeEnum {

    NEW(0, "新订单"),
    FINISHED(1, "完结"),
    CANCEL(2, "已取消"),
    ;

    private Integer value;

    private String message;

    OrderStatusEnum(Integer value, String message) {
        this.value = value;
        this.message = message;
    }

    public static OrderStatusEnum getOrderStatusEnum(Integer code) {
        for (OrderStatusEnum orderStatusEnum : OrderStatusEnum.values()) {
            if (orderStatusEnum.getValue().equals(code)) {
                return orderStatusEnum;
            }
        }
        return null;
    }
}
