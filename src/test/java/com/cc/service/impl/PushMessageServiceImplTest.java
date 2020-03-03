package com.cc.service.impl;

import com.cc.dto.OrderMasterDTO;
import com.cc.service.OrderMasterService;
import com.cc.service.PushMessageService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * @author cchen
 * @version 1.0
 * @date 2020/3/1 13:49
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class PushMessageServiceImplTest {

    @Autowired
    private PushMessageServiceImpl pushMessageService;

    @Autowired
    private OrderMasterService orderMasterService;

    @Test
    public void orderStatusChange() {
        OrderMasterDTO orderMasterDTO = orderMasterService.findOne("15820322106438751432");
        pushMessageService.orderStatusChange(orderMasterDTO);

    }
}