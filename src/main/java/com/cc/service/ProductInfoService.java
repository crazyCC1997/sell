package com.cc.service;

import com.cc.dto.CartDTO;
import com.cc.pojo.ProductInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * 商品service接口
 *
 * @author cchen
 * @version 1.0
 * @date 2020/2/11 10:07
 */
public interface ProductInfoService {

    ProductInfo findOne(String productId);

    /**
     * 查询所有在上架商品列表
     * @return
     */
    List<ProductInfo> findUpAll();

    /**
     * 分页查询所有商品
     * @param pageable
     * @return
     */
    Page<ProductInfo> findAll(Pageable pageable);

    ProductInfo save(ProductInfo productInfo);

    /**
     * 加库存
     * @param cartDTOList 购物车DTO
     */
    void increaseStock(List<CartDTO> cartDTOList);

    /**
     * 减库存
     * @param cartDTOList 购物车DTO
     */
    void decreaseStock(List<CartDTO> cartDTOList);

}
