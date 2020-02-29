package com.cc.service.impl;

import com.cc.dto.OrderMasterDTO;
import com.cc.enums.OrderStatusEnum;
import com.cc.enums.PayStatusEnum;
import com.cc.pojo.OrderDetail;
import lombok.Builder;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @author cchen
 * @version 1.0
 * @date 2020/2/13 10:30
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class OrderMasterServiceImplTest {

    @Autowired
    OrderMasterServiceImpl orderMasterService;

    private final String BUYER_OPENID = "110110";

    private final String ORDER_ID = "15815637458085223636";

    @Test
    public void create() {
        List<OrderDetail> orderDetailList = new ArrayList<>();
        orderDetailList.add(
                OrderDetail
                        .builder()
                        .productId("1234568")
                        .productQuantity(1)
                        .build()
        );
        orderDetailList.add(
                OrderDetail
                        .builder()
                        .productId("1234567")
                        .productQuantity(2)
                        .build()
        );

        OrderMasterDTO orderMasterDTO = orderMasterService.create(
                OrderMasterDTO
                        .builder()
                        .buyerAddress("东北")
                        .buyerName("电棍")
                        .buyerOpenid(BUYER_OPENID)
                        .buyerPhone("18936548888")
                        .orderDetailList(orderDetailList)
                        .build()
        );
        log.info("【创建订单】 orderMasterDTO={}", orderMasterDTO);
        Assert.assertNotNull(orderMasterDTO);
    }

    @Test
    public void findOne() {
        OrderMasterDTO result = orderMasterService.findOne(ORDER_ID);
        log.info("【单个订单详情】 result={}", result);
        Assert.assertEquals(ORDER_ID, result.getOrderId());
    }

    @Test
    public void findList() {
        PageRequest pageRequest = new PageRequest(0, 1);
        Page<OrderMasterDTO> orderMasterDTOPage = orderMasterService.findList(BUYER_OPENID, pageRequest);
        Assert.assertNotEquals(0, orderMasterDTOPage.getTotalElements());
    }

    @Test
    public void cancel() {
        OrderMasterDTO orderMasterDTO = orderMasterService.findOne(ORDER_ID);
        OrderMasterDTO result = orderMasterService.cancel(orderMasterDTO);
        Assert.assertEquals(OrderStatusEnum.CANCEL.getValue(), result.getOrderStatus());
    }

    @Test
    public void finish() {
        OrderMasterDTO orderMasterDTO = orderMasterService.findOne(ORDER_ID);
        OrderMasterDTO result = orderMasterService.finish(orderMasterDTO);
        Assert.assertEquals(OrderStatusEnum.FINISHED.getValue(), result.getOrderStatus());
    }

    @Test
    public void paid() {
        OrderMasterDTO orderMasterDTO = orderMasterService.findOne(ORDER_ID);
        OrderMasterDTO result = orderMasterService.paid(orderMasterDTO);
        Assert.assertEquals(PayStatusEnum.SUCCESS.getValue(), result.getPayStatus());
    }

    @Test
    public void findAllList(){
        PageRequest pageRequest = new PageRequest(0, 10);
        Page<OrderMasterDTO> orderMasterDTOPage = orderMasterService.findList(pageRequest);
//        Assert.assertNotEquals(0, orderMasterDTOPage.getTotalElements());
        Assert.assertTrue("查询所有的订单列表", orderMasterDTOPage.getTotalElements() > 0);
    }
}