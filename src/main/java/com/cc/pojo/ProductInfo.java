package com.cc.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;

/**
 * 商品
 *
 * @author cchen
 * @version 1.0
 * @date 2020/2/11 9:24
 */
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class ProductInfo {

    /**
     * 商品id
     */
    @Id
    private String productId;

    /**
     * 商品名
     */
    private String productName;

    /**
     * 商品单价
     */
    private BigDecimal productPrice;

    /**
     * 商品库存
     */
    private Integer productStock;

    /**
     * 商品描述
     */
    private String productDescription;

    /**
     * 小图
     */
    private String productIcon;

    /**
     * 商品状态  0正常 1下架
     */
    private Integer productStatus;

    /**
     * 商品类目
     */
    private Integer categoryType;
}
