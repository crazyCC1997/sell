package com.cc.config;

import com.lly835.bestpay.config.WxPayH5Config;
import com.lly835.bestpay.service.impl.BestPayServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 微信支付配置类
 *
 * @author cchen
 * @version 1.0
 * @date 2020/2/16 13:13
 */
@Configuration
public class WechatPayConfig {

    @Autowired
    private WechatProperties wechatProperties;

    @Bean
    public BestPayServiceImpl bestPayService(){
        BestPayServiceImpl bestPayService = new BestPayServiceImpl();
        bestPayService.setWxPayH5Config(wxPayH5Config());
        return bestPayService;
    }

    @Bean
    public WxPayH5Config wxPayH5Config(){
        WxPayH5Config wxPayH5Config = new WxPayH5Config();
        wxPayH5Config.setAppId(wechatProperties.getMpAppId());
        wxPayH5Config.setAppSecret(wechatProperties.getMpAppSecret());
        wxPayH5Config.setMchId(wechatProperties.getMchId());
        wxPayH5Config.setMchKey(wechatProperties.getMchKey());
        wxPayH5Config.setKeyPath(wechatProperties.getKeyPath());
        wxPayH5Config.setNotifyUrl(wechatProperties.getNotifyUrl());
        return wxPayH5Config;
    }
}
