package com.cc.converter;

import com.cc.dto.OrderMasterDTO;
import com.cc.pojo.OrderMaster;
import com.fasterxml.jackson.databind.util.BeanUtil;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * OrderMaster转为OrderMasterDTO
 *
 * @author cchen
 * @version 1.0
 * @date 2020/2/13 13:27
 */
public class OrderMaster2OrderMasterDTOConverter {

    public static OrderMasterDTO convert(OrderMaster orderMaster){
        OrderMasterDTO orderMasterDTO = new OrderMasterDTO();
        BeanUtils.copyProperties(orderMaster, orderMasterDTO);
        return orderMasterDTO;
    }

    public static List<OrderMasterDTO> convert(List<OrderMaster> orderMasterList){
        return orderMasterList.stream().map(
                e -> convert(e)
        ).collect(Collectors.toList());
    }
}
