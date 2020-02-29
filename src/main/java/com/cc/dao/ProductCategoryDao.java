package com.cc.dao;

import com.cc.pojo.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * 商品类目dao
 *
 * @author cchen
 * @version 1.0
 * @date 2020/2/10 20:58
 */
public interface ProductCategoryDao extends JpaRepository<ProductCategory, Integer> {

    List<ProductCategory> findByCategoryType(List<Integer> categoryTypeList);
}
