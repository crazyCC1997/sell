package com.cc.enums;

import lombok.Getter;

/**
 * 结果集枚举类
 *
 * @author cchen
 * @version 1.0
 * @date 2020/2/12 19:36
 */
@Getter
public enum ResultEnum {
    SUCCESS(0, "成功"),

    PARAM_ERROR(1, "参数不正确"),

    PRODUCT_NOT_EXIST(10, "商品不存在"),

    PRODUCT_STOCK_ERROR(11, "库存不足"),

    ORDER_NOT_EXIST(12, "订单不存在"),

    ORDER_DETAIL_NOT_EXIST(13, "订单详情不存在"),

    ORDER_STATUS_ERROR(14, "订单状态不正确"),

    ORDER_UPDATE_FAIL(15, "订单更新失败"),

    ORDER_DETAIL_EMPTY(16, "订单详情为空"),

    ORDER_PAY_STATUS_ERROR(17, "订单支付状态不正确"),

    CART_EMPTY(18, "购物车为空"),

    ORDER_OWNER_ERROR(19, "非本人订单"),

    WECHAT_MP_ERROR(20, "微信公众账号方面错误"),

    WXPAY_NOTIFY_MONEY_VERIFY_ERROR(21, "微信支付异步通知金额校验不通过"),

    ORDER_CANCEL_SUCCESS(22, "订单取消成功"),

    ORDER_FINISH_SUCCESS(23, "订单取消成功"),

    PRODUCT_STATUS_ERROR(24, "商品状态错误"),

    LOGIN_FAIL(25, "登录失败，登录信息不正确"),

    ;

    private Integer code;

    private String message;

    ResultEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }}
