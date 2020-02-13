package com.cc.service;

import com.cc.dto.OrderMasterDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * 订单service
 *
 * @author cchen
 * @version 1.0
 * @date 2020/2/12 18:09
 */
public interface OrderMasterService {

    /**
     * 创建订单
     * @param orderMasterDTO 订单数据传输对象
     * @return
     */
    OrderMasterDTO create(OrderMasterDTO orderMasterDTO);

    /**
     * 查询单个订单
     * @param orderId
     * @return
     */
    OrderMasterDTO findOne(String orderId);

    /**
     * 查询买家订单列表
     * @param buyerOpenid
     * @param pageable
     * @return
     */
    Page<OrderMasterDTO> findList(String buyerOpenid, Pageable pageable);

    /**
     * 取消订单
     * @param orderMasterDTO
     * @return
     */
    OrderMasterDTO cancel(OrderMasterDTO orderMasterDTO);

    /**
     * 完成订单
     * @param orderMasterDTO
     * @return
     */
    OrderMasterDTO finish(OrderMasterDTO orderMasterDTO);

    /**
     * 支付订单
     * @param orderMasterDTO
     * @return
     */
    OrderMasterDTO paid(OrderMasterDTO orderMasterDTO);
}
