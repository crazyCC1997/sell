package com.cc.service.impl;

import com.cc.dto.OrderMasterDTO;
import com.cc.service.OrderMasterService;
import com.cc.service.PayService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.lly835.bestpay.service.impl.BestPayServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * @author cchen
 * @version 1.0
 * @date 2020/2/16 13:34
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class PayServiceImplTest {

    @Autowired
    private PayService payService;

    @Autowired
    private OrderMasterService orderMasterService;

    @Test
    public void create() {
        OrderMasterDTO orderMasterDTO = orderMasterService.findOne("1581656038820401478");
        payService.create(orderMasterDTO);

    }

    @Test
    public void refund(){
        OrderMasterDTO orderMasterDTO = orderMasterService.findOne("1581656038820401478");
        payService.refund(orderMasterDTO);
    }
}