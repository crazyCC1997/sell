package com.cc.converter;

import com.cc.dto.OrderMasterDTO;
import com.cc.enums.ResultEnum;
import com.cc.exception.SellException;
import com.cc.form.OrderForm;
import com.cc.pojo.OrderDetail;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 订单表单信息转为OrderMasterDTO
 *
 * @author cchen
 * @version 1.0
 * @date 2020/2/14 11:26
 */
@Slf4j
public class OrderForm2OrderMasterDTOConverter {

    public static OrderMasterDTO convert(OrderForm orderForm){
        Gson gson = new Gson();

        OrderMasterDTO orderMasterDTO = new OrderMasterDTO();
        orderMasterDTO.setBuyerName(orderForm.getName());
        orderMasterDTO.setBuyerPhone(orderForm.getPhone());
        orderMasterDTO.setBuyerAddress(orderForm.getAddress());
        orderMasterDTO.setBuyerOpenid(orderForm.getOpenid());

        //将json格式的items转换为List类型
        List<OrderDetail> orderDetailList = new ArrayList<>();
        try {
            orderDetailList = gson.fromJson(orderForm.getItems(),
                    new TypeToken<List<OrderDetail>>(){}.getType());
        } catch (Exception e){
            log.error("【对象转换】 错误，String={}", orderForm.getItems());
            throw new SellException(ResultEnum.PARAM_ERROR);
        }
        orderMasterDTO.setOrderDetailList(orderDetailList);
        return orderMasterDTO;
    }

    public static List<OrderMasterDTO> convert(List<OrderForm> orderFormList){
        return orderFormList.stream().map(
                e -> convert(e)
        ).collect(Collectors.toList());
    }
}
