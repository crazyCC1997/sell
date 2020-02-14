package com.cc.service.impl;

import com.cc.dto.OrderMasterDTO;
import com.cc.enums.ResultEnum;
import com.cc.exception.SellException;
import com.cc.service.BuyerService;
import com.cc.service.OrderMasterService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 买家实现类
 *
 * @author cchen
 * @version 1.0
 * @date 2020/2/14 17:22
 */
@Slf4j
@Service
public class BuyerServiceImpl implements BuyerService {

    @Autowired
    private OrderMasterService orderMasterService;

    @Override
    public OrderMasterDTO findOrderOne(String openid, String orderId) {
        return checkOrderMasterOwner(openid, orderId);
    }

    @Override
    public OrderMasterDTO cancelOrder(String openid, String orderId) {
        OrderMasterDTO orderMasterDTO = checkOrderMasterOwner(openid, orderId);
        if (null == orderMasterDTO){
            log.error("【取消订单】 订单不存在 orderId={}", orderId);
            throw new SellException(ResultEnum.ORDER_NOT_EXIST);
        }
        return orderMasterService.cancel(orderMasterDTO);
    }

    private OrderMasterDTO checkOrderMasterOwner(String openid, String orderId){
        OrderMasterDTO orderMasterDTO = orderMasterService.findOne(orderId);
        if(null == orderMasterDTO){
            return null;
        }
        //判断是否是本人订单
        if(!orderMasterDTO.getBuyerOpenid().equalsIgnoreCase(orderId)){
            log.error("【查询订单】 非本人订单，无法查询");
            throw new SellException(ResultEnum.ORDER_OWNER_ERROR);
        }
        return orderMasterDTO;
    }
}
