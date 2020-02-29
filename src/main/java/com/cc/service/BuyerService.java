package com.cc.service;

import com.cc.dto.OrderMasterDTO;

/**
 * 买家服务接口
 *
 * @author cchen
 * @version 1.0
 * @date 2020/2/14 17:16
 */
public interface BuyerService {

    /**
     * 查询一个订单
     *
     * @param openid
     * @param orderId
     * @return
     */
    OrderMasterDTO findOrderOne(String openid, String orderId);

    /**
     * 取消订单
     *
     * @param openid
     * @param orderId
     * @return
     */
    OrderMasterDTO cancelOrder(String openid, String orderId);
}
