package com.cc.dao;

import com.cc.pojo.OrderMaster;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.awt.print.Pageable;
import java.math.BigDecimal;

import static org.junit.Assert.*;

/**
 * @author cchen
 * @version 1.0
 * @date 2020/2/12 17:11
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderMasterDaoTest {

    @Autowired
    private OrderMasterDao orderMasterDao;

    private final String OPENID = "110110";

    @Test
    public void saveTest(){
        OrderMaster orderMaster = new OrderMaster();
        orderMaster.setOrderId("1234561");
        orderMaster.setBuyerName("大西瓜");
        orderMaster.setBuyerPhone("18936549088");
        orderMaster.setBuyerAddress("北京");
        orderMaster.setBuyerOpenid(OPENID);
        orderMaster.setOrderAmount(new BigDecimal(2.50));
        OrderMaster result = orderMasterDao.save(orderMaster);
        Assert.assertNotNull(result);
    }

    @Test
    public void findByBuyerOpenid(){

        PageRequest pageRequest = new PageRequest(0,2);
        Page<OrderMaster> result = orderMasterDao.findByBuyerOpenid(OPENID, pageRequest);
        System.out.println(result.getTotalElements());
        System.out.println(result.getTotalPages());
        Assert.assertNotEquals(0, result.getTotalElements());
    }
}