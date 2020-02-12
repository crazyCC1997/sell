package com.cc.pojo;

import com.cc.enums.OrderStatusEnum;
import com.cc.enums.PayStatusEnum;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 订单表
 *
 * @author cchen
 * @version 1.0
 * @date 2020/2/12 11:07
 */
@Data
@Entity
@DynamicUpdate
public class OrderMaster {

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
    private Integer orderStatus = OrderStatusEnum.NEW.getValue();

    /**
     * 支付状态 默认 0：等待支付
     */
    private Integer payStatus = PayStatusEnum.WAIT.getValue();

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改时间
     */
    private Date updateTime;
}
