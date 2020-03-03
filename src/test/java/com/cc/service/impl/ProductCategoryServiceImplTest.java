package com.cc.service.impl;

import com.cc.pojo.ProductCategory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @author cchen
 * @version 1.0
 * @date 2020/2/10 23:10
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductCategoryServiceImplTest {

    @Autowired
    private ProductCategoryServiceImpl productCategoryServiceImpl;

    @Test
    public void findOne() {
        ProductCategory result = productCategoryServiceImpl.findOne(1);
        System.out.println(result);
        Assert.assertEquals(new Integer(1), result.getCategoryId());
    }

    @Test
    public void findAll() {
        List<ProductCategory> lists = productCategoryServiceImpl.findAll();
        Assert.assertNotEquals(0, lists.size());

    }

    @Test
    public void findByCategoryTypeIn() {
        List<ProductCategory> lists = productCategoryServiceImpl.findByCategoryTypeIn(Arrays.asList(1, 2, 3, 4));
        Assert.assertNotEquals(0, lists.size());
    }

    @Test
    public void save() {
        ProductCategory productCategory = new ProductCategory();
        productCategory.setCategoryName("男士专享");
        productCategory.setCategoryType(11);
        ProductCategory result = productCategoryServiceImpl.save(productCategory);
        Assert.assertNotNull(result);
    }
}