package com.cc.form;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * 订单数据表单
 *
 * @author cchen
 * @version 1.0
 * @date 2020/2/13 22:08
 */
@Data
public class OrderForm {

    /**
     * 买家姓名
     */
    @NotEmpty(message = "姓名不能为空")
    private String name;

    /**
     * 买家手机号
     */
    @NotEmpty(message = "手机号不能为空")
    private String phone;

    /**
     * 买家地址
     */
    @NotEmpty(message = "地址必填")
    private String address;

    /**
     * 买家openid
     */
    @NotEmpty(message = "openid必填")
    private String openid;

    /**
     * 购物车信息
     */
    @NotEmpty(message = "购物车信息不能为空")
    private String items;
}
