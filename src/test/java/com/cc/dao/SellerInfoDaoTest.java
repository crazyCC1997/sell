package com.cc.dao;

import com.cc.pojo.SellerInfo;
import com.cc.utils.KeyUtil;
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
 * @date 2020/2/20 15:51
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class SellerInfoDaoTest {

    @Autowired
    private SellerInfoDao sellerInfoDao;

    @Test
    public void save() {
        SellerInfo sellerInfo = sellerInfoDao.save(
                SellerInfo
                        .builder()
                        .sellerId(KeyUtil.genUniqueKey())
                        .username("superAdmin")
                        .password("superAdmin")
                        .openid("ab123789")
                        .build()
        );
        Assert.assertTrue("新增成功", sellerInfo != null);

    }

    @Test
    public void findByOpenid() {
        SellerInfo sellerInfo = sellerInfoDao.findByOpenid("abc");
        Assert.assertEquals("abc", sellerInfo.getOpenid());
    }
}