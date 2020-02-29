package com.cc.service.impl;

import com.cc.dao.ProductCategoryDao;
import com.cc.pojo.ProductCategory;
import com.cc.service.ProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 商品类目service实现
 *
 * @author cchen
 * @version 1.0
 * @date 2020/2/10 23:07
 */
@Service
public class ProductCategoryServiceImpl implements ProductCategoryService {

    @Autowired
    private ProductCategoryDao productCategoryDao;

    @Override
    public ProductCategory findOne(Integer categoryId) {
        return productCategoryDao.findOne(categoryId);
    }

    @Override
    public List<ProductCategory> findAll() {
        return productCategoryDao.findAll();
    }

    @Override
    public List<ProductCategory> findByCategoryType(List<Integer> categoryTypeList) {
        return productCategoryDao.findByCategoryType(categoryTypeList);
    }

    @Override
    public ProductCategory save(ProductCategory productCategory) {
        return productCategoryDao.save(productCategory);
    }
}
