package com.cc.pojo;

import com.cc.enums.ProductStatusEnum;
import com.cc.utils.EnumUtil;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

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
@DynamicUpdate
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
    private Integer productStatus = ProductStatusEnum.UP.getValue();

    /**
     * 商品类目
     */
    private Integer categoryType;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改时间
     */
    private Date updateTime;

    @JsonIgnore
    public ProductStatusEnum getProductStatusEnum() {
        return EnumUtil.getByCode(productStatus, ProductStatusEnum.class);
    }
}
