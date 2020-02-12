package com.cc.vo;

import com.cc.pojo.ProductInfo;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

/**
 * 商品Vo(包含category类目)
 *
 * @author cchen
 * @version 1.0
 * @date 2020/2/11 11:16
 */
@Data
public class ProductVo {

    /**
     * 类目名称
     */
    @JsonProperty("name")
    private String categoryName;

    /**
     * 类目类型
     */
    @JsonProperty("type")
    private Integer categoryType;

    @JsonProperty("foods")
    private List<ProductInfoVo> productInfoVoList;
}
