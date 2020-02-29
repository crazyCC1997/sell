package com.cc.dto;

import com.cc.enums.OrderStatusEnum;
import com.cc.enums.PayStatusEnum;
import com.cc.pojo.OrderDetail;
import com.cc.pojo.OrderMaster;
import com.cc.utils.EnumUtil;
import com.cc.utils.serializer.Date2LongSerializer;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 订单DTO DTO:数据传输对象
 *
 * @author cchen
 * @version 1.0
 * @date 2020/2/12 18:17
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
//@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL) //过时
//@JsonInclude(JsonInclude.Include.NON_NULL)   //只要求json字符串中不为空的返回
public class OrderMasterDTO {

    /**
     * 订单id
     */
    @Id
    private String orderId;

    /**
     * 买家姓名
     */
    private String buyerName;

    /**
     * 买家电话
     */
    private String buyerPhone;

    /**
     * 买家地址
     */
    private String buyerAddress;

    /**
     * 买家openId
     */
    private String buyerOpenid;

    /**
     * 订单总金额
     */
    private BigDecimal orderAmount;

    /**
     * 订单状态 默认 0：新订单
     */
    private Integer orderStatus;

    /**
     * 支付状态 默认 0：等待支付
     */
    private Integer payStatus;

    /**
     * 创建时间
     */
    @JsonSerialize(using = Date2LongSerializer.class)
    private Date createTime;

    /**
     * 修改时间
     */
    @JsonSerialize(using = Date2LongSerializer.class)
    private Date updateTime;

    /**
     * 订单详情列表
     */
    private List<OrderDetail> orderDetailList;

    @JsonIgnore
    // 对象转json格式时，将忽略此方法
    public OrderStatusEnum getOrderStatusEnum() {
        return EnumUtil.getByCode(orderStatus, OrderStatusEnum.class);
    }

    @JsonIgnore
    // 对象转json格式时，将忽略此方法
    public PayStatusEnum getPayStatusEnum() {
        return  EnumUtil.getByCode(payStatus, PayStatusEnum.class);
    }
}
