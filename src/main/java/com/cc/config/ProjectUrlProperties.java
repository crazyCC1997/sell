package com.cc.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * url配置类
 *
 * @author cchen
 * @version 1.0
 * @date 2020/2/20 18:23
 */
@Data
@Component
@ConfigurationProperties(prefix = "projectUrl")
public class ProjectUrlProperties {

    /**
     * 微信公众平台授权url
     */
    private String wechatMpAuthorize;

    /**
     * 微信开放平台授权url
     */
    private String wechatOpenAuthorize;

    /**
     * 项目url
     */
    private String sell;
}
