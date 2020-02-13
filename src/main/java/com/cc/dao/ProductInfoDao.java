package com.cc.dao;

import com.cc.pojo.ProductInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * 商品dao
 *
 * @author cchen
 * @version 1.0
 * @date 2020/2/11 9:49
 */
public interface ProductInfoDao extends JpaRepository<ProductInfo, String> {

    List<ProductInfo> findByProductStatus(Integer productStatus);

}
