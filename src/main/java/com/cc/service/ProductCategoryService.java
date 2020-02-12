package com.cc.service;

import com.cc.pojo.ProductCategory;

import java.util.List;

/**
 * 商品类目service接口
 *
 * @author cchen
 * @version 1.0
 * @date 2020/2/10 23:02
 */
public interface ProductCategoryService {

    ProductCategory findOne(Integer categoryId);

    List<ProductCategory> findAll();

    /**
     * 根据类目编号查询商品类目
     * @param categoryTypeList
     * @return
     */
    List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList);

    ProductCategory save(ProductCategory productCategory);
}
