package com.cc.dao;

import com.cc.pojo.ProductInfo;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @author cchen
 * @version 1.0
 * @date 2020/2/11 9:57
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductInfoDaoTest {

    @Autowired
    private ProductInfoDao productInfoDao;

    @Test
    public void save(){
        ProductInfo productInfo = new ProductInfo();
        productInfo.setProductId("123456");
        productInfo.setProductName("南瓜粥");
        productInfo.setProductPrice(new BigDecimal(3.99));
        productInfo.setProductStock(100);
        productInfo.setProductDescription("又香又甜");
        productInfo.setProductIcon("http://ajdka.jpg");
        productInfo.setProductStatus(0);
        productInfo.setCategoryType(1);
        ProductInfo result = productInfoDao.save(productInfo);
        Assert.assertNotNull(result);

    }

    @Test
    public void findByProductStatusTest(){
        List<ProductInfo> lists = productInfoDao.findByProductStatus(0);
        Assert.assertNotEquals(0,lists.size());
    }

}