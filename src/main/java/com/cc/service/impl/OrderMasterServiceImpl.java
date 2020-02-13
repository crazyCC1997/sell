package com.cc.service.impl;

import com.cc.converter.OrderMaster2OrderMasterDTOConverter;
import com.cc.dao.OrderDetailDao;
import com.cc.dao.OrderMasterDao;
import com.cc.dto.CartDTO;
import com.cc.dto.OrderMasterDTO;
import com.cc.enums.OrderStatusEnum;
import com.cc.enums.PayStatusEnum;
import com.cc.enums.ResultEnum;
import com.cc.exception.SellException;
import com.cc.pojo.OrderDetail;
import com.cc.pojo.OrderMaster;
import com.cc.pojo.ProductInfo;
import com.cc.service.OrderMasterService;
import com.cc.service.ProductInfoService;
import com.cc.utils.KeyUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.xml.transform.Result;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 订单实现类
 *
 * @author cchen
 * @version 1.0
 * @date 2020/2/12 18:29
 */
@Service
@Slf4j
public class OrderMasterServiceImpl implements OrderMasterService {

    @Autowired
    private OrderMasterDao orderMasterDao;

    @Autowired
    private OrderDetailDao orderDetailDao;

    @Autowired
    private ProductInfoService productInfoService;

    @Override
    @Transactional
    public OrderMasterDTO create(OrderMasterDTO orderMasterDTO) {

        String orderId = KeyUtil.genUniqueKey();
        BigDecimal orderAmount = new BigDecimal(BigInteger.ZERO);
//        ArrayList<CartDTO> cartDTOList = new ArrayList<>();

        //1.查询订单（价格，数量）
        for (OrderDetail orderDetail : orderMasterDTO.getOrderDetailList()){
            ProductInfo productInfo = productInfoService.findOne(orderDetail.getProductId());
            if (null == productInfo){
                throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
            }

            //2.计算订单总价格
            orderAmount =productInfo.getProductPrice()
                    .multiply(new BigDecimal(orderDetail.getProductQuantity()))
                    .add(orderAmount);

            //3.信息入库（OrderMaster和OrderDetail）
            orderDetail.setOrderId(orderId);
            orderDetail.setDetailId(KeyUtil.genUniqueKey());
            BeanUtils.copyProperties(productInfo, orderDetail);
            orderDetailDao.save(orderDetail);

            //从订单详情中获取购物车信息(传统方法)
//            CartDTO cartDTO = new CartDTO(orderDetail.getProductId(), orderDetail.getProductQuantity());
//            cartDTOList.add(cartDTO);
        }
        OrderMaster orderMaster = new OrderMaster();
        //先拷贝,再设置。以免orderMasterDTO中的null值覆盖
        BeanUtils.copyProperties(orderMasterDTO, orderMaster);
        orderMaster.setOrderId(orderId);
        orderMaster.setOrderAmount(orderAmount);
        orderMaster.setOrderStatus(OrderStatusEnum.NEW.getValue());
        orderMaster.setPayStatus(PayStatusEnum.WAIT.getValue());
        orderMasterDao.save(orderMaster);

        //4.库存变化
        //lambda表达式
        List<CartDTO> cartDTOList = orderMasterDTO.getOrderDetailList().stream().map(
                e -> (new CartDTO(e.getProductId(), e.getProductQuantity()))
        ).collect(Collectors.toList());
        productInfoService.decreaseStock(cartDTOList);

        return orderMasterDTO;
    }

    @Override
    public OrderMasterDTO findOne(String orderId) {
        OrderMaster orderMaster = orderMasterDao.findOne(orderId);
        if (null == orderMaster){
            throw new SellException(ResultEnum.ORDER_NOT_EXIST);
        }
        List<OrderDetail> orderDetailList = orderDetailDao.findByOrderId(orderId);
        if (CollectionUtils.isEmpty(orderDetailList)){
            throw new SellException(ResultEnum.ORDER_DETAIL_NOT_EXIST);
        }
        OrderMasterDTO orderMasterDTO = new OrderMasterDTO();
        BeanUtils.copyProperties(orderMaster, orderMasterDTO);
        orderMasterDTO.setOrderDetailList(orderDetailList);
        return orderMasterDTO;
    }

    @Override
    public Page<OrderMasterDTO> findList(String buyerOpenid, Pageable pageable) {
        Page<OrderMaster> orderMasterPage = orderMasterDao.findByBuyerOpenid(buyerOpenid, pageable);
        List<OrderMasterDTO> orderMasterDTOList = OrderMaster2OrderMasterDTOConverter.convert(orderMasterPage.getContent());
        PageImpl<OrderMasterDTO> orderMasterDTOPage = new PageImpl<>(orderMasterDTOList, pageable, orderMasterPage.getTotalPages());
        return orderMasterDTOPage;
    }

