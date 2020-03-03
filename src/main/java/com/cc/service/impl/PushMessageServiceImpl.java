package com.cc.service.impl;

import com.cc.config.WechatProperties;
import com.cc.dto.OrderMasterDTO;
import com.cc.service.PushMessageService;
import lombok.Builder;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateData;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 微信消息推送实现类
 *
 * @author cchen
 * @version 1.0
 * @date 2020/3/1 13:19
 */
@Service
@Slf4j
public class PushMessageServiceImpl implements PushMessageService {

    @Autowired
    private WxMpService wxMpService;

    @Autowired
    private WechatProperties wechatProperties;

    @Override
    public void orderStatusChange(OrderMasterDTO orderMasterDTO) {
        WxMpTemplateMessage wxMpTemplateMessage = new WxMpTemplateMessage();
        wxMpTemplateMessage.setTemplateId(wechatProperties.getTemplateId().get("orderStatus"));
        wxMpTemplateMessage.setToUser(orderMasterDTO.getBuyerOpenid());

        List<WxMpTemplateData> data = Arrays.asList(
                new WxMpTemplateData("first", "亲，请记得收货"),
                new WxMpTemplateData("keyword1", "微信点餐"),
                new WxMpTemplateData("keyword2", "18936549088"),
                new WxMpTemplateData("keyword3", orderMasterDTO.getOrderId()),
                new WxMpTemplateData("keyword4", orderMasterDTO.getOrderStatusEnum().getMessage()),
                new WxMpTemplateData("keyword5", "¥" + orderMasterDTO.getOrderAmount()),
                new WxMpTemplateData("remark", "欢迎再次光临！")
        );
        wxMpTemplateMessage.setData(data);
        try {
            wxMpService.getTemplateMsgService().sendTemplateMsg(wxMpTemplateMessage);
        } catch (WxErrorException e) {
            log.error("【微信模板消息】发送失败，{}", e);
        }
    }
}
