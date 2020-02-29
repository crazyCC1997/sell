package com.cc.service.impl;

import com.cc.pojo.SellerInfo;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * @author cchen
 * @version 1.0
 * @date 2020/2/20 17:20
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class SellerServiceImplTest {

    @Autowired
    private SellerServiceImpl sellerService;

    @Test
    public void findSellerInfoByOpenid() {

        SellerInfo sellerInfo = sellerService.findSellerInfoByOpenid("abc");
        Assert.assertEquals("abc", sellerInfo.getOpenid());
    }
}