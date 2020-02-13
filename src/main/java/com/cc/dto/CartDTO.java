package com.cc.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.print.DocFlavor;

/**
 * 购物车DTO
 *
 * @author cchen
 * @version 1.0
 * @date 2020/2/13 9:42
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartDTO {

    /**
     * 商品id
     */
    private String productId;

    /**
     * 商品数量
     */
    private Integer productQuantity;
}
