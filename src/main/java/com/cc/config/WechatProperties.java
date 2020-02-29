package com.cc.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 微信配置类
 *
 * @author cchen
 * @version 1.0
 * @date 2020/2/15 17:52
 */
@Data
@Component
@ConfigurationProperties(prefix = "wechat")
public class WechatProperties {

    /**
     * 公众号appId
     */
    private String mpAppId;

    /**
     * 公众号appSecret
     */
    private String mpAppSecret;

    /**
     * 开发平台appId
     */
    private String openAppId;

    /**
     * 开放平台appSecret
     */
    private String openAppSecret;

    /**
     * 商户号
     */
    private String mchId;

    /**
     * 商户秘钥
     */
    private String mchKey;

    /**
     * 商户证书路径
     */
    private String keyPath;

    /**
     * 微信支付异步通知地址
     */
    private String notifyUrl;
}