    @Override
    @Transactional
    public OrderMasterDTO cancel(OrderMasterDTO orderMasterDTO) {
        //判断订单状态
        if (!orderMasterDTO.getOrderStatus().equals(OrderStatusEnum.NEW.getValue())){
            log.error("【取消订单】 订单状态不正确，orderId={}，orderStatus={}", orderMasterDTO.getOrderId(), orderMasterDTO.getOrderStatus());
            throw  new SellException(ResultEnum.ORDER_STATUS_ERROR);
        }
        //修改订单状态
        orderMasterDTO.setOrderStatus(OrderStatusEnum.CANCEL.getValue());
        OrderMaster orderMaster = new OrderMaster();
        BeanUtils.copyProperties(orderMasterDTO, orderMaster);
        OrderMaster updateResult = orderMasterDao.save(orderMaster);
        if(null == updateResult){
            log.error("【取消订单】 更新失败，orderMaster={}", orderMaster);
            throw new SellException(ResultEnum.ORDER_UPDATE_FAIL);
        }
        //返还库存
        if (CollectionUtils.isEmpty(orderMasterDTO.getOrderDetailList())){
            log.error("【取消订单】 订单中无商品详情，orderMasterDTO={}", orderMasterDTO);
            throw new SellException(ResultEnum.ORDER_DETAIL_EMPTY);
        }

        List<CartDTO> cartDTOList = orderMasterDTO.getOrderDetailList().stream().map(
                e -> (new CartDTO(e.getProductId(), e.getProductQuantity()))
        ).collect(Collectors.toList());
        productInfoService.increaseStock(cartDTOList);

        //如果已支付，需要退款
        if (orderMasterDTO.getPayStatus().equals(PayStatusEnum.SUCCESS.getValue())){
            //TODO
        }
        return orderMasterDTO;
    }

    @Override
    @Transactional
    public OrderMasterDTO finish(OrderMasterDTO orderMasterDTO) {
        //判断订单状态
        if (!OrderStatusEnum.NEW.getValue().equals(orderMasterDTO.getOrderStatus())){
            log.error("【完结订单】 订单状态不正确，orderId={}, orderStatus={}", orderMasterDTO.getOrderId(), orderMasterDTO.getOrderStatus());
            throw new SellException(ResultEnum.ORDER_STATUS_ERROR);
        }
        //修改订单状态
        orderMasterDTO.setOrderStatus(OrderStatusEnum.FINISHED.getValue());
        OrderMaster orderMaster = new OrderMaster();
        BeanUtils.copyProperties(orderMasterDTO, orderMaster);
        OrderMaster updateResult  = orderMasterDao.save(orderMaster);
        if(null == updateResult){
            log.error("【完结订单】 订单更新失败，oderMaster={}", orderMaster);
            throw new SellException(ResultEnum.ORDER_UPDATE_FAIL);
        }
        return orderMasterDTO;
    }

    @Override
    @Transactional
    public OrderMasterDTO paid(OrderMasterDTO orderMasterDTO) {
        //判断订单状态
        if (!OrderStatusEnum.NEW.getValue().equals(orderMasterDTO.getOrderStatus())){
            log.error("【支付订单】 订单状态不正确，orderId={}, orderStatus={}", orderMasterDTO.getOrderId(), orderMasterDTO.getOrderStatus());
            throw new SellException(ResultEnum.ORDER_STATUS_ERROR);
        }
        //判断支付状态
        if (!PayStatusEnum.WAIT.getValue().equals(orderMasterDTO.getPayStatus())){
            log.error("【支付订单】 支付状态不正确 orderId={}, payStatus={}", orderMasterDTO.getOrderId(), orderMasterDTO.getPayStatus());
            throw new SellException(ResultEnum.ORDER_PAY_STATUS_ERROR);
        }
        //修改订单状态
        orderMasterDTO.setPayStatus(PayStatusEnum.SUCCESS.getValue());
        OrderMaster orderMaster = new OrderMaster();
        BeanUtils.copyProperties(orderMasterDTO, orderMaster);
        OrderMaster updateResult = orderMasterDao.save(orderMaster);
        if(null == updateResult){
            log.error("【支付订单】 订单更新失败, orderMaster={}", orderMaster);
            throw new SellException(ResultEnum.ORDER_UPDATE_FAIL);
        }
        return orderMasterDTO;
    }
}
