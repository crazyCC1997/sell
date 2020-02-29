package com.cc.service.impl;

import com.cc.dto.OrderMasterDTO;
import com.cc.enums.ResultEnum;
import com.cc.exception.SellException;
import com.cc.service.OrderMasterService;
import com.cc.service.PayService;
import com.cc.utils.JsonUtil;
import com.cc.utils.MathUtil;
import com.lly835.bestpay.enums.BestPayTypeEnum;
import com.lly835.bestpay.model.PayRequest;
import com.lly835.bestpay.model.PayResponse;
import com.lly835.bestpay.model.RefundRequest;
import com.lly835.bestpay.model.RefundResponse;
import com.lly835.bestpay.service.impl.BestPayServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * 微信支付实现类
 *
 * @author cchen
 * @version 1.0
 * @date 2020/2/16 11:40
 */
@Service
@Slf4j
public class PayServiceImpl implements PayService {

    private static final String ORDER_NAME = "微信点餐订单";

    @Autowired
    private BestPayServiceImpl bestPayService;

    @Autowired
    private OrderMasterService orderMasterService;

    @Override
    public PayResponse create(OrderMasterDTO orderMasterDTO) {
        PayRequest payRequest = new PayRequest();
        payRequest.setOpenid(orderMasterDTO.getBuyerOpenid());
        payRequest.setOrderAmount(orderMasterDTO.getOrderAmount().doubleValue());
        payRequest.setOrderId(orderMasterDTO.getOrderId());
        payRequest.setOrderName(ORDER_NAME);
        payRequest.setPayTypeEnum(BestPayTypeEnum.WXPAY_H5);
        log.info("【微信支付】发起支付，request={}", JsonUtil.toJson(payRequest));
        PayResponse payResponse = bestPayService.pay(payRequest);
        log.info("【微信支付】发起支付，response={}" , JsonUtil.toJson(payResponse));

        return payResponse;
    }

    @Override
    public PayResponse notify(String notifyData) {
        //1.验证签名
        //2.支付的状态
        //3.支付金额
        //4.支付人（下单==支付人）

        PayResponse payResponse = bestPayService.asyncNotify(notifyData);
        log.info("【微信支付】 异步通知，payResponse={}", payResponse);
        OrderMasterDTO orderMasterDTO = orderMasterService.findOne(payResponse.getOrderId());
        if(null == orderMasterDTO){
           log.error("【微信支付】异步通知，查询订单不存在，orderId={}", payResponse.getOrderId());
           throw new SellException(ResultEnum.ORDER_NOT_EXIST);
        }
        //判断金额是否一致(0.10和0.1不相等)
        //(orderMasterDTO.getOrderAmount().compareTo(new BigDecimal(payResponse.getOrderAmount()))) != 0
        if(!MathUtil.equals(orderMasterDTO.getOrderAmount().doubleValue(), payResponse.getOrderAmount())){
            log.error("【微信支付】 异步通知，支付金额不一致，orderId={}，微信通知金额={}，系统金额={}",
                    payResponse.getOrderId(),
                    payResponse.getOrderAmount(),
                    orderMasterDTO.getOrderAmount());
            throw new SellException(ResultEnum.WXPAY_NOTIFY_MONEY_VERIFY_ERROR);
        }
        //修改订单状态
        orderMasterService.paid(orderMasterDTO);
        return payResponse;
    }

    @Override
    public RefundResponse refund(OrderMasterDTO orderMasterDTO) {
        RefundRequest refundRequest = new RefundRequest();
        refundRequest.setOrderId(orderMasterDTO.getOrderId());
        refundRequest.setOrderAmount(orderMasterDTO.getOrderAmount().doubleValue());
        refundRequest.setPayTypeEnum(BestPayTypeEnum.WXPAY_H5);
        log.info("【微信退款】 refundRequest={}", JsonUtil.toJson(refundRequest));
        RefundResponse refundResponse = bestPayService.refund(refundRequest);
        log.info("【微信退款】 refundResponse={}", JsonUtil.toJson(refundResponse));
        return refundResponse;
    }
}
