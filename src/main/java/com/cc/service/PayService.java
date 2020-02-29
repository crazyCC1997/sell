package com.cc.service;

import com.cc.dto.OrderMasterDTO;
import com.cc.pojo.OrderMaster;
import com.lly835.bestpay.model.PayResponse;
import com.lly835.bestpay.model.RefundResponse;

/**
 * 微信支付service
 *
 * @author cchen
 * @version 1.0
 * @date 2020/2/16 11:36
 */
public interface PayService {

    /**
     * 发起支付 返回支付相关信息
     * @param orderMasterDTO
     */
    PayResponse create(OrderMasterDTO orderMasterDTO);

    /**
     * 微信异步通知
     * @param notifyData
     */
    PayResponse notify(String notifyData);

    /**
     * 退款
     * @param orderMasterDTO
     */
    RefundResponse refund(OrderMasterDTO orderMasterDTO);
}
