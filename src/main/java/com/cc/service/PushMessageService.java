package com.cc.service;

import com.cc.dto.OrderMasterDTO;

/**
 * 微信模板消息推送service
 *
 * @author cchen
 * @version 1.0
 * @date 2020/3/1 12:58
 */
public interface PushMessageService {

    /**
     * 订单状态消息变更
     *
     * @param orderMasterDTO
     */
    void orderStatusChange(OrderMasterDTO orderMasterDTO);
}
