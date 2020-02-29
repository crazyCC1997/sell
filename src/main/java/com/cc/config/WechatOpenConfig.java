package com.cc.config;

import me.chanjar.weixin.mp.api.WxMpConfigStorage;
import me.chanjar.weixin.mp.api.WxMpInMemoryConfigStorage;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.impl.WxMpServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 微信开放平台配置类
 *
 * @author cchen
 * @version 1.0
 * @date 2020/2/20 17:41
 */
@Configuration
public class WechatOpenConfig {

    @Autowired
    private WechatProperties wechatProperties;

    @Bean
    public WxMpService wxOpenService() {
        WxMpService wxOpenService = new WxMpServiceImpl();
        wxOpenService.setWxMpConfigStorage(wxMpConfigStorage());
        return wxOpenService;
    }

    @Bean
    public WxMpConfigStorage wxMpConfigStorage() {
        WxMpInMemoryConfigStorage configStorage = new WxMpInMemoryConfigStorage();
        configStorage.setAppId(wechatProperties.getOpenAppId());
        configStorage.setSecret(wechatProperties.getOpenAppSecret());
        return configStorage;
    }
}
