package com.cc.form;

import antlr.debug.MessageAdapter;
import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.Id;
import java.math.BigDecimal;

/**
 * 商品数据表单
 *
 * @author cchen
 * @version 1.0
 * @date 2020/2/20 10:35
 */
@Data
public class ProductForm {

    /**
     * 商品id
     */
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
     * 商品类目
     */
    private Integer categoryType;

}
