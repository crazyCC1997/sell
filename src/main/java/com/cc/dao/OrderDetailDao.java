package com.cc.dao;

import com.cc.pojo.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * 订单详情dao
 *
 * @author cchen
 * @version 1.0
 * @date 2020/2/12 12:50
 */
public interface OrderDetailDao extends JpaRepository<OrderDetail, String> {

    List<OrderDetail> findByOrderId(String orderId);
}
