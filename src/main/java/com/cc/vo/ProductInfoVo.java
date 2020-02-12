package com.cc.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * 商品详情Vo
 *
 * @author cchen
 * @version 1.0
 * @date 2020/2/11 11:24
 */
@Data
public class ProductInfoVo {

    /**
     * 商品id
     */
    @JsonProperty("id")
    private String productId;

    /**
     * 商品名
     */
    @JsonProperty("name")
    private String productName;

    /**
     * 商品单价
     */
    @JsonProperty("price")
    private BigDecimal productPrice;

    /**
     * 商品描述
     */
    @JsonProperty("description")
    private String productDescription;

    /**
     * 小图
     */
    @JsonProperty("icon")
    private String productIcon;
}
